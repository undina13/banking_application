package com.undina.conveyor.service;

import com.undina.conveyor.dto.LoanApplicationRequestDTO;
import com.undina.conveyor.dto.LoanOfferDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final ScoringService scoringService;

    public List<LoanOfferDTO> generateOffers(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        return List.of(
                createOffer(false, false, loanApplicationRequestDTO),
                createOffer(true, false, loanApplicationRequestDTO),
                createOffer(false, true, loanApplicationRequestDTO),
                createOffer(true, true, loanApplicationRequestDTO));
    }

    private LoanOfferDTO createOffer(Boolean isInsuranceEnabled, Boolean isSalaryClient,
                                     LoanApplicationRequestDTO loanApplicationRequestDTO) {
        BigDecimal totalAmount = scoringService.evaluateTotalAmountByServices(loanApplicationRequestDTO.getAmount(),
                isInsuranceEnabled);
        BigDecimal rate = scoringService.calculateRate(isInsuranceEnabled, isSalaryClient);
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
