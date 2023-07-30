package com.undina.conveyor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.LoanApplicationRequestDTO;
import org.openapitools.model.LoanOfferDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import util.ModelFormatter;

import java.math.BigDecimal;
import java.util.Arrays;
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
        log.info("getLoanOffers  {}", ModelFormatter.toLogFormat(loanApplicationRequestDTO));
        List<LoanOfferDTO> loanOfferDTOS =
                Arrays.asList(createOffer(false, false, loanApplicationRequestDTO),
                        createOffer(true, false, loanApplicationRequestDTO),
                        createOffer(false, true, loanApplicationRequestDTO),
                        createOffer(true, true, loanApplicationRequestDTO));
        loanOfferDTOS.sort(Comparator.comparing(LoanOfferDTO::getRate).reversed());
        log.info("generateOffers result " + loanOfferDTOS);
        return loanOfferDTOS;
    }

    private LoanOfferDTO createOffer(Boolean isInsuranceEnabled, Boolean isSalaryClient,
                                     LoanApplicationRequestDTO loanApplicationRequestDTO) {
        BigDecimal totalAmount = scoringService.evaluateTotalAmountByServices(loanApplicationRequestDTO.getAmount(),
                isInsuranceEnabled, loanApplicationRequestDTO.getTerm());
        BigDecimal rate = scoringService.calculateRate(isInsuranceEnabled, isSalaryClient, baseRate);
        return new LoanOfferDTO()
                .requestedAmount(loanApplicationRequestDTO.getAmount())
                .totalAmount(totalAmount)
                .term(loanApplicationRequestDTO.getTerm())
                .isInsuranceEnabled(isInsuranceEnabled)
                .isSalaryClient(isSalaryClient)
                .rate(rate)
                .monthlyPayment(scoringService.calculateMonthlyPayment(totalAmount, loanApplicationRequestDTO.getTerm(),
                        rate));
    }
}
