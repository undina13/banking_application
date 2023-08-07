package com.undina.deal.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanOfferDTO {
    private final Long applicationId;

    private final BigDecimal requestedAmount;

    private final BigDecimal totalAmount;

    private final Integer term;

    private final BigDecimal monthlyPayment;

    private final BigDecimal rate;

    private final Boolean isInsuranceEnabled;

    private final Boolean isSalaryClient;
}

