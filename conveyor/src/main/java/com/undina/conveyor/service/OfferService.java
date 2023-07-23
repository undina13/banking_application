package com.undina.conveyor.service;

import com.undina.conveyor.dto.LoanApplicationRequestDTO;
import com.undina.conveyor.dto.LoanOfferDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferService {
    private final ScoringService scoringService;

    @Value("${base_rate}")
    private BigDecimal baseRate;

    public List<LoanOfferDTO> generateOffers(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.info("generateOffers " + loanApplicationRequestDTO.toString());
        List<LoanOfferDTO> loanOfferDTOS = new ArrayList<>();

        loanOfferDTOS.add(createOffer(false, false, loanApplicationRequestDTO));
        loanOfferDTOS.add(createOffer(true, false, loanApplicationRequestDTO));
        loanOfferDTOS.add(createOffer(false, true, loanApplicationRequestDTO));
        loanOfferDTOS.add(createOffer(true, true, loanApplicationRequestDTO));
        loanOfferDTOS.sort(Comparator.comparing(LoanOfferDTO::getRate).reversed());
        return loanOfferDTOS;
    }

    private LoanOfferDTO createOffer(Boolean isInsuranceEnabled, Boolean isSalaryClient,
                                     LoanApplicationRequestDTO loanApplicationRequestDTO) {
        BigDecimal totalAmount = scoringService.evaluateTotalAmountByServices(loanApplicationRequestDTO.getAmount(),
                isInsuranceEnabled, loanApplicationRequestDTO.getTerm());
        BigDecimal rate = scoringService.calculateRate(isInsuranceEnabled, isSalaryClient, baseRate);
        return LoanOfferDTO.builder()
                .requestedAmount(loanApplicationRequestDTO.getAmount())
                .totalAmount(totalAmount)
                .term(loanApplicationRequestDTO.getTerm())
                .isInsuranceEnabled(isInsuranceEnabled)
                .isSalaryClient(isSalaryClient)
                .rate(rate)
                .monthlyPayment(scoringService.calculateMonthlyPayment(totalAmount, loanApplicationRequestDTO.getTerm(),
                        rate))
                .build();
    }
}
