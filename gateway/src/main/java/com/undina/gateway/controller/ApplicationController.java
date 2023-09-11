package com.undina.gateway.controller;

import com.undina.gateway.service.ApplicationService;
import com.undina.gateway.util.ModelFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.GatewayApplicationControllerApi;
import org.openapitools.model.LoanApplicationRequestDTO;
import org.openapitools.model.LoanOfferDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApplicationController implements GatewayApplicationControllerApi {
    private final ApplicationService applicationService;

    @Override
    public ResponseEntity<Void> applyLoanOffer(LoanOfferDTO loanOfferDTO) {
        log.info("applyLoanOffer - start: loanOfferDTO = {}", loanOfferDTO);
        applicationService.applyLoanOffer(loanOfferDTO);
        log.info("applyLoanOffer - end");
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> createApplication(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.info("createApplication - start: loanApplicationRequestDTO = {}",
                ModelFormatter.toLogFormat(loanApplicationRequestDTO));
        applicationService.createApplication(loanApplicationRequestDTO);
        log.info("createApplication - end");
        return ResponseEntity.ok().build();
    }
}
