package com.undina.dossier.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.undina.dossier.model.EmailMessage;
import com.undina.dossier.model.Theme;
import org.junit.jupiter.api.Test;

class KafkaIntegrationTest extends AbstractKafkaTest {
    @Test
    void simpleTest() throws JsonProcessingException {
        String emailMessage = objectMapper.writeValueAsString(new EmailMessage("undina13@bk.ru",  Theme.FINISH_REGISTRATION, 132L, "some text"));

        kafkaTemplate.send("finishRegistration", emailMessage);

        KafkaConsumerService kafkaConsumerService = new KafkaConsumerService(objectMapper, emailSender);
//        kafkaConsumerService.getFinishRegistrationMessage(emailMessage);
//        verify(emailSender, times(1)).sendMessage(any());
    }
}