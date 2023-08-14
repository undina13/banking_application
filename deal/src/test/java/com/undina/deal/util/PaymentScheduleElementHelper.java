package com.undina.deal.util;

import com.undina.deal.dto.PaymentScheduleElement;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentScheduleElementHelper {
    public static PaymentScheduleElement paymentScheduleElement1 = PaymentScheduleElement.builder()
            .number(1)
            .date(LocalDate.now().plusMonths(1))
            .totalPayment(BigDecimal.valueOf(1725.48))
            .interestPayment(BigDecimal.valueOf(103.53))
            .debtPayment(BigDecimal.valueOf(1621.95))
            .remainingDebt(BigDecimal.valueOf(8627.40))
            .build();

    public static PaymentScheduleElement paymentScheduleElement2 = PaymentScheduleElement.builder()
            .number(2)
            .date(LocalDate.now().plusMonths(2))
            .totalPayment(BigDecimal.valueOf(1725.48))
            .interestPayment(BigDecimal.valueOf(86.27))
            .debtPayment(BigDecimal.valueOf(1639.21))
            .remainingDebt(BigDecimal.valueOf(6901.92))
            .build();

    public static PaymentScheduleElement paymentScheduleElement3 = PaymentScheduleElement.builder()
            .number(3)
            .date(LocalDate.now().plusMonths(3))
            .totalPayment(BigDecimal.valueOf(1725.48))
            .interestPayment(BigDecimal.valueOf(69.02))
            .debtPayment(BigDecimal.valueOf(1656.46))
            .remainingDebt(BigDecimal.valueOf(5176.44))
            .build();

    public static PaymentScheduleElement paymentScheduleElement4 = PaymentScheduleElement.builder()
            .number(4)
            .date(LocalDate.now().plusMonths(4))
            .totalPayment(BigDecimal.valueOf(1725.48))
            .interestPayment(BigDecimal.valueOf(51.76))
            .debtPayment(BigDecimal.valueOf(1673.72))
            .remainingDebt(BigDecimal.valueOf(3450.96))
            .build();

    public static PaymentScheduleElement paymentScheduleElement5 = PaymentScheduleElement.builder()
            .number(5)
            .date(LocalDate.now().plusMonths(5))
            .totalPayment(BigDecimal.valueOf(1725.48))
            .interestPayment(BigDecimal.valueOf(34.51))
            .debtPayment(BigDecimal.valueOf(1690.97))
            .remainingDebt(BigDecimal.valueOf(1725.48))
            .build();

    public static PaymentScheduleElement paymentScheduleElement6 = PaymentScheduleElement.builder()
            .number(6)
            .date(LocalDate.now().plusMonths(6))
            .totalPayment(BigDecimal.valueOf(1725.48))
            .interestPayment(BigDecimal.valueOf(17.25))
            .debtPayment(BigDecimal.valueOf(1708.23))
            .remainingDebt(BigDecimal.valueOf(0))
            .build();

    public static List<PaymentScheduleElement> paymentScheduleElements = List.of(paymentScheduleElement1,
            paymentScheduleElement2, paymentScheduleElement3, paymentScheduleElement4, paymentScheduleElement5,
            paymentScheduleElement6);


}
