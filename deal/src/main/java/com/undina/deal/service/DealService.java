package com.undina.deal.service;

import com.undina.deal.dto.FinishRegistrationRequestDTO;
import com.undina.deal.dto.LoanApplicationRequestDTO;
import com.undina.deal.dto.LoanOfferDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DealService {
    public List<LoanOfferDTO> createApplication(LoanApplicationRequestDTO loanApplication) {
        return null;
    }

    public void applyOffer(LoanOfferDTO loanOffer) {
    }


    public void calculateCredit(Long applicationId, FinishRegistrationRequestDTO finishRegistrationRequestDTO) {
    }
}
