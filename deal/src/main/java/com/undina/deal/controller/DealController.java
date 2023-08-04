package com.undina.deal.controller;

import com.undina.deal.dto.FinishRegistrationRequestDTO;
import com.undina.deal.dto.LoanApplicationRequestDTO;
import com.undina.deal.dto.LoanOfferDTO;
import com.undina.deal.service.DealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/deal")
@RequiredArgsConstructor
public class DealController {
    private final DealService dealService;

    @PostMapping("/application")
    public ResponseEntity<List<LoanOfferDTO>> createApplication(@RequestBody LoanApplicationRequestDTO loanApplication) {
        log.info("createApplication " + loanApplication);
        List<LoanOfferDTO> loanOfferDTOList = dealService.createApplication(loanApplication);
        log.info("createApplication result " + loanOfferDTOList);
        return ResponseEntity.ok(loanOfferDTOList);
    }

    @PutMapping("/offer")
    public ResponseEntity<Void> applyLoanOffer(@RequestBody LoanOfferDTO loanOffer) {
        log.info("applyLoanOffer " + loanOffer);
        dealService.applyOffer(loanOffer);
        log.info("applyLoanOffer result OK");
        return ResponseEntity.ok().build();
    }

    @PutMapping(("/calculate/{applicationId}"))
    public ResponseEntity<Void> getCalculation(@PathVariable Long applicationId,
                                               @RequestBody FinishRegistrationRequestDTO finishRegistrationRequestDTO) {
        log.info("getCalculation  applicationId = {}, scoringData = {}", applicationId, finishRegistrationRequestDTO);
        dealService.calculateCredit(applicationId, finishRegistrationRequestDTO);
        log.info("getCalculation result OK");
        return ResponseEntity.ok().build();
    }
}
