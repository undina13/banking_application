package com.undina.dossier.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.undina.dossier.emailsender.EmailSender;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@Testcontainers
public class AbstractKafkaConsumerIntegrationTest {
    @Container
    static final KafkaContainer kafka = new KafkaContainer(
            DockerImageName.parse("confluentinc/cp-kafka:7.3.3"))
            .withEnv("KAFKA_CREATE_TOPICS", "finish-registration,create-documents,send-documents,send-ses,credit-issued,application-denied");

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.consumer.bootstrap-servers", kafka::getBootstrapServers);
        registry.add("spring.producer.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }

    @InjectMocks
    protected ObjectMapper objectMapper;

    @Autowired
    protected ApplicationContext _appContext;
    @Mock
    protected EmailSender emailSender;
}