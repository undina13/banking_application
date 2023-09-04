package com.undina.dossier.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.openapitools.model.EmailMessage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class KafkaIntegrationTest extends AbstractKafkaTest {
    @Test
    void simpleTest() throws JsonProcessingException {
        String emailMessage = objectMapper.writeValueAsString(new EmailMessage().address("undina13@bk.ru")
                .applicationId(123L)
                .theme(EmailMessage.ThemeEnum.FINISH_REGISTRATION)
                .text("some text"));
        kafkaTemplate.send("finishRegistration", emailMessage);

        KafkaService kafkaService = new KafkaService(objectMapper, emailSender);
        kafkaService.getFinishRegistrationMessage(emailMessage);
        verify(emailSender, times(1)).sendMessage(any());
    }
}