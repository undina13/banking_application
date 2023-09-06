//package com.undina.dossier.service;
//
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.undina.dossier.model.EmailMessage;
//import com.undina.dossier.model.Theme;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.junit.jupiter.api.Test;
//
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//class KafkaIntegrationTest extends AbstractKafkaTest {
//    @Test
//    void finishRegistrationTest() throws JsonProcessingException {
//        EmailMessage emailMessage = new EmailMessage("undina13@bk.ru",  Theme.FINISH_REGISTRATION,
//                132L, "some text");
//        kafkaTemplate.send("finishRegistration", objectMapper.writeValueAsString(emailMessage));
//        KafkaConsumerService kafkaConsumerService = new KafkaConsumerService( emailSender);
//        kafkaConsumerService.getFinishRegistrationMessage(new ConsumerRecord<>("finishRegistration",
//                1, 1L, null, emailMessage));
//        verify(emailSender, times(1)).sendMessage(emailMessage);
//    }
//
//    @Test
//    void createDocumentsMessageTest() throws JsonProcessingException {
//        EmailMessage emailMessage = new EmailMessage("undina13@bk.ru",  Theme.CREATE_DOCUMENTS,
//                132L, "some text");
//        kafkaTemplate.send("createDocuments", objectMapper.writeValueAsString(emailMessage));
//        KafkaConsumerService kafkaConsumerService = new KafkaConsumerService( emailSender);
//        kafkaConsumerService.getCreateDocumentsMessage(new ConsumerRecord<>("createDocuments",
//                1, 1L, null, emailMessage));
//        verify(emailSender, times(1)).sendMessage(emailMessage);
//    }
//
//    @Test
//    void sendDocumentsTest() throws JsonProcessingException {
//        EmailMessage emailMessage = new EmailMessage("undina13@bk.ru",  Theme.SEND_DOCUMENTS,
//                132L, "some text");
//        kafkaTemplate.send("sendDocuments", objectMapper.writeValueAsString(emailMessage));
//        KafkaConsumerService kafkaConsumerService = new KafkaConsumerService( emailSender);
//        kafkaConsumerService.getSendDocumentsMessage(new ConsumerRecord<>("sendDocuments",
//                1, 1L, null, emailMessage));
//        verify(emailSender, times(1)).sendMessage(emailMessage);
//    }
//
//    @Test
//    void sendSesTest() throws JsonProcessingException {
//        EmailMessage emailMessage = new EmailMessage("undina13@bk.ru",  Theme.SEND_SES,
//                132L, "some text");
//        kafkaTemplate.send("sendSes", objectMapper.writeValueAsString(emailMessage));
//        KafkaConsumerService kafkaConsumerService = new KafkaConsumerService( emailSender);
//        kafkaConsumerService.getSendSesMessage(new ConsumerRecord<>("sendSes",
//                1, 1L, null, emailMessage));
//        verify(emailSender, times(1)).sendMessage(emailMessage);
//    }
//
//    @Test
//    void creditIssuesTest() throws JsonProcessingException {
//        EmailMessage emailMessage = new EmailMessage("undina13@bk.ru",  Theme.CREDIT_ISSUED,
//                132L, "some text");
//        kafkaTemplate.send("creditIssues", objectMapper.writeValueAsString(emailMessage));
//        KafkaConsumerService kafkaConsumerService = new KafkaConsumerService( emailSender);
//        kafkaConsumerService.getCreditIssuesMessage(new ConsumerRecord<>("creditIssues",
//                1, 1L, null, emailMessage));
//        verify(emailSender, times(1)).sendMessage(emailMessage);
//    }
//
//    @Test
//    void applicationDeniedTest() throws JsonProcessingException {
//        EmailMessage emailMessage = new EmailMessage("undina13@bk.ru", Theme.APPLICATION_DENIED,
//                132L, "some text");
//        kafkaTemplate.send("applicationDenied", objectMapper.writeValueAsString(emailMessage));
//        KafkaConsumerService kafkaConsumerService = new KafkaConsumerService( emailSender);
//        kafkaConsumerService.getApplicationDeniedMessage(new ConsumerRecord<>("applicationDenied",
//                1, 1L, null, emailMessage));
//        verify(emailSender, times(1)).sendMessage(emailMessage);
//    }
//}