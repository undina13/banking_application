package com.undina.application.controller;

import com.undina.application.service.ApplicationService;
import com.undina.application.util.ModelFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.ApplicationApi;
import org.openapitools.model.CreditDTO;
import org.openapitools.model.LoanApplicationRequestDTO;
import org.openapitools.model.LoanOfferDTO;
import org.openapitools.model.ScoringDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ApplicationController implements ApplicationApi {
    private final ApplicationService applicationService;

    @Override
    public ResponseEntity<List<LoanOfferDTO>> getLoanOffers(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.info("getLoanOffers  {}", ModelFormatter.toLogFormat(loanApplicationRequestDTO));
        List<LoanOfferDTO> loanOfferDTOS = applicationService.getLoanOffers(loanApplicationRequestDTO);
        log.info("getLoanOffers result: " + loanOfferDTOS);
        return ResponseEntity.ok()
                .body(loanOfferDTOS);
    }


    @Override
    public ResponseEntity<CreditDTO> getCalculation(ScoringDataDTO scoringDataDTO) {
        log.info("getCalculation  {}", ModelFormatter.toLogFormat(scoringDataDTO));
        CreditDTO creditDTO = applicationService.getCalculation(scoringDataDTO);
        log.info("getCalculation result: " + creditDTO);
        return ResponseEntity.ok()
                .body(creditDTO);
    }
}
