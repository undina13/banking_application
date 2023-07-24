package com.undina.conveyor.model;

import java.math.BigDecimal;

import static com.undina.conveyor.model.PaymentScheduleElementData.paymentScheduleElements;

public class CreditDTOData {
    public static CreditDTO creditDTO = CreditDTO
            .builder()
            .amount(BigDecimal.valueOf(10000))
            .term(6)
            .monthlyPayment(BigDecimal.valueOf(1725.48))
            .rate(BigDecimal.valueOf(12))
            .psk(BigDecimal.valueOf(7.06))
            .isInsuranceEnabled(false)
            .isSalaryClient(false)
            .paymentSchedule(paymentScheduleElements)
            .build();
}
