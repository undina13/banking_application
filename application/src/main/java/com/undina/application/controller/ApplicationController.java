package com.undina.application.controller;

import com.undina.application.service.ApplicationService;
import com.undina.application.util.ModelFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.ApplicationApi;
import org.openapitools.model.LoanApplicationRequestDTO;
import org.openapitools.model.LoanOfferDTO;
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
        log.info("getLoanOffers - start: {}", ModelFormatter.toLogFormat(loanApplicationRequestDTO));
        List<LoanOfferDTO> loanOfferDTOS = applicationService.getLoanOffers(loanApplicationRequestDTO);
        log.info("getLoanOffers - result: {}", loanOfferDTOS);
        return ResponseEntity.ok()
                .body(loanOfferDTOS);
    }


    @Override
    public ResponseEntity<Void> getCalculation(LoanOfferDTO loanOfferDTO) {
        log.info("getCalculation - start: {}", loanOfferDTO);
        applicationService.getCalculation(loanOfferDTO);
        log.info("getCalculation - result: ok");
        return ResponseEntity.ok().build();

    }
}
