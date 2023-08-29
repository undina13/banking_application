package com.undina.application.controller;

import com.undina.application.AbstractTest;
import org.junit.jupiter.api.Test;
import org.mockserver.model.Header;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static com.undina.application.util.LoanApplicationRequestDTOHelper.*;
import static com.undina.application.util.LoanOfferDTOHelper.loanOfferDTO13;
import static com.undina.application.util.LoanOfferDTOHelper.loanOfferDTOS1;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.StringBody.exact;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ApplicationControllerIntegrationTest extends AbstractTest {
    @Test
    void createApplicationTestOk() throws Exception {
        mockServerClient
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/deal/application")
                                .withBody(exact(mapper.writeValueAsString(loanApplicationRequestDTO1))),
                        exactly(1))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8"))
                                .withBody(mapper.writeValueAsString(loanOfferDTOS1))
                );

        mockMvc.perform(post("/application")
                        .content(mapper.writeValueAsString(loanApplicationRequestDTO1))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(mapper.writeValueAsString(loanOfferDTOS1)));
    }

    @Test
    void createApplicationTestTooYoung() throws Exception {
        mockMvc.perform(post("/application")
                        .content(mapper.writeValueAsString(loanApplicationRequestTooYong))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createApplicationTestSmallAmount() throws Exception {
        mockMvc.perform(post("/application")
                        .content(mapper.writeValueAsString(loanApplicationRequestSmallAmount))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createApplyLoanOfferTestOk() throws Exception {
        mockServerClient
                .when(
                        request()
                                .withMethod("PUT")
                                .withPath("/deal/offer")
                                .withBody(exact(mapper.writeValueAsString(loanOfferDTO13))),
                        exactly(1))
                .respond(
                        response()
                                .withStatusCode(200)
                );
        mockMvc.perform(put("/application/offer")
                        .content(mapper.writeValueAsString(loanOfferDTO13))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createApplyLoanOfferTestException() throws Exception {
        mockServerClient
                .when(
                        request()
                                .withMethod("PUT")
                                .withPath("/deal/offer")
                                .withBody(exact(mapper.writeValueAsString(loanOfferDTO13))),
                        exactly(1))
                .respond(
                        response()
                                .withStatusCode(404)
                );
        mockMvc.perform(put("/application/offer")
                        .content(mapper.writeValueAsString(loanOfferDTO13))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}
