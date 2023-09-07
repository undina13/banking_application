package com.undina.dossier.service;


import com.undina.dossier.model.EmailMessage;
import com.undina.dossier.model.Theme;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class KafkaServiceConsumerTest extends AbstractKafkaConsumerTest {
    @Test
    void finishRegistrationTest() {
        EmailMessage emailMessage = new EmailMessage("undina13@bk.ru", Theme.FINISH_REGISTRATION,
                132L, "some text");
        KafkaConsumerService kafkaConsumerService = new KafkaConsumerService(emailSender);
        kafkaConsumerService.getFinishRegistrationMessage(new ConsumerRecord<>("finish-registration",
                1, 1L, null, emailMessage));
        verify(emailSender, times(1)).sendMessage(emailMessage);
    }

    @Test
    void createDocumentsMessageTest() {
        EmailMessage emailMessage = new EmailMessage("undina13@bk.ru", Theme.CREATE_DOCUMENTS,
                132L, "some text");

        KafkaConsumerService kafkaConsumerService = new KafkaConsumerService(emailSender);
        kafkaConsumerService.getCreateDocumentsMessage(new ConsumerRecord<>("create-documents",
                1, 1L, null, emailMessage));
        verify(emailSender, times(1)).sendMessage(emailMessage);
    }

    @Test
    void sendDocumentsTest() {
        EmailMessage emailMessage = new EmailMessage("undina13@bk.ru", Theme.SEND_DOCUMENTS,
                132L, "some text");
        KafkaConsumerService kafkaConsumerService = new KafkaConsumerService(emailSender);
        kafkaConsumerService.getSendDocumentsMessage(new ConsumerRecord<>("sendDocuments",
                1, 1L, null, emailMessage));
        verify(emailSender, times(1)).sendMessage(emailMessage);
    }

    @Test
    void sendSesTest() {
        EmailMessage emailMessage = new EmailMessage("undina13@bk.ru", Theme.SEND_SES,
                132L, "some text");
        KafkaConsumerService kafkaConsumerService = new KafkaConsumerService(emailSender);
        kafkaConsumerService.getSendSesMessage(new ConsumerRecord<>("send-ses",
                1, 1L, null, emailMessage));
        verify(emailSender, times(1)).sendMessage(emailMessage);
    }

    @Test
    void creditIssuesTest() {
        EmailMessage emailMessage = new EmailMessage("undina13@bk.ru", Theme.CREDIT_ISSUED,
                132L, "some text");
        KafkaConsumerService kafkaConsumerService = new KafkaConsumerService(emailSender);
        kafkaConsumerService.getCreditIssuesMessage(new ConsumerRecord<>("creditIssues",
                1, 1L, null, emailMessage));
        verify(emailSender, times(1)).sendMessage(emailMessage);
    }

    @Test
    void applicationDeniedTest() {
        EmailMessage emailMessage = new EmailMessage("undina13@bk.ru", Theme.APPLICATION_DENIED,
                132L, "some text");
        KafkaConsumerService kafkaConsumerService = new KafkaConsumerService(emailSender);
        kafkaConsumerService.getApplicationDeniedMessage(new ConsumerRecord<>("applicationDenied",
                1, 1L, null, emailMessage));
        verify(emailSender, times(1)).sendMessage(emailMessage);
    }
}