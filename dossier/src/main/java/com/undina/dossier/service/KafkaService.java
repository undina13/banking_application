package com.undina.dossier.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.undina.dossier.emailsender.EmailSender;
import com.undina.dossier.exception.JsonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.EmailMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaService {
    private final ObjectMapper objectMapper;

    private final EmailSender emailSender;


    @KafkaListener(topics = "${topic.finish-registration}", groupId = "dossier")
    public void getFinishRegistrationMessage(String message) {
        log.info("getFinishRegistrationMessage - start: {}", message);
        sendMessage(message);
        log.info("getFinishRegistrationMessage - end");
    }

    @KafkaListener(topics = "${topic.create-documents}", groupId = "dossier")
    public void getCreateDocumentsMessage(String message) {
        log.info("getCreateDocumentsMessage - start: {}", message);
        sendMessage(message);
        log.info("getCreateDocumentsMessage - end");
    }

    @KafkaListener(topics = "${topic.send-documents}", groupId = "dossier")
    public void getSendDocumentsMessage(String message) {
        log.info("getSendDocumentsMessage - start: {}", message);
        sendMessage(message);
        log.info("getSendDocumentsMessage - end");
    }

    @KafkaListener(topics = "${topic.send-ses}", groupId = "dossier")
    public void getSendSesMessage(String message) {
        log.info("getSendSesMessage - start: {}", message);
        sendMessage(message);
        log.info("getSendSesMessage - end");
    }

    @KafkaListener(topics = "${topic.credit-issued}", groupId = "dossier")
    public void getCreditIssuesMessage(String message) {
        log.info("getCreditIssuesMessage - start: {}", message);
        sendMessage(message);
        log.info("getCreditIssuesMessage - end");
    }

    @KafkaListener(topics = "${topic.application-denied}", groupId = "dossier")
    public void getApplicationDeniedMessage(String message) {
        log.info("getApplicationDeniedMessage - start: {}", message);
        sendMessage(message);
        log.info("getApplicationDeniedMessage - end");
    }

    private void sendMessage(String message){
        try {
            log.info("MESSAGE - start: {}", message);
            EmailMessage  emailMessage = objectMapper.readValue(message, EmailMessage.class);
            emailSender.sendMessage(emailMessage);
        } catch (JsonProcessingException e) {
            throw new JsonException(e.getMessage());
        }
    }
}
