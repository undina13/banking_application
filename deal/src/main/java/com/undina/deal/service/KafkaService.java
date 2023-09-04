package com.undina.deal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.undina.deal.exception.JsonException;
import com.undina.deal.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.EmailMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    @Value("${topic.finish-registration}")
    private String finishRegistration;

    @Value("${topic.create-documents}")
    private String createDocuments;

    @Value("${topic.send-documents}")
    private String sendDocuments;

    @Value("${topic.send-ses}")
    private String sendSes;

    @Value("${topic.credit-issued}")
    private String creditIssued;

    @Value("${topic.application-denied}")
    private String applicationDenied;

    public void writeMessage(EmailMessage emailMessage) {
        log.info("writeMessage - start: {}", emailMessage);
        String topic = getTopic(emailMessage);
        String message = "";
        try {
         message =  objectMapper.writeValueAsString(emailMessage);
        } catch (JsonProcessingException e) {
            throw new JsonException("wrong emailMessage to json");
        }
        kafkaTemplate.send(topic, message);
        log.info("writeMessage - end");
    }

    private String getTopic(EmailMessage emailMessage) {
        switch (emailMessage.getTheme()) {
            case FINISH_REGISTRATION: {
                return finishRegistration;
            }
            case CREATE_DOCUMENTS: {
                return createDocuments;
            }
            case SEND_DOCUMENTS: {
                return sendDocuments;
            }
            case SEND_SES: {
                return sendSes;
            }
            case CREDIT_ISSUED: {
                return creditIssued;
            }
            case APPLICATION_DENIED: {
                return applicationDenied;
            }
        }
        throw new NotFoundException("wrong topic name");
    }
}
