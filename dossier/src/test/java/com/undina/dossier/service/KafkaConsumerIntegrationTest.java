package com.undina.dossier.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.undina.dossier.model.EmailMessage;
import com.undina.dossier.model.Theme;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class KafkaConsumerIntegrationTest extends AbstractKafkaConsumerIntegrationTest {
    @Test
    void creditIssuesTest() throws ExecutionException, InterruptedException, JsonProcessingException {
        EmailMessage emailMessage = new EmailMessage("undina13@bk.ru", Theme.CREDIT_ISSUED,
                132L, "some text");
        AdminClient adminClient = AdminClient.create(
                ImmutableMap.of(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers())
        );
        KafkaProducer<String, String> producer = new KafkaProducer<>(
                ImmutableMap.of(
                        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                        kafka.getBootstrapServers(),
                        ProducerConfig.CLIENT_ID_CONFIG,
                        UUID.randomUUID().toString()
                ),
                new StringSerializer(),
                new StringSerializer()
        );
        ReflectionTestUtils.setField(emailSender, "username", "emailSender");
        ReflectionTestUtils.setField(_appContext.getBean("kafkaConsumerService"), "emailSender",
                emailSender);
        producer.send(new ProducerRecord<>("credit-issued", "dossier",
                objectMapper.writeValueAsString(emailMessage))).get();
        Thread.sleep(3000);
        verify(emailSender, times(1)).sendMessage(emailMessage);
    }
}