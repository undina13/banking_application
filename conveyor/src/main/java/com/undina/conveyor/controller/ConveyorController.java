package com.undina.conveyor.controller;

import com.undina.conveyor.service.ConveyorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.model.CreditDTO;
import org.openapitools.client.model.LoanApplicationRequestDTO;
import org.openapitools.client.model.LoanOfferDTO;
import org.openapitools.client.model.ScoringDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/conveyor")
@RequiredArgsConstructor
@Validated
@Tag(name = "ConveyorController", description = "Контроллер ConveyorController")
public class ConveyorController {
    private final ConveyorService conveyorService;

    @PostMapping("/offers")
    public ResponseEntity<List<LoanOfferDTO>> getLoanOffers(@RequestBody @Valid LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.info("getLoanOffers: " + loanApplicationRequestDTO);
        List<LoanOfferDTO> loanOfferDTOS = conveyorService.getLoanOffers(loanApplicationRequestDTO);
        log.info("getLoanOffers result: " + loanOfferDTOS);
        return ResponseEntity.ok()
                .body(loanOfferDTOS);
    }

    @PostMapping("/calculation")
    public ResponseEntity<CreditDTO> getCalculation(@RequestBody @Valid ScoringDataDTO scoringDataDTO) {
        log.info("getCalculation: " + scoringDataDTO);
        CreditDTO creditDTO = conveyorService.getCalculation(scoringDataDTO);
        log.info("getCalculation result: " + creditDTO);
        return ResponseEntity.ok()
                .body(creditDTO);
    }
}
