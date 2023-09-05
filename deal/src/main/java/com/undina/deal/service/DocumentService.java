package com.undina.deal.service;

import com.undina.deal.entity.Application;
import com.undina.deal.enums.ApplicationStatus;
import com.undina.deal.exception.NotFoundException;
import com.undina.deal.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.EmailMessage;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentService {
    private final ApplicationRepository applicationRepository;

    private final KafkaProducerService kafkaProducerService;

    public void sendDocuments(Long applicationId) {
        log.info("sendDocuments - start: applicationId = {}", applicationId);
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Application with id = {} not found" + applicationId));
        EmailMessage emailMessage = new EmailMessage(application.getClient().getEmail(),
                EmailMessage.ThemeEnum.SEND_DOCUMENTS, application.getApplicationId(),
                application.getAppliedOffer().toString());
        kafkaProducerService.writeMessage(emailMessage);
        log.info("sendDocuments - result: OK");
    }

    public void signDocuments(Long applicationId) {
        log.info("signDocuments - start: applicationId = {}", applicationId);
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Application with id = {} not found" + applicationId));
        EmailMessage emailMessage = new EmailMessage(application.getClient().getEmail(), EmailMessage.ThemeEnum.SEND_SES,
                application.getApplicationId(), application.getAppliedOffer().toString());
        kafkaProducerService.writeMessage(emailMessage);
        log.info("signDocuments - result: OK");
    }

    public void codeDocuments(Long applicationId, Integer sesCode) {
        log.info("codeDocuments - start: applicationId = {}", applicationId);
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Application with id = {} not found" + applicationId));
        application.setSesCode(sesCode.toString());
        application.setStatus(ApplicationStatus.DOCUMENT_SIGNED);
        applicationRepository.save(application);
        EmailMessage emailMessage = new EmailMessage(application.getClient().getEmail(),
                EmailMessage.ThemeEnum.CREDIT_ISSUED, application.getApplicationId(), "Кредит выдан");
        kafkaProducerService.writeMessage(emailMessage);
        log.info("codeDocuments - result: OK");
    }

}
