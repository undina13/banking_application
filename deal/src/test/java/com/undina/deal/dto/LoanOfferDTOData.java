package com.undina.deal.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.model.LoanOfferDTO;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoanOfferDTOData {
    public static LoanOfferDTO loanOfferDTO1 = new LoanOfferDTO()
            .applicationId(null)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10000))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(893.17))
            .rate(BigDecimal.valueOf(13))
            .isInsuranceEnabled(false)
            .isSalaryClient(false);

    public static LoanOfferDTO loanOfferDTO2 = new LoanOfferDTO()
            .applicationId(null)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10120.00))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(894.42))
            .rate(BigDecimal.valueOf(11))
            .isInsuranceEnabled(true)
            .isSalaryClient(false);

    public static LoanOfferDTO loanOfferDTO3 = new LoanOfferDTO()
            .applicationId(null)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10000.00))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(879.16))
            .rate(BigDecimal.valueOf(10))
            .isInsuranceEnabled(false)
            .isSalaryClient(true);

    public static LoanOfferDTO loanOfferDTO4 = new LoanOfferDTO()
            .applicationId(null)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10120.00))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(880.32))
            .rate(BigDecimal.valueOf(8))
            .isInsuranceEnabled(true)
            .isSalaryClient(true);

    public static List<LoanOfferDTO> loanOfferDTOS = List.of(loanOfferDTO1, loanOfferDTO2, loanOfferDTO3, loanOfferDTO4);

    public static LoanOfferDTO loanOfferDTO11 = new LoanOfferDTO()
            .applicationId(1L)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10000))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(893.17))
            .rate(BigDecimal.valueOf(13))
            .isInsuranceEnabled(false)
            .isSalaryClient(false);

    public static LoanOfferDTO loanOfferDTO21 = new LoanOfferDTO()
            .applicationId(1L)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10120.00))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(894.42))
            .rate(BigDecimal.valueOf(11))
            .isInsuranceEnabled(true)
            .isSalaryClient(false);

    public static LoanOfferDTO loanOfferDTO31 = new LoanOfferDTO()
            .applicationId(1L)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10000.00))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(879.16))
            .rate(BigDecimal.valueOf(10))
            .isInsuranceEnabled(false)
            .isSalaryClient(true);

    public static LoanOfferDTO loanOfferDTO41 = new LoanOfferDTO()
            .applicationId(1L)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10120.00))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(880.32))
            .rate(BigDecimal.valueOf(8))
            .isInsuranceEnabled(true)
            .isSalaryClient(true);

    public static List<LoanOfferDTO> loanOfferDTOS1 = List.of(loanOfferDTO11, loanOfferDTO21, loanOfferDTO31, loanOfferDTO41);

    public static LoanOfferDTO loanOfferDTO13 = new LoanOfferDTO()
            .applicationId(11L)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10120.00))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(880.32))
            .rate(BigDecimal.valueOf(8))
            .isInsuranceEnabled(true)
            .isSalaryClient(true);
}
