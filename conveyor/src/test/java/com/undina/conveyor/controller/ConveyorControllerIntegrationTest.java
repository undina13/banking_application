package com.undina.conveyor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.undina.conveyor.model.CreditDTOData.creditDTO;
import static com.undina.conveyor.model.LoanApplicationRequestDTOData.loanApplicationRequestDTO1;
import static com.undina.conveyor.model.LoanApplicationRequestDTOData.loanApplicationRequestDTOTooYoung;
import static com.undina.conveyor.model.LoanOfferDTOData.*;
import static com.undina.conveyor.model.ScoringDataDTOData.scoringDataDTO1;
import static com.undina.conveyor.model.ScoringDataDTOData.scoringDataDTOUnemployed;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ConveyorControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void getLoanOffersTestOk() throws Exception {
        mockMvc.perform(post("/conveyor/offers")
                        .content(mapper.writeValueAsString(loanApplicationRequestDTO1))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(mapper.writeValueAsString(List.of(loanOfferDTO1, loanOfferDTO2, loanOfferDTO3,
                        loanOfferDTO4))));
    }

    @Test
    void getLoanOffersTestBadRequest() throws Exception {
        mockMvc.perform(post("/conveyor/offers")
                        .content(mapper.writeValueAsString(loanApplicationRequestDTOTooYoung))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCalculationTestOk() throws Exception {
        mockMvc.perform(post("/conveyor/calculation")
                        .content(mapper.writeValueAsString(scoringDataDTO1))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(mapper.writeValueAsString(creditDTO)));
    }

    @Test
    void getCalculationTestRejection() throws Exception {
        mockMvc.perform(post("/conveyor/calculation")
                        .content(mapper.writeValueAsString(scoringDataDTOUnemployed))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}
