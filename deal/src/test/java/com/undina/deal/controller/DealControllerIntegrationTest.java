package com.undina.deal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

import java.nio.charset.StandardCharsets;

import static com.undina.deal.dto.CreditDTOData.creditDTO;
import static com.undina.deal.dto.FinishRegistrationRequestDTOData.finishRegistrationRequest;
import static com.undina.deal.dto.LoanApplicationRequestDTOData.loanApplicationRequestDTO1;
import static com.undina.deal.dto.LoanOfferDTOData.loanOfferDTO13;
import static com.undina.deal.dto.LoanOfferDTOData.loanOfferDTOS1;
import static com.undina.deal.dto.ScoringDataDTOData.scoringDataDTO1;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.StringBody.exact;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = DealControllerIntegrationTest.Initializer.class)
@AutoConfigureMockMvc(addFilters = false)
public class DealControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    static ClientAndServer mockServer = startClientAndServer(8080);

    @ClassRule
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13.3")
            .withPassword("password")
            .withUsername("user");

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues values = TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.liquibase.enabled=true",
                    "spring.liquibase.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.liquibase.change-log=db/changelog/db.changelog-master-test.xml",
                    "spring.liquibase.user=user",
                    "spring.liquibase.password=password",
                    "feign.conveyor.url=http://localhost:8080/conveyor"

            );
            values.applyTo(configurableApplicationContext);
        }
    }

    @Test
    public void createApplicationTestOk() throws Exception {
        mockServer
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/conveyor/offers")
                                .withBody(exact(mapper.writeValueAsString(loanApplicationRequestDTO1))),
                        exactly(1))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8"))
                                .withBody(mapper.writeValueAsString(loanOfferDTOS1))
                );

        mockMvc.perform(post("/deal/application")
                        .content(mapper.writeValueAsString(loanApplicationRequestDTO1))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(mapper.writeValueAsString(loanOfferDTOS1)));

    }

    @Test
    public void createApplyLoanOfferTestOk() throws Exception {
        mockMvc.perform(put("/deal/offer")
                        .content(mapper.writeValueAsString(loanOfferDTO13))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getCalculationTestOk() throws Exception {
        mockServer
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/conveyor/calculation")
                                .withBody(mapper.writeValueAsString(scoringDataDTO1)),
                        exactly(1))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8"))
                                .withBody(mapper.writeValueAsString(creditDTO))
                );

        mockMvc.perform(put("/deal/calculate/12")
                        .content(mapper.writeValueAsString(finishRegistrationRequest))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
