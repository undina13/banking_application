package com.undina.gateway.controller;


import com.undina.gateway.AbstractTest;
import org.junit.jupiter.api.Test;
import org.mockserver.model.Header;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;

import java.nio.charset.StandardCharsets;

import static com.undina.gateway.util.LoanApplicationRequestDTOHelper.loanApplicationRequestDTO1;
import static com.undina.gateway.util.LoanOfferDTOHelper.loanOfferDTO13;
import static com.undina.gateway.util.LoanOfferDTOHelper.loanOfferDTOS1;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.StringBody.exact;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WithUserDetails(value = "user@mail.ru")
class ApplicationControllerIntegrationTest extends AbstractTest {

    @Test
    void createApplicationTestOk() throws Exception {
        mockApplicationServerClient
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/application")
                                .withBody(exact(mapper.writeValueAsString(loanApplicationRequestDTO1))),
                        exactly(1))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8"))
                                .withBody(mapper.writeValueAsString(loanOfferDTOS1))
                );

        mockMvc.perform(post("/gateway/application")
                        .content(mapper.writeValueAsString(loanApplicationRequestDTO1))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void createApplyLoanOfferTestOk() throws Exception {
        mockApplicationServerClient
                .when(
                        request()
                                .withMethod("PUT")
                                .withPath("/application/offer")
                                .withBody(exact(mapper.writeValueAsString(loanOfferDTO13))),
                        exactly(1))
                .respond(
                        response()
                                .withStatusCode(200)
                );
        mockMvc.perform(put("/gateway/application/offer")
                        .content(mapper.writeValueAsString(loanOfferDTO13))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
