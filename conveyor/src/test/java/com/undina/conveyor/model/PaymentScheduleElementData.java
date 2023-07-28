package com.undina.conveyor.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.client.model.PaymentScheduleElement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentScheduleElementData {
    public static PaymentScheduleElement paymentScheduleElement1 = new PaymentScheduleElement()
            .number(1)
            .date(LocalDate.now().plusMonths(1))
            .totalPayment(BigDecimal.valueOf(1725.48))
            .interestPayment(BigDecimal.valueOf(103.53))
            .debtPayment(BigDecimal.valueOf(1621.95))
            .remainingDebt(BigDecimal.valueOf(8627.40));

    public static PaymentScheduleElement paymentScheduleElement2 = new PaymentScheduleElement()
            .number(2)
            .date(LocalDate.now().plusMonths(2))
            .totalPayment(BigDecimal.valueOf(1725.48))
            .interestPayment(BigDecimal.valueOf(86.27))
            .debtPayment(BigDecimal.valueOf(1639.21))
            .remainingDebt(BigDecimal.valueOf(6901.92));

    public static PaymentScheduleElement paymentScheduleElement3 = new PaymentScheduleElement()
            .number(3)
            .date(LocalDate.now().plusMonths(3))
            .totalPayment(BigDecimal.valueOf(1725.48))
            .interestPayment(BigDecimal.valueOf(69.02))
            .debtPayment(BigDecimal.valueOf(1656.46))
            .remainingDebt(BigDecimal.valueOf(5176.44));

    public static PaymentScheduleElement paymentScheduleElement4 = new PaymentScheduleElement()
            .number(4)
            .date(LocalDate.now().plusMonths(4))
            .totalPayment(BigDecimal.valueOf(1725.48))
            .interestPayment(BigDecimal.valueOf(51.76))
            .debtPayment(BigDecimal.valueOf(1673.72))
            .remainingDebt(BigDecimal.valueOf(3450.96));

    public static PaymentScheduleElement paymentScheduleElement5 = new PaymentScheduleElement()
            .number(5)
            .date(LocalDate.now().plusMonths(5))
            .totalPayment(BigDecimal.valueOf(1725.48))
            .interestPayment(BigDecimal.valueOf(34.51))
            .debtPayment(BigDecimal.valueOf(1690.97))
            .remainingDebt(BigDecimal.valueOf(1725.48));

    public static PaymentScheduleElement paymentScheduleElement6 = new PaymentScheduleElement()
            .number(6)
            .date(LocalDate.now().plusMonths(6))
            .totalPayment(BigDecimal.valueOf(1725.48))
            .interestPayment(BigDecimal.valueOf(17.25))
            .debtPayment(BigDecimal.valueOf(1708.23))
            .remainingDebt(BigDecimal.valueOf(0));

    public static List<PaymentScheduleElement> paymentScheduleElements = List.of(paymentScheduleElement1,
            paymentScheduleElement2, paymentScheduleElement3, paymentScheduleElement4, paymentScheduleElement5,
            paymentScheduleElement6);

    public static PaymentScheduleElement paymentScheduleElement7 = new PaymentScheduleElement()
            .number(1)
            .date(LocalDate.now().plusMonths(1))
            .totalPayment(BigDecimal.valueOf(8652.67))
            .interestPayment(BigDecimal.valueOf(605.69))
            .debtPayment(BigDecimal.valueOf(8046.98))
            .remainingDebt(BigDecimal.valueOf(95179.37));

    public static PaymentScheduleElement paymentScheduleElement8 = new PaymentScheduleElement()
            .number(2)
            .date(LocalDate.now().plusMonths(2))
            .totalPayment(BigDecimal.valueOf(8652.67))
            .interestPayment(BigDecimal.valueOf(555.21))
            .debtPayment(BigDecimal.valueOf(8097.46))
            .remainingDebt(BigDecimal.valueOf(86526.70).setScale(2, RoundingMode.HALF_DOWN));

    public static PaymentScheduleElement paymentScheduleElement9 = new PaymentScheduleElement()
            .number(3)
            .date(LocalDate.now().plusMonths(3))
            .totalPayment(BigDecimal.valueOf(8652.67))
            .interestPayment(BigDecimal.valueOf(504.74))
            .debtPayment(BigDecimal.valueOf(8147.93))
            .remainingDebt(BigDecimal.valueOf(77874.03));

    public static PaymentScheduleElement paymentScheduleElement10 = new PaymentScheduleElement()
            .number(4)
            .date(LocalDate.now().plusMonths(4))
            .totalPayment(BigDecimal.valueOf(8652.67))
            .interestPayment(BigDecimal.valueOf(454.27))
            .debtPayment(BigDecimal.valueOf(8198.40).setScale(2, RoundingMode.HALF_DOWN))
            .remainingDebt(BigDecimal.valueOf(69221.36));

    public static PaymentScheduleElement paymentScheduleElement11 = new PaymentScheduleElement()
            .number(5)
            .date(LocalDate.now().plusMonths(5))
            .totalPayment(BigDecimal.valueOf(8652.67))
            .interestPayment(BigDecimal.valueOf(403.79))
            .debtPayment(BigDecimal.valueOf(8248.88))
            .remainingDebt(BigDecimal.valueOf(60568.69));

    public static PaymentScheduleElement paymentScheduleElement12 = new PaymentScheduleElement()
            .number(6)
            .date(LocalDate.now().plusMonths(6))
            .totalPayment(BigDecimal.valueOf(8652.67))
            .interestPayment(BigDecimal.valueOf(353.32))
            .debtPayment(BigDecimal.valueOf(8299.35))
            .remainingDebt(BigDecimal.valueOf(51916.02));

    public static PaymentScheduleElement paymentScheduleElement13 = new PaymentScheduleElement()
            .number(7)
            .date(LocalDate.now().plusMonths(7))
            .totalPayment(BigDecimal.valueOf(8652.67))
            .interestPayment(BigDecimal.valueOf(302.84))
            .debtPayment(BigDecimal.valueOf(8349.83))
            .remainingDebt(BigDecimal.valueOf(43263.35));

    public static PaymentScheduleElement paymentScheduleElement14 = new PaymentScheduleElement()
            .number(8)
            .date(LocalDate.now().plusMonths(8))
            .totalPayment(BigDecimal.valueOf(8652.67))
            .interestPayment(BigDecimal.valueOf(252.37))
            .debtPayment(BigDecimal.valueOf(8400.30).setScale(2, RoundingMode.HALF_DOWN))
            .remainingDebt(BigDecimal.valueOf(34610.68));

    public static PaymentScheduleElement paymentScheduleElement15 = new PaymentScheduleElement()
            .number(9)
            .date(LocalDate.now().plusMonths(9))
            .totalPayment(BigDecimal.valueOf(8652.67))
            .interestPayment(BigDecimal.valueOf(201.90).setScale(2, RoundingMode.HALF_DOWN))
            .debtPayment(BigDecimal.valueOf(8450.77))
            .remainingDebt(BigDecimal.valueOf(25958.01));

    public static PaymentScheduleElement paymentScheduleElement16 = new PaymentScheduleElement()
            .number(10)
            .date(LocalDate.now().plusMonths(10))
            .totalPayment(BigDecimal.valueOf(8652.67))
            .interestPayment(BigDecimal.valueOf(151.42))
            .debtPayment(BigDecimal.valueOf(8501.25))
            .remainingDebt(BigDecimal.valueOf(17305.34));

    public static PaymentScheduleElement paymentScheduleElement17 = new PaymentScheduleElement()
            .number(11)
            .date(LocalDate.now().plusMonths(11))
            .totalPayment(BigDecimal.valueOf(8652.67))
            .interestPayment(BigDecimal.valueOf(100.95))
            .debtPayment(BigDecimal.valueOf(8551.72))
            .remainingDebt(BigDecimal.valueOf(8652.67));

    public static PaymentScheduleElement paymentScheduleElement18 = new PaymentScheduleElement()
            .number(12)
            .date(LocalDate.now().plusMonths(12))
            .totalPayment(BigDecimal.valueOf(8652.67))
            .interestPayment(BigDecimal.valueOf(50.47))
            .debtPayment(BigDecimal.valueOf(8602.20).setScale(2, RoundingMode.HALF_DOWN))
            .remainingDebt(BigDecimal.valueOf(0.00).setScale(2, RoundingMode.HALF_DOWN));

    public static List<PaymentScheduleElement> paymentScheduleElements1 = List.of(paymentScheduleElement7,
            paymentScheduleElement8, paymentScheduleElement9, paymentScheduleElement10, paymentScheduleElement11,
            paymentScheduleElement12, paymentScheduleElement13, paymentScheduleElement14, paymentScheduleElement15,
            paymentScheduleElement16, paymentScheduleElement17, paymentScheduleElement18);
}
