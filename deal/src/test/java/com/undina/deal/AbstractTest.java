package com.undina.deal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockserver.client.MockServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.util.TestSocketUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@Testcontainers
@DirtiesContext
public abstract class AbstractTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper mapper;

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:14")
            .withDatabaseName("test-db")
            .withUsername("test-user")
            .withPassword("test-password");

    @Container
    static final KafkaContainer kafka = new KafkaContainer(
            DockerImageName.parse("confluentinc/cp-kafka:7.3.3"))
            .withEnv("KAFKA_CREATE_TOPICS", "finish-registration,create-documents,send-documents,send-ses,credit-issued,application-denied");


    protected static MockServerClient mockServerClient = startClientAndServer(TestSocketUtils.findAvailableTcpPort());

    @DynamicPropertySource
    static void registryProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);

        registry.add("spring.liquibase.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.liquibase.user", postgreSQLContainer::getUsername);
        registry.add("spring.liquibase.password", postgreSQLContainer::getPassword);

        registry.add("feign.conveyor.url", () -> "localhost:" + mockServerClient.getPort() + "/conveyor");

        registry.add("spring.kafka.producer.bootstrap-servers", kafka::getBootstrapServers);
    }
}
