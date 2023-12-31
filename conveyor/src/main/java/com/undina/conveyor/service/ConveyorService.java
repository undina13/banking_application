package com.undina.conveyor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.CreditDTO;
import org.openapitools.model.LoanApplicationRequestDTO;
import org.openapitools.model.LoanOfferDTO;
import org.openapitools.model.ScoringDataDTO;
import org.springframework.stereotype.Service;
import util.ModelFormatter;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConveyorService {
    private final OfferService offerService;

    private final CreditService creditService;

    public List<LoanOfferDTO> getLoanOffers(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.info("getLoanOffers  {}", ModelFormatter.toLogFormat(loanApplicationRequestDTO));
        List<LoanOfferDTO> loanOfferDTOS = offerService.generateOffers(loanApplicationRequestDTO);
        log.info("getLoanOffers result: " + loanOfferDTOS);
        return loanOfferDTOS;
    }

    public CreditDTO getCalculation(ScoringDataDTO scoringDataDTO) {
        log.info("getCalculation  {}", ModelFormatter.toLogFormat(scoringDataDTO));
        CreditDTO creditDTO = creditService.getCalculation(scoringDataDTO);
        log.info("getCalculation result: " + creditDTO);
        return creditDTO;
    }
}
