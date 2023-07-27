package com.undina.conveyor.service;

import com.undina.conveyor.model.CreditDTO;
import com.undina.conveyor.model.LoanApplicationRequestDTO;
import com.undina.conveyor.model.LoanOfferDTO;
import com.undina.conveyor.model.ScoringDataDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConveyorService {
    private final OfferService offerService;

    private final CreditService creditService;

    public List<LoanOfferDTO> getLoanOffers(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.info("getLoanOffers " + loanApplicationRequestDTO);
        List<LoanOfferDTO> loanOfferDTOS = offerService.generateOffers(loanApplicationRequestDTO);
        log.info("getLoanOffers result: " + loanOfferDTOS);
        return loanOfferDTOS;
    }

    public CreditDTO getCalculation(ScoringDataDTO scoringDataDTO) {
        log.info("getCalculation " + scoringDataDTO);
        CreditDTO creditDTO = creditService.getCalculation(scoringDataDTO);
        log.info("getCalculation result: " + creditDTO);
        return creditDTO;
    }
}
