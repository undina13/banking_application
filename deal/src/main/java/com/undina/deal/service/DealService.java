package com.undina.deal.service;

import com.undina.deal.entity.Application;
import com.undina.deal.entity.Client;
import com.undina.deal.entity.StatusHistory;
import com.undina.deal.enums.ApplicationStatus;
import com.undina.deal.enums.ChangeType;
import com.undina.deal.exception.NotFoundException;
import com.undina.deal.feign.ConveyorFeignClient;
import com.undina.deal.mapper.ClientMapper;
import com.undina.deal.mapper.ScoringDataMapper;
import com.undina.deal.repository.ApplicationRepository;
import com.undina.deal.repository.ClientRepository;
import com.undina.deal.util.ModelFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.*;
import org.springframework.stereotype.Service;

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

    private final ConveyorFeignClient conveyorFeignClient;

    public List<LoanOfferDTO> createApplication(LoanApplicationRequestDTO loanApplication) {
        log.info("createApplication: {}", ModelFormatter.toLogFormat(loanApplication));
        Client client = clientMapper.toClient(loanApplication);
        client = clientRepository.save(client);
        log.info("savingClient: {}", client);
        Application application = new Application();
        application.setClient(client);
        application.setCreationDate(LocalDateTime.now());
        application = updateStatus(application, ApplicationStatus.PREAPPROVAL, ChangeType.AUTOMATIC);
        application = applicationRepository.save(application);
        log.info("save application: {}", application);
        List<LoanOfferDTO> loanOffers;
        loanOffers = conveyorFeignClient.getOffers(loanApplication).getBody();
        if (loanOffers != null) {
            Application finalApplication = application;
            loanOffers.forEach(loanOfferDTO -> loanOfferDTO.setApplicationId(finalApplication.getApplicationId()));
        }
        log.info("createApplication: {}", loanOffers);
        return loanOffers;
    }

    public void applyOffer(LoanOfferDTO loanOffer) {
        log.info("applyOffer: {}", loanOffer);
        Application application = applicationRepository.findById(loanOffer.getApplicationId())
                .orElseThrow(() -> new NotFoundException("application not found? id = {}" + loanOffer.getApplicationId()));
        application = updateStatus(application, ApplicationStatus.APPROVED, ChangeType.AUTOMATIC);
        log.info("applyOffer application updated status: {}", application);
        application.setAppliedOffer(loanOffer);
        applicationRepository.save(application);
        log.info("applyOffer: {}", application);
    }

    public void calculateCredit(Long applicationId, FinishRegistrationRequestDTO finishRegistrationRequestDTO) {
        log.info("calculateCredit: applicationId = {}, finishRegistrationRequestDTO = {}", applicationId,
                ModelFormatter.toLogFormat(finishRegistrationRequestDTO));
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("application not found? id = {}" + applicationId));
        Client client = application.getClient();
        log.info("calculateCredit: client {}", client);
        ScoringDataDTO scoringDataDTO = scoringDataMapper.toScoringDataDTO(finishRegistrationRequestDTO, client,
                application.getAppliedOffer());
        log.info("calculateCredit: scoringDataDTO  {}", ModelFormatter.toLogFormat(scoringDataDTO));
        CreditDTO creditDTO = conveyorFeignClient.calculateCredit(scoringDataDTO).getBody();
        log.info("calculateCredit:  ok,  {}", creditDTO);
    }

    private Application updateStatus(Application application, ApplicationStatus status, ChangeType changeType) {
        application.setStatus(status);
        List<StatusHistory> statusHistories = application.getStatusHistory();
        if (statusHistories == null) {
            statusHistories = new ArrayList<>();
        }
        statusHistories.add(new StatusHistory(status, LocalDateTime.now(), changeType));
        application.setStatusHistory(statusHistories);
        return application;
    }
}
