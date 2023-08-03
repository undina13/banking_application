package com.undina.deal.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class PaymentScheduleElement {
    private final Integer number;

    private final LocalDate date;

    private final BigDecimal totalPayment;

    private final BigDecimal interestPayment;

    private final BigDecimal debtPayment;

    private final BigDecimal remainingDebt;
}

