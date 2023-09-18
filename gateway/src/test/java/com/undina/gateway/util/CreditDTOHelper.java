package com.undina.gateway.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.model.CreditDTO;

import java.math.BigDecimal;

import static com.undina.gateway.util.PaymentScheduleElementHelper.paymentScheduleElements;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreditDTOHelper {
    public static CreditDTO creditDTO = new CreditDTO()
            .amount(BigDecimal.valueOf(10000))
            .term(6)
            .monthlyPayment(BigDecimal.valueOf(1725.48))
            .rate(BigDecimal.valueOf(12))
            .psk(BigDecimal.valueOf(7.06))
            .isInsuranceEnabled(false)
            .isSalaryClient(false)
            .paymentSchedule(paymentScheduleElements);
}
