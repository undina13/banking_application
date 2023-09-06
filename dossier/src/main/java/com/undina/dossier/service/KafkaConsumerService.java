package com.undina.dossier.service;

import com.undina.dossier.emailsender.EmailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.openapitools.model.EmailMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final EmailSender emailSender;

    @KafkaListener(topics = "${topic.finish-registration}", groupId = "dossier")
    public void getFinishRegistrationMessage(ConsumerRecord<String, EmailMessage> record) {
        log.info("getFinishRegistrationMessage - start: {}", record.value());
        sendMessage(record.value());
        log.info("getFinishRegistrationMessage - end");
    }

    @KafkaListener(topics = "${topic.create-documents}", groupId = "dossier")
    public void getCreateDocumentsMessage(ConsumerRecord<String, EmailMessage> record) {
        log.info("getCreateDocumentsMessage - start: {}", record.value());
        sendMessage(record.value());
        log.info("getCreateDocumentsMessage - end");
    }

    @KafkaListener(topics = "${topic.send-documents}", groupId = "dossier")
    public void getSendDocumentsMessage(ConsumerRecord<String, EmailMessage> record) {
        log.info("getSendDocumentsMessage - start: {}", record.value());
        sendMessage(record.value());
        log.info("getSendDocumentsMessage - end");
    }

    @KafkaListener(topics = "${topic.send-ses}", groupId = "dossier")
    public void getSendSesMessage(ConsumerRecord<String, EmailMessage> record) {
        log.info("getSendSesMessage - start: {}", record.value());
        sendMessage(record.value());
        log.info("getSendSesMessage - end");
    }

    @KafkaListener(topics = "${topic.credit-issued}", groupId = "dossier")
    public void getCreditIssuesMessage(ConsumerRecord<String, EmailMessage> record) {
        log.info("getCreditIssuesMessage - start: {}", record.value());
        sendMessage(record.value());
        log.info("getCreditIssuesMessage - end");
    }

    @KafkaListener(topics = "${topic.application-denied}", groupId = "dossier")
    public void getApplicationDeniedMessage(ConsumerRecord<String, EmailMessage> record) {
        log.info("getApplicationDeniedMessage - start: {}", record.value());
        sendMessage(record.value());
        log.info("getApplicationDeniedMessage - end");
    }

    private void sendMessage(EmailMessage message){
            log.info("MESSAGE - start: {}", message);
            emailSender.sendMessage(message);
    }
}
