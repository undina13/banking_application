package com.undina.conveyor.service;

import com.undina.conveyor.dto.LoanApplicationRequestDTO;
import com.undina.conveyor.dto.LoanOfferDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Slf4j
@Validated
public class ConveyorService {
    public List<LoanOfferDTO> getLoanOffers(@Valid LoanApplicationRequestDTO loanApplicationRequestDTO) {
        return  null;
    }
}
