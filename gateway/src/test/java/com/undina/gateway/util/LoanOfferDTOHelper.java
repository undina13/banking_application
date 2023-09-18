package com.undina.gateway.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.model.LoanOfferDTO;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoanOfferDTOHelper {
    public static LoanOfferDTO loanOfferDTO11 =  LoanOfferDTO
            .builder()
            .applicationId(1L)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10000))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(893.17))
            .rate(BigDecimal.valueOf(13))
            .isInsuranceEnabled(false)
            .isSalaryClient(false)
            .build();

    public static LoanOfferDTO loanOfferDTO21 =  LoanOfferDTO
            .builder()
            .applicationId(1L)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10120.00))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(894.42))
            .rate(BigDecimal.valueOf(11))
            .isInsuranceEnabled(true)
            .isSalaryClient(false)
            .build();

    public static LoanOfferDTO loanOfferDTO31 = LoanOfferDTO
            .builder()
            .applicationId(1L)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10000.00))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(879.16))
            .rate(BigDecimal.valueOf(10))
            .isInsuranceEnabled(false)
            .isSalaryClient(true)
            .build();

    public static LoanOfferDTO loanOfferDTO41 = LoanOfferDTO
            .builder()
            .applicationId(1L)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10120.00))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(880.32))
            .rate(BigDecimal.valueOf(8))
            .isInsuranceEnabled(true)
            .isSalaryClient(true)
            .build();

    public static List<LoanOfferDTO> loanOfferDTOS1 = List.of(loanOfferDTO11, loanOfferDTO21, loanOfferDTO31, loanOfferDTO41);

    public static LoanOfferDTO loanOfferDTO13 = LoanOfferDTO
            .builder()
            .applicationId(11L)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10120.00))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(880.32))
            .rate(BigDecimal.valueOf(8))
            .isInsuranceEnabled(true)
            .isSalaryClient(true)
            .build();
}
