package com.undina.conveyor.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoanOfferDTOData {
    public static LoanOfferDTO loanOfferDTO1 = LoanOfferDTO
            .builder()
            .applicationId(null)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10000))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(893.17))
            .rate(BigDecimal.valueOf(13))
            .isInsuranceEnabled(false)
            .isSalaryClient(false)
            .build();

    public static LoanOfferDTO loanOfferDTO2 = LoanOfferDTO
            .builder()
            .applicationId(null)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10120.00))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(894.42))
            .rate(BigDecimal.valueOf(11))
            .isInsuranceEnabled(true)
            .isSalaryClient(false)
            .build();

    public static LoanOfferDTO loanOfferDTO3 = LoanOfferDTO
            .builder()
            .applicationId(null)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10000.00))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(879.16))
            .rate(BigDecimal.valueOf(10))
            .isInsuranceEnabled(false)
            .isSalaryClient(true)
            .build();

    public static LoanOfferDTO loanOfferDTO4 = LoanOfferDTO
            .builder()
            .applicationId(null)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10120.00))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(880.32))
            .rate(BigDecimal.valueOf(8))
            .isInsuranceEnabled(true)
            .isSalaryClient(true)
            .build();
}
