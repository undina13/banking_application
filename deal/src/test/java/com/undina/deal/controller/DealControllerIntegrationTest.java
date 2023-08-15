package com.undina.deal.controller;

import com.undina.deal.AbstractTest;
import org.junit.jupiter.api.Test;
import org.mockserver.model.Header;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.nio.charset.StandardCharsets;

import static com.undina.deal.util.CreditDTOHelper.creditDTO;
import static com.undina.deal.util.FinishRegistrationRequestDTOHelper.finishRegistrationRequest;
import static com.undina.deal.util.LoanApplicationRequestDTOHelper.loanApplicationRequestDTO1;
import static com.undina.deal.util.LoanOfferDTOHelper.loanOfferDTO13;
import static com.undina.deal.util.LoanOfferDTOHelper.loanOfferDTOS1;
import static com.undina.deal.util.ScoringDataDTOHelper.scoringDataDTO1;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.StringBody.exact;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DealControllerIntegrationTest extends AbstractTest {

    @Test
    @Sql(scripts = "/insert_test_create_application.sql", executionPhase = BEFORE_TEST_METHOD)
    public void createApplicationTestOk() throws Exception {
        mockServerClient
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
    @Sql(scripts = "/insert_test_create_calculation.sql", executionPhase = BEFORE_TEST_METHOD)
    public void getCalculationTestOk() throws Exception {
        mockServerClient
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
