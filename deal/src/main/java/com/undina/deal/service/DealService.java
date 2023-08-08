package com.undina.deal.service;

import com.undina.deal.exception.NotFoundException;
import com.undina.deal.exception.RejectionException;
import com.undina.deal.exception.ValidationException;
import com.undina.deal.model.*;
import com.undina.deal.repository.ApplicationRepository;
import com.undina.deal.repository.ClientRepository;
import com.undina.deal.util.ClientMapper;
import com.undina.deal.util.MyFeignClient;
import com.undina.deal.util.ScoringDataMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.FinishRegistrationRequestDTO;
import org.openapitools.model.LoanApplicationRequestDTO;
import org.openapitools.model.LoanOfferDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DealService {
    private final ClientMapper clientMapper;

    private final ScoringDataMapper scoringDataMapper;

    private final ClientRepository clientRepository;

    private final ApplicationRepository applicationRepository;

    private final ApplicationService applicationService;

    private final MyFeignClient myFeignClient;

    public List<LoanOfferDTO> createApplication(LoanApplicationRequestDTO loanApplication) {
        log.info("createApplication: {}", loanApplication);
        Client client = clientMapper.toClient(loanApplication);
        client = clientRepository.save(client);
        log.info("savingClient: {}", client);
        Application application = new Application();
        application.setClient(client);
        application.setCreationDate(LocalDate.now());
        application = applicationService.updateStatus(application, ApplicationStatus.PREAPPROVAL, ChangeType.AUTOMATIC);
        log.info("save application: {}", application);
        application = applicationRepository.save(application);
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
        log.info("createApplication result: {}", loanOffers);
        return loanOffers;
    }

    public void applyOffer(LoanOfferDTO loanOffer) {
        log.info("applyOffer: {}", loanOffer);
        Application application = applicationRepository.findById(loanOffer.getApplicationId())
                .orElseThrow(() -> new NotFoundException("application not found? id = {}" + loanOffer.getApplicationId()));
        application = applicationService.updateStatus(application, ApplicationStatus.APPROVED, ChangeType.AUTOMATIC);
        log.info("applyOffer application updated status: {}", application);
        application.setAppliedOffer(loanOffer);
        applicationRepository.save(application);
        log.info("applyOffer result: {}", application);
    }

    public void calculateCredit(Long applicationId, FinishRegistrationRequestDTO finishRegistrationRequestDTO) {
        log.info("calculateCredit: applicationId - {}, {}", applicationId, finishRegistrationRequestDTO);
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("application not found? id = {}" + applicationId));
        Client client = application.getClient();
        log.info("calculateCredit: client {}", client);
        ScoringDataDTO scoringDataDTO = scoringDataMapper.toScoringDataDTO(finishRegistrationRequestDTO, client,
                application.getAppliedOffer());
        log.info("calculateCredit: scoringDataDTO  {}", scoringDataDTO);
        CreditDTO creditDTO = null;
        try {
            creditDTO = myFeignClient.calculateCredit(scoringDataDTO).getBody();
        } catch (FeignException e) {
            throw new RejectionException(e.getMessage());
        }
        log.info("calculateCredit:  ok,  {}", creditDTO);
    }
}
