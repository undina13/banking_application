package com.undina.deal.service;

import com.undina.deal.entity.Application;
import com.undina.deal.entity.Client;
import com.undina.deal.entity.StatusHistory;
import com.undina.deal.enums.ApplicationStatus;
import com.undina.deal.enums.ChangeType;
import com.undina.deal.exception.NotFoundException;
import com.undina.deal.exception.RejectionException;
import com.undina.deal.exception.ValidationException;
import com.undina.deal.dto.*;
import com.undina.deal.repository.ApplicationRepository;
import com.undina.deal.repository.ClientRepository;
import com.undina.deal.mapper.ClientMapper;
import com.undina.deal.util.ModelFormatter;
import com.undina.deal.feign.MyFeignClient;
import com.undina.deal.mapper.ScoringDataMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.FinishRegistrationRequestDTO;
import org.openapitools.model.LoanApplicationRequestDTO;
import org.openapitools.model.LoanOfferDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DealService {
    private final ClientMapper clientMapper;

    private final ScoringDataMapper scoringDataMapper;

    private final ClientRepository clientRepository;

    private final ApplicationRepository applicationRepository;

   // private final ApplicationService applicationService;

    private final MyFeignClient myFeignClient;

    public List<LoanOfferDTO> createApplication(LoanApplicationRequestDTO loanApplication) {
        log.info("createApplication - start: {}", ModelFormatter.toLogFormat(loanApplication));
        Client client = clientMapper.toClient(loanApplication);
        client = clientRepository.save(client);
        log.info("savingClient: {}", client);
        Application application = new Application();
        application.setClient(client);
        application.setCreationDate(LocalDate.now());
        application = updateStatus(application, ApplicationStatus.PREAPPROVAL, ChangeType.AUTOMATIC);
        application = applicationRepository.save(application);
        log.info("save application: {}", application);
        List<LoanOfferDTO> loanOffers;
        try {
            loanOffers = myFeignClient.getOffers(loanApplication).getBody();
        } catch (FeignException e) {
            throw new ValidationException(e.getMessage());
        }
        if (loanOffers != null) {
            Application finalApplication = application;
            loanOffers.forEach(loanOfferDTO -> loanOfferDTO.setApplicationId(finalApplication.getApplicationId()));
        }
        log.info("createApplication - result: {}", loanOffers);
        return loanOffers;
    }

    public void applyOffer(LoanOfferDTO loanOffer) {
        log.info("applyOffer - start: {}", loanOffer);
        Application application = applicationRepository.findById(loanOffer.getApplicationId())
                .orElseThrow(() -> new NotFoundException("application not found? id = {}" + loanOffer.getApplicationId()));
        application = updateStatus(application, ApplicationStatus.APPROVED, ChangeType.AUTOMATIC);
        log.info("applyOffer application updated status: {}", application);
        application.setAppliedOffer(loanOffer);
        applicationRepository.save(application);
        log.info("applyOffer - result: {}", application);
    }

    public void calculateCredit(Long applicationId, FinishRegistrationRequestDTO finishRegistrationRequestDTO) {
        log.info("calculateCredit - start: applicationId = {}, finishRegistrationRequestDTO = {}", applicationId,
                ModelFormatter.toLogFormat(finishRegistrationRequestDTO));
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("application not found? id = {}" + applicationId));
        Client client = application.getClient();
        log.info("calculateCredit: client {}", client);
        ScoringDataDTO scoringDataDTO = scoringDataMapper.toScoringDataDTO(finishRegistrationRequestDTO, client,
                application.getAppliedOffer());
        log.info("calculateCredit: scoringDataDTO  {}", ModelFormatter.toLogFormat(scoringDataDTO));
        CreditDTO creditDTO = null;
        try {
            creditDTO = myFeignClient.calculateCredit(scoringDataDTO).getBody();
        } catch (FeignException e) {
            throw new RejectionException(e.getMessage());
        }
        log.info("calculateCredit - result  ok,  {}", creditDTO);
    }

    private Application updateStatus(Application application, ApplicationStatus status, ChangeType changeType) {
      //  log.info("updateStatus - start: {}, {}, {}", application, status, changeType);
        application.setStatus(status);
        List<StatusHistory> statusHistories = application.getStatusHistory();
        if (statusHistories == null) {
            statusHistories = new ArrayList<>();
        }
        statusHistories.add(new StatusHistory(status, LocalDateTime.now(), changeType));
        application.setStatusHistory(statusHistories);
      //  log.info("updateStatus - result: {}", application);
        return application;
    }
}
