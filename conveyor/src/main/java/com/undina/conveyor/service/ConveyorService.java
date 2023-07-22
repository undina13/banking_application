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

    private  final OfferService offerService;
    public List<LoanOfferDTO> getLoanOffers(@Valid LoanApplicationRequestDTO loanApplicationRequestDTO) {
        return  null;
    }

    public CreditDTO getCalculation(@Valid ScoringDataDTO scoringDataDTO) {
        return  null;
    }
}
