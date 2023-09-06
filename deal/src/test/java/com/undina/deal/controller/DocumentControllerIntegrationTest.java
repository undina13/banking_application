package com.undina.deal.controller;

import com.undina.deal.AbstractKafkaTest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.openapitools.model.EmailMessage;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.jdbc.Sql;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DocumentControllerIntegrationTest extends AbstractKafkaTest {
    @Test
    @Sql(scripts = "/insert_test_create_calculation.sql", executionPhase = BEFORE_TEST_METHOD)

    public void sendDocumentsTestOk() throws Exception {
        mockMvc.perform(post("/deal/document/12/send")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(kafkaServer, "dossier", "true");
//        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        Consumer<String, String>  consumer1 = new DefaultKafkaConsumerFactory<String, String>(consumerProps)
//                .createConsumer();
//        consumer1.subscribe(Collections.singletonList("send-documents"));
        ConsumerRecord<String, String> singleRecord = KafkaTestUtils.getSingleRecord(consumer, "send-documents");
        assertThat(singleRecord).isNotNull();
        EmailMessage emailMessage = mapper.readValue(singleRecord.value(), EmailMessage.class);
        assertEquals("sidorov@mail.ru", emailMessage.getAddress());
        assertEquals(emailMessage.getTheme().toString(), "SEND_DOCUMENTS");
       // consumer1.unsubscribe();
    }

//    @Test
//    @Sql(scripts = "/insert_test_create_application.sql", executionPhase = BEFORE_TEST_METHOD)
//
//    public void signDocumentsTestOk() throws Exception {
//        mockMvc.perform(post("/deal/document/12/sign")
//                        .characterEncoding(StandardCharsets.UTF_8)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
////        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(kafkaServer, "dossier", "true");
////        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
////        Consumer<String, String>  consumer = new DefaultKafkaConsumerFactory<String, String>(consumerProps)
////                .createConsumer();
////        consumer.subscribe(Collections.singletonList("send-ses"));
//        ConsumerRecord<String, String> singleRecord = KafkaTestUtils.getSingleRecord(consumer, "send-ses");
//        assertThat(singleRecord).isNotNull();
//        EmailMessage emailMessage = mapper.readValue(singleRecord.value(), EmailMessage.class);
//        assertEquals("sidorov@mail.ru", emailMessage.getAddress());
//        assertEquals(emailMessage.getTheme().toString(), "SEND_SES");
//      //  consumer.unsubscribe();
//    }
//
//    @Test
//    @Sql(scripts = "/insert_test_create_calculation.sql", executionPhase = BEFORE_TEST_METHOD)
//
//    public void codeDocumentsTestOk() throws Exception {
//        mockMvc.perform(post("/deal/document/12/code")
//                        .content(String.valueOf(12345L))
//                        .characterEncoding(StandardCharsets.UTF_8)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
////        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(kafkaServer, "dossier", "true");
////        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
////        Consumer<String, String>  consumer = new DefaultKafkaConsumerFactory<String, String>(consumerProps)
////                .createConsumer();
////        consumer.subscribe(Collections.singletonList("credit-issued"));
//        ConsumerRecord<String, String> singleRecord = KafkaTestUtils.getSingleRecord(consumer, "credit-issued");
//        assertThat(singleRecord).isNotNull();
//        EmailMessage emailMessage = mapper.readValue(singleRecord.value(), EmailMessage.class);
//        assertEquals("sidorov@mail.ru", emailMessage.getAddress());
//        assertEquals(emailMessage.getTheme().toString(), "CREDIT_ISSUED");
//      //  consumer.unsubscribe();
//    }
}
