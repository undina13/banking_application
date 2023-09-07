package com.undina.deal.controller;

import com.undina.deal.AbstractKafkaTest;
import org.apache.kafka.clients.consumer.Consumer;
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
        Consumer<String, String> consumer = configureConsumer();
        ConsumerRecord<String, String> singleRecord = KafkaTestUtils.getSingleRecord(consumer, "send-documents");
        assertThat(singleRecord).isNotNull();
        EmailMessage emailMessage = mapper.readValue(singleRecord.value(), EmailMessage.class);
        assertEquals("sidorov@mail.ru", emailMessage.getAddress());
        assertEquals("SEND_DOCUMENTS", emailMessage.getTheme().toString());
    }
}
