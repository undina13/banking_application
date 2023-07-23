package com.undina.conveyor;

import com.undina.conveyor.dto.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DataDTO {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static LoanApplicationRequestDTO loanApplicationRequestDTO1 = LoanApplicationRequestDTO
            .builder()
            .amount(BigDecimal.valueOf(10000))
            .term(12)
            .firstName("Ivan")
            .lastName("Ivanov")
            .middleName("Ivanovich")
            .email("ivanov@mail.ru")
            .birthdate(LocalDate.now().minusYears(18))
            .passportSeries("1234")
            .passportNumber("123456")
            .build();

    public static LoanApplicationRequestDTO loanApplicationRequestDTOTooYoung = LoanApplicationRequestDTO
            .builder()
            .amount(BigDecimal.valueOf(10000))
            .term(6)
            .firstName("Ivan")
            .lastName("Ivanov")
            .middleName("Ivanovich")
            .email("ivanov@mail.ru")
            .birthdate(LocalDate.now().minusYears(16))
            .passportSeries("1234")
            .passportNumber("123456")
            .build();

    public static LoanOfferDTO loanOfferDTO1 = LoanOfferDTO
            .builder()
            .applicationId(null)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10000))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(893.17))
            .rate(BigDecimal.valueOf(13))
            .isInsuranceEnabled(false)
            .isSalaryClient(false)
            .build();

    public static LoanOfferDTO loanOfferDTO2 = LoanOfferDTO
            .builder()
            .applicationId(null)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10120.00))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(894.42))
            .rate(BigDecimal.valueOf(11))
            .isInsuranceEnabled(true)
            .isSalaryClient(false)
            .build();

    public static LoanOfferDTO loanOfferDTO3 = LoanOfferDTO
            .builder()
            .applicationId(null)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10000.00))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(879.16))
            .rate(BigDecimal.valueOf(10))
            .isInsuranceEnabled(false)
            .isSalaryClient(true)
            .build();

    public static LoanOfferDTO loanOfferDTO4 = LoanOfferDTO
            .builder()
            .applicationId(null)
            .requestedAmount(BigDecimal.valueOf(10000))
            .totalAmount(BigDecimal.valueOf(10120.00))
            .term(12)
            .monthlyPayment(BigDecimal.valueOf(880.32))
            .rate(BigDecimal.valueOf(8))
            .isInsuranceEnabled(true)
            .isSalaryClient(true)
            .build();

    public static EmploymentDTO employmentDTO = EmploymentDTO
            .builder()
            .employmentStatus(EmploymentStatus.SELF_EMPLOYED)
            .employerINN("567676686767")
            .salary(BigDecimal.valueOf(50000))
            .position(Position.MANAGER)
            .workExperienceTotal(17)
            .workExperienceCurrent(5)
            .build();

    public static ScoringDataDTO scoringDataDTO1 = ScoringDataDTO
            .builder()
            .term(6)
            .amount(BigDecimal.valueOf(10000))
            .firstName("Ivan")
            .lastName("Ivanov")
            .middleName("Ivanovich")
            .gender(Gender.MALE)
            .birthdate(LocalDate.parse("2000-07-13", formatter))
            .passportSeries("4444")
            .passportNumber("666666")
            .passportIssueDate(LocalDate.parse("2022-07-13", formatter))
            .passportIssueBranch("556-876")
            .maritalStatus(MaritalStatus.MARRIED)
            .dependentAmount(2)
            .employment(employmentDTO)
            .account("account")
            .isInsuranceEnabled(false)
            .isSalaryClient(false)
            .build();

    public static PaymentScheduleElement paymentScheduleElement1 = PaymentScheduleElement
            .builder()
            .number(1)
            .date(LocalDate.now().plusMonths(1))
            .totalPayment(BigDecimal.valueOf(1725.48))
            .interestPayment(BigDecimal.valueOf(58.81))
            .debtPayment(BigDecimal.valueOf(1666.67))
            .remainingDebt(BigDecimal.valueOf(8274.52))
            .build();

    public static PaymentScheduleElement paymentScheduleElement2 = PaymentScheduleElement
            .builder()
            .number(2)
            .date(LocalDate.now().plusMonths(2))
            .totalPayment(BigDecimal.valueOf(1725.48))
            .interestPayment(BigDecimal.valueOf(58.81))
            .debtPayment(BigDecimal.valueOf(1666.67))
            .remainingDebt(BigDecimal.valueOf(6549.04))
            .build();

    public static PaymentScheduleElement paymentScheduleElement3 = PaymentScheduleElement
            .builder()
            .number(3)
            .date(LocalDate.now().plusMonths(3))
            .totalPayment(BigDecimal.valueOf(1725.48))
            .interestPayment(BigDecimal.valueOf(58.81))
            .debtPayment(BigDecimal.valueOf(1666.67))
            .remainingDebt(BigDecimal.valueOf(4823.56))
            .build();

    public static PaymentScheduleElement paymentScheduleElement4 = PaymentScheduleElement
            .builder()
            .number(4)
            .date(LocalDate.now().plusMonths(4))
            .totalPayment(BigDecimal.valueOf(1725.48))
            .interestPayment(BigDecimal.valueOf(58.81))
            .debtPayment(BigDecimal.valueOf(1666.67))
            .remainingDebt(BigDecimal.valueOf(3098.08))
            .build();

    public static PaymentScheduleElement paymentScheduleElement5 = PaymentScheduleElement
            .builder()
            .number(5)
            .date(LocalDate.now().plusMonths(5))
            .totalPayment(BigDecimal.valueOf(1725.48))
            .interestPayment(BigDecimal.valueOf(58.81))
            .debtPayment(BigDecimal.valueOf(1666.67))
            .remainingDebt(BigDecimal.valueOf(1372.60))
            .build();

    public static PaymentScheduleElement paymentScheduleElement6 = PaymentScheduleElement
            .builder()
            .number(6)
            .date(LocalDate.now().plusMonths(6))
            .totalPayment(BigDecimal.valueOf(1372.60))
            .interestPayment(BigDecimal.valueOf(58.81))
            .debtPayment(BigDecimal.valueOf(1313.79))
            .remainingDebt(BigDecimal.valueOf(0))
            .build();

    public static List<PaymentScheduleElement> paymentScheduleElements = List.of(paymentScheduleElement1,
            paymentScheduleElement2, paymentScheduleElement3, paymentScheduleElement4, paymentScheduleElement5,
            paymentScheduleElement6);

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

    public static EmploymentDTO employmentDTO2 = EmploymentDTO
            .builder()
            .employmentStatus(EmploymentStatus.UNEMPLOYED)
            .employerINN("567676686767")
            .salary(BigDecimal.valueOf(50000))
            .position(Position.MANAGER)
            .workExperienceTotal(17)
            .workExperienceCurrent(5)
            .build();

    public static ScoringDataDTO scoringDataDTOUnemployed = ScoringDataDTO
            .builder()
            .term(6)
            .amount(BigDecimal.valueOf(10000))
            .firstName("Ivan")
            .lastName("Ivanov")
            .middleName("Ivanovich")
            .gender(Gender.MALE)
            .birthdate(LocalDate.parse("2000-07-13", formatter))
            .passportSeries("4444")
            .passportNumber("666666")
            .passportIssueDate(LocalDate.parse("2022-07-13", formatter))
            .passportIssueBranch("556-876")
            .maritalStatus(MaritalStatus.MARRIED)
            .dependentAmount(2)
            .employment(employmentDTO2)
            .account("account")
            .isInsuranceEnabled(false)
            .isSalaryClient(false)
            .build();
}
