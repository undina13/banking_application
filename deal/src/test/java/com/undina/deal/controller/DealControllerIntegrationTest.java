package com.undina.deal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static com.undina.deal.model.CreditDTOData.creditDTO;
import static com.undina.deal.model.FinishRegistrationRequestDTOData.finishRegistrationRequest;
import static com.undina.deal.model.LoanApplicationRequestDTOData.loanApplicationRequestDTO1;
import static com.undina.deal.model.LoanOfferDTOData.loanOfferDTO13;
import static com.undina.deal.model.LoanOfferDTOData.loanOfferDTOS1;
import static com.undina.deal.model.ScoringDataDTOData.scoringDataDTO1;
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

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class DealControllerIntegrationTest extends SpringBootApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    static ClientAndServer mockServer;

    @BeforeAll
    static void start() {
        mockServer = startClientAndServer(8080);
    }


    @Test
    void createApplicationTestOk() throws Exception {
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
    void createApplyLoanOfferTestOk() throws Exception {
        mockMvc.perform(put("/deal/offer")
                        .content(mapper.writeValueAsString(loanOfferDTO13))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getCalculationTestOk() throws Exception {

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
