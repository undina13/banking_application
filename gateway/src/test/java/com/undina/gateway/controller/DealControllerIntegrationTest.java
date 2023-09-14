package com.undina.gateway.controller;


import com.undina.gateway.AbstractTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;

import java.nio.charset.StandardCharsets;

import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.StringBody.exact;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WithUserDetails(value = "user@mail.ru")
class DealControllerIntegrationTest extends AbstractTest {
    @Test
    void sendDocumentsTestOk() throws Exception {
        mockDealServerClient
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/deal/document/1/send"),
                        exactly(1))
                .respond(
                        response()
                                .withStatusCode(200)
                );
        mockMvc.perform(post("/gateway/deal/document/1/send"))
                .andExpect(status().isOk());
    }

    @Test
    void signDocumentsTestOk() throws Exception {
        mockDealServerClient
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/deal/document/1/sign"),
                        exactly(1))
                .respond(
                        response()
                                .withStatusCode(200)
                );
        mockMvc.perform(post("/gateway/deal/document/1/sign"))
                .andExpect(status().isOk());
    }

    @Test
    void codeDocumentsTestOk() throws Exception {
        mockDealServerClient
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/deal/document/1/code")
                                .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON)
                                .withBody(exact(mapper.writeValueAsString(45))),
                        exactly(1))
                .respond(
                        response()
                                .withStatusCode(200)
                );
        mockMvc.perform(post("/gateway/deal/document/1/code")
                        .content(mapper.writeValueAsString(45))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
