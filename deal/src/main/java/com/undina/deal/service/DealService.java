package com.undina.deal.service;

import com.undina.deal.entity.Application;
import com.undina.deal.entity.Client;
import com.undina.deal.entity.Credit;
import com.undina.deal.entity.StatusHistory;
import com.undina.deal.enums.ApplicationStatus;
import com.undina.deal.enums.ChangeType;
import com.undina.deal.enums.CreditStatus;
import com.undina.deal.exception.FeignDealException;
import com.undina.deal.exception.NotFoundException;
import com.undina.deal.feign.ConveyorFeignClient;
import com.undina.deal.mapper.ClientMapper;
import com.undina.deal.mapper.CreditMapper;
import com.undina.deal.mapper.ScoringDataMapper;
import com.undina.deal.repository.ApplicationRepository;
import com.undina.deal.repository.ClientRepository;
import com.undina.deal.repository.CreditRepository;
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

    private final CreditMapper creditMapper;

    private final ClientRepository clientRepository;

    private final CreditRepository creditRepository;

    private final ApplicationRepository applicationRepository;

    private final ConveyorFeignClient conveyorFeignClient;

    private final KafkaProducerService kafkaProducerService;

    public List<LoanOfferDTO> createApplication(LoanApplicationRequestDTO loanApplication) {
        log.info("createApplication - start: {}", ModelFormatter.toLogFormat(loanApplication));
        Client client = clientMapper.toClient(loanApplication);
        client = clientRepository.save(client);
        log.info("savingClient: {}", client);
        Application application = new Application();
        application.setClient(client);
        application.setCreationDate(LocalDateTime.now());
        updateStatus(application, ApplicationStatus.PREAPPROVAL);
        application = applicationRepository.save(application);
        log.info("save application: {}", application);
        List<LoanOfferDTO> loanOffers = conveyorFeignClient.getOffers(loanApplication).getBody();
        if (loanOffers == null) {
            throw new FeignDealException("The response body is null");
        }
        for (LoanOfferDTO loanOfferDTO : loanOffers) {
            loanOfferDTO.setApplicationId(application.getApplicationId());
        }
        EmailMessage emailMessage = new EmailMessage(application.getClient().getEmail(),
                EmailMessage.ThemeEnum.FINISH_REGISTRATION, application.getApplicationId(),
                loanOffers.toString());
        kafkaProducerService.writeMessage(emailMessage);
        log.info("createApplication - result: {}", loanOffers);
        return loanOffers;
    }

    public void applyOffer(LoanOfferDTO loanOffer) {
        log.info("applyOffer - start: {}", loanOffer);
        Application application = applicationRepository.findById(loanOffer.getApplicationId())
                .orElseThrow(() -> new NotFoundException("Application with id = {} not found"
                        + loanOffer.getApplicationId()));
        updateStatus(application, ApplicationStatus.APPROVED);
        log.info("applyOffer application updated status: {}", application);
        application.setAppliedOffer(loanOffer);
        applicationRepository.save(application);
        EmailMessage emailMessage = new EmailMessage(application.getClient().getEmail(),
                EmailMessage.ThemeEnum.CREATE_DOCUMENTS, application.getApplicationId(),
                "Кредитное предложение принято");
        kafkaProducerService.writeMessage(emailMessage);
        log.info("applyOffer - result: {}", application);
    }

    public void calculateCredit(Long applicationId, FinishRegistrationRequestDTO finishRegistrationRequestDTO) {
        log.info("calculateCredit - start: applicationId = {}, finishRegistrationRequestDTO = {}", applicationId,
                ModelFormatter.toLogFormat(finishRegistrationRequestDTO));
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Application with id = {} not found" + applicationId));
        Client client = application.getClient();
        log.info("calculateCredit: client {}", client);
        ScoringDataDTO scoringDataDTO = scoringDataMapper.toScoringDataDTO(finishRegistrationRequestDTO, client,
                application.getAppliedOffer());
        client = clientMapper.toClientFromFinishRegistrationRequestDTO(client, finishRegistrationRequestDTO);
        clientRepository.save(client);
        log.info("calculateCredit: scoringDataDTO  {}", ModelFormatter.toLogFormat(scoringDataDTO));
        CreditDTO creditDTO;

        try {
            creditDTO = conveyorFeignClient.calculateCredit(scoringDataDTO).getBody();
        } catch (Exception e) {
            log.info("calculateCredit - exception:    {}", e.getMessage());
            if (e.getMessage().contains("Вам отказано в кредите")) {
                EmailMessage emailMessage = new EmailMessage(application.getClient().getEmail(),
                        EmailMessage.ThemeEnum.APPLICATION_DENIED, application.getApplicationId(),
                        "Вам отказано в кредите");
                kafkaProducerService.writeMessage(emailMessage);
            }
            throw new FeignDealException(e.getMessage());
        }

        Credit credit = creditMapper.toCredit(creditDTO);
        application.setCredit(credit);
        updateStatus(application, ApplicationStatus.CC_DENIED);
        credit.setCreditStatus(CreditStatus.CALCULATED);
        creditRepository.save(credit);
        applicationRepository.save(application);
        log.info("calculateCredit - result:  ok,  {}", creditDTO);
    }

    private void updateStatus(Application application, ApplicationStatus status) {
        application.setStatus(status);
        List<StatusHistory> statusHistories = application.getStatusHistory();
        if (statusHistories == null) {
            statusHistories = new ArrayList<>();
        }
        statusHistories.add(new StatusHistory(status, LocalDateTime.now(), ChangeType.AUTOMATIC));
        application.setStatusHistory(statusHistories);
    }
}
