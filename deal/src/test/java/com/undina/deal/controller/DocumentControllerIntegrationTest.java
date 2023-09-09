package com.undina.deal.controller;

import com.undina.deal.AbstractTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DocumentControllerIntegrationTest extends AbstractTest {
    @Test
    @Sql(scripts = "/insert_test_create_calculation_send.sql", executionPhase = BEFORE_TEST_METHOD)
    void sendDocumentsTestOk() throws Exception {
        mockMvc.perform(post("/deal/document/13/send")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Sql(scripts = "/insert_test_create_application.sql", executionPhase = BEFORE_TEST_METHOD)
    void signDocumentsTestOk() throws Exception {
        mockMvc.perform(post("/deal/document/12/sign")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Sql(scripts = "/insert_test_create_calculation.sql", executionPhase = BEFORE_TEST_METHOD)
    void codeDocumentsTestOk() throws Exception {
        mockMvc.perform(post("/deal/document/12/code")
                        .content(String.valueOf(12345L))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
