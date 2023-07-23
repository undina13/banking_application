package com.undina.conveyor.service;

import com.undina.conveyor.dto.CreditDTO;
import com.undina.conveyor.dto.LoanApplicationRequestDTO;
import com.undina.conveyor.dto.LoanOfferDTO;
import com.undina.conveyor.dto.ScoringDataDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Slf4j
@Validated
@RequiredArgsConstructor
public class ConveyorService {

    private final OfferService offerService;
    private final CreditService creditService;

    public List<LoanOfferDTO> getLoanOffers(@Valid LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.info("getLoanOffers " + loanApplicationRequestDTO.toString());
        return offerService.generateOffers(loanApplicationRequestDTO);
    }

    public CreditDTO getCalculation(@Valid ScoringDataDTO scoringDataDTO) {
        log.info("getCalculation " + scoringDataDTO.toString());
        return creditService.getCalculation(scoringDataDTO);
    }
}
