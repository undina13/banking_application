package com.undina.gateway.controller;


import com.undina.gateway.AbstractTest;
import org.junit.jupiter.api.Test;
import org.mockserver.model.Header;

import static com.undina.gateway.util.ApplicationDTOHelper.applicationDTO;
import static com.undina.gateway.util.ApplicationDTOHelper.applicationDTOList;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

class AdminControllerIntegrationTest extends AbstractTest {

    @Test
    void getApplicationTestOk() throws Exception {
        mockDealServerClient
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/deal/admin/application/1"),
                        exactly(1))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8"))
                                .withBody(mapper.writeValueAsString(applicationDTO))
                );
        mockMvc.perform(get("/gateway/admin/application/1"))
                .andExpect(content().json(mapper.writeValueAsString(applicationDTO)));
    }

    @Test
    void getAllApplicationsTestOk() throws Exception {
        mockDealServerClient
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/deal/admin/application"),
                        exactly(1))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8"))
                                .withBody(mapper.writeValueAsString(applicationDTOList))
                );
        mockMvc.perform(get("/gateway/admin/application"))
                .andExpect(content().json(mapper.writeValueAsString(applicationDTOList)));
    }
}
