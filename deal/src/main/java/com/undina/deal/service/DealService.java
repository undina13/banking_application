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

    private final KafkaService kafkaService;

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
        List<LoanOfferDTO> loanOffers;
        loanOffers = conveyorFeignClient.getOffers(loanApplication).getBody();
        if (loanOffers != null) {
            Application finalApplication = application;
            loanOffers.forEach(loanOfferDTO -> loanOfferDTO.setApplicationId(finalApplication.getApplicationId()));
        }
        EmailMessage emailMessage = new EmailMessage()
                .address(application.getClient().getEmail())
                .theme(EmailMessage.ThemeEnum.FINISH_REGISTRATION)
                .applicationId(application.getApplicationId())
                .text(loanOffers.toString());
        log.info("createApplication send emailMessage {}", emailMessage);
        kafkaService.writeMessage(emailMessage);
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
        EmailMessage emailMessage = new EmailMessage()
                .address(application.getClient().getEmail())
                .theme(EmailMessage.ThemeEnum.CREATE_DOCUMENTS)
                .applicationId(application.getApplicationId())
                .text("apply offer");
        kafkaService.writeMessage(emailMessage);
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
        log.info("calculateCredit: scoringDataDTO  {}", ModelFormatter.toLogFormat(scoringDataDTO));
        CreditDTO creditDTO = conveyorFeignClient.calculateCredit(scoringDataDTO).getBody();
        log.info("calculateCredit - result:  ok,  {}", creditDTO);
    }

    public void sendDocuments(Long applicationId) {
        log.info("sendDocuments - start: applicationId = {}", applicationId);
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Application with id = {} not found" + applicationId));
        EmailMessage emailMessage = new EmailMessage()
                .address(application.getClient().getEmail())
                .theme(EmailMessage.ThemeEnum.SEND_DOCUMENTS)
                .applicationId(application.getApplicationId())
                .text(application.getAppliedOffer().toString());
        kafkaService.writeMessage(emailMessage);
        log.info("sendDocuments - result: OK");
    }

    public void signDocuments(Long applicationId) {
        log.info("signDocuments - start: applicationId = {}", applicationId);
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Application with id = {} not found" + applicationId));
        EmailMessage emailMessage = new EmailMessage()
                .address(application.getClient().getEmail())
                .theme(EmailMessage.ThemeEnum.SEND_SES)
                .applicationId(application.getApplicationId())
                .text(application.getAppliedOffer().toString());
        kafkaService.writeMessage(emailMessage);
        log.info("signDocuments - result: OK");
    }

    public void codeDocuments(Long applicationId) {
        log.info("codeDocuments - start: applicationId = {}", applicationId);
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Application with id = {} not found" + applicationId));
        application.setSesCode("ses");
        application.setStatus(ApplicationStatus.DOCUMENT_SIGNED);
        applicationRepository.save(application);
        EmailMessage emailMessage = new EmailMessage()
                .address(application.getClient().getEmail())
                .theme(EmailMessage.ThemeEnum.CREDIT_ISSUED)
                .applicationId(application.getApplicationId())
                .text("Кредит выдан");
        kafkaService.writeMessage(emailMessage);
        log.info("codeDocuments - result: OK");
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
