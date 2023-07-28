package com.undina.conveyor.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.client.model.LoanOfferDTO;

import java.math.BigDecimal;

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
}
