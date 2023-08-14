package com.undina.deal.util;

import com.undina.deal.dto.CreditDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.undina.deal.util.PaymentScheduleElementHelper.paymentScheduleElements;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreditDTOHelper {
    public static CreditDTO creditDTO = CreditDTO.builder()
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
