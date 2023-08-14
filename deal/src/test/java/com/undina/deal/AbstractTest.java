package com.undina.deal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.mockserver.client.MockServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.TestSocketUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(initializers = AbstractTest.Initializer.class)
@Testcontainers
@RunWith(SpringRunner.class)
public abstract class AbstractTest {
    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper mapper;

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:14")
            .withDatabaseName("test-db")
            .withPassword("password")
            .withUsername("user");

    static {
        postgreSQLContainer.start();
    }

    protected static MockServerClient mockServerClient = startClientAndServer(TestSocketUtils.findAvailableTcpPort());

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues values = TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.liquibase.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.liquibase.user=" + postgreSQLContainer.getUsername(),
                    "spring.liquibase.password=" + postgreSQLContainer.getPassword(),
                    "feign.conveyor.url=http://localhost:" + mockServerClient.getPort() + "/conveyor"
            );
            values.applyTo(configurableApplicationContext);
        }
    }
}
