package com.undina.gateway.controller;


import com.undina.gateway.AbstractTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static com.undina.gateway.util.LoginRequestHelper.loginRequest;
import static com.undina.gateway.util.LoginRequestHelper.loginSignupRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class UserControllerIntegrationTest extends AbstractTest {
    @Test
    void registerUserTestOk() throws Exception {
        mockMvc.perform(post("/signup")
                        .content(mapper.writeValueAsString(loginSignupRequest))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void loginUserTestOk() throws Exception {
        mockMvc.perform(post("/login")
                        .content(mapper.writeValueAsString(loginRequest))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
