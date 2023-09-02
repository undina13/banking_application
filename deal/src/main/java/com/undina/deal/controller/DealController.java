package com.undina.deal.controller;

import com.undina.deal.service.DealService;
import com.undina.deal.util.ModelFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.DealApi;
import org.openapitools.model.FinishRegistrationRequestDTO;
import org.openapitools.model.LoanApplicationRequestDTO;
import org.openapitools.model.LoanOfferDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DealController implements DealApi {
    private final DealService dealService;

    @Override
    public ResponseEntity<List<LoanOfferDTO>> createApplication(@RequestBody LoanApplicationRequestDTO loanApplication) {
        log.info("createApplication - start: {}", ModelFormatter.toLogFormat(loanApplication));
        List<LoanOfferDTO> loanOfferDTOList = dealService.createApplication(loanApplication);
        log.info("createApplication - result: {}", loanOfferDTOList);
        return ResponseEntity.ok(loanOfferDTOList);
    }

    @Override
    public ResponseEntity<Void> applyLoanOffer(@RequestBody LoanOfferDTO loanOffer) {
        log.info("applyLoanOffer  - start: {}", loanOffer);
        dealService.applyOffer(loanOffer);
        log.info("applyLoanOffer - result: OK");
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> getCalculation(@PathVariable Long applicationId,
                                               @RequestBody FinishRegistrationRequestDTO finishRegistrationRequestDTO) {
        log.info("getCalculation - start: applicationId = {}, finishRegistrationRequestDTO = {}", applicationId,
                ModelFormatter.toLogFormat(finishRegistrationRequestDTO));
        dealService.calculateCredit(applicationId, finishRegistrationRequestDTO);
        log.info("getCalculation - result: OK");
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> sendDocuments(Long applicationId) {
        log.info("sendDocuments - start: applicationId = {}", applicationId);
        dealService.sendDocuments(applicationId);
        log.info("sendDocuments - result: OK");
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> signDocuments(Long applicationId) {
        log.info("signDocuments - start: applicationId = {}", applicationId);
        dealService.signDocuments(applicationId);
        log.info("signDocuments - result: OK");
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> codeDocuments(Long applicationId) {
        log.info("codeDocuments - start: applicationId = {}", applicationId);
        dealService.codeDocuments(applicationId);
        log.info("codeDocuments - result: OK");
        return ResponseEntity.ok().build();
    }
}
