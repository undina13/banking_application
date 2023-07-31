package com.undina.conveyor.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.undina.conveyor.model.PaymentScheduleElementData.paymentScheduleElements;
import static com.undina.conveyor.model.PaymentScheduleElementData.paymentScheduleElements1;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
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

    public static CreditDTO creditDTO1 = CreditDTO
            .builder()
            .amount(BigDecimal.valueOf(100000))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(8652.67))
            .rate(BigDecimal.valueOf(7))
            .psk(BigDecimal.valueOf(3.83))
            .isInsuranceEnabled(false)
            .isSalaryClient(true)
            .paymentSchedule(paymentScheduleElements1)
            .build();

}
