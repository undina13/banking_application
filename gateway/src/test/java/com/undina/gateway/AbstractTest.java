package com.undina.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockserver.client.MockServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper mapper;
    protected static MockServerClient mockDealServerClient = startClientAndServer(8081);
    protected static MockServerClient mockApplicationServerClient = startClientAndServer(8082);
}
