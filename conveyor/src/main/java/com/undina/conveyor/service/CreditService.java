package com.undina.conveyor.service;

import com.undina.conveyor.exception.RejectionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.CreditDTO;
import org.openapitools.model.EmploymentDTO;
import org.openapitools.model.PaymentScheduleElement;
import org.openapitools.model.ScoringDataDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditService {
    private final ScoringService scoringService;
    @Value("${base_rate}")
    private BigDecimal baseRate;
    private static final BigDecimal MONTH_IN_YEAR = BigDecimal.valueOf(12);

    public CreditDTO getCalculation(ScoringDataDTO scoringDataDTO) {
        log.info("getCalculation scoringDataDTO : amount={} , term={}, gender={}, birthdate={}, maritalStatus={}" +
                        "dependentAmount={},isInsuranceEnabled={}, isSalaryClient={}, employmentStatus={}, " +
                        "salary={}, position={}, workExperienceTotal={}, workExperienceCurrent={}",
                scoringDataDTO.getAmount(), scoringDataDTO.getTerm(), scoringDataDTO.getGender(),
                scoringDataDTO.getBirthdate(), scoringDataDTO.getMaritalStatus(), scoringDataDTO.getDependentAmount(),
                scoringDataDTO.getIsInsuranceEnabled(), scoringDataDTO.getIsSalaryClient(),
                scoringDataDTO.getEmployment().getEmploymentStatus(), scoringDataDTO.getEmployment().getSalary(),
                scoringDataDTO.getEmployment().getPosition(), scoringDataDTO.getEmployment().getWorkExperienceTotal(),
                scoringDataDTO.getEmployment().getWorkExperienceCurrent());
        Integer age = Period.between(scoringDataDTO.getBirthdate(), LocalDate.now()).getYears();
        checkScoringData(scoringDataDTO, age);
        BigDecimal personalRate = getPersonalRate(scoringDataDTO, age);
        BigDecimal rate = scoringService.calculateRate(scoringDataDTO.getIsInsuranceEnabled(),
                scoringDataDTO.getIsSalaryClient(), personalRate);
        log.info("scoringService.calculateRate {} {} {}", scoringDataDTO.getIsInsuranceEnabled(),
                scoringDataDTO.getIsSalaryClient(), personalRate);
        BigDecimal totalAmount = scoringService.evaluateTotalAmountByServices(scoringDataDTO.getAmount(),
                scoringDataDTO.getIsInsuranceEnabled(), scoringDataDTO.getTerm());
        log.info("evaluateTotalAmountByServices {} {} {}", scoringDataDTO.getAmount(),
                scoringDataDTO.getIsInsuranceEnabled(), scoringDataDTO.getTerm());
        BigDecimal monthlyPayment = scoringService.calculateMonthlyPayment(totalAmount, scoringDataDTO.getTerm(), rate);
        log.info("calculateMonthlyPayment {} {} {}", totalAmount, scoringDataDTO.getTerm(), rate);
        BigDecimal psk = calculatePsk(monthlyPayment, totalAmount, scoringDataDTO.getTerm());
        List<PaymentScheduleElement> paymentScheduleElements = calculatePaymentScheduleElement(scoringDataDTO.getTerm(),
                monthlyPayment, rate);
        CreditDTO creditDTO = new CreditDTO()
                .amount(scoringDataDTO.getAmount())
                .term(scoringDataDTO.getTerm())
                .monthlyPayment(monthlyPayment)
                .rate(rate)
                .psk(psk)
                .isInsuranceEnabled(scoringDataDTO.getIsInsuranceEnabled())
                .isSalaryClient(scoringDataDTO.getIsSalaryClient())
                .paymentSchedule(paymentScheduleElements);

        log.info("getCalculation result: " + creditDTO);
        return creditDTO;
    }

    private void checkScoringData(ScoringDataDTO scoringDataDTO, Integer age) {
        EmploymentDTO employment = scoringDataDTO.getEmployment();
        if (employment.getEmploymentStatus().equals(EmploymentDTO.EmploymentStatusEnum.UNEMPLOYED)
                || scoringDataDTO.getAmount().compareTo(employment.getSalary().multiply(BigDecimal.valueOf(20))) > 0
                || age > 60 || age < 20
                || employment.getWorkExperienceTotal() < 12
                || employment.getWorkExperienceCurrent() < 3) {
            throw new RejectionException("Вам отказано в кредите");
        }
    }

    private BigDecimal getPersonalRate(ScoringDataDTO scoringDataDTO, Integer age) {
        BigDecimal rate = baseRate;
        if (scoringDataDTO.getEmployment().getEmploymentStatus().equals(EmploymentDTO.EmploymentStatusEnum.SELF_EMPLOYED)) {
            rate = rate.add(BigDecimal.valueOf(1));
        }
        if (scoringDataDTO.getEmployment().getEmploymentStatus().equals(EmploymentDTO.EmploymentStatusEnum.BUSINESS_OWNER)) {
            rate = rate.add(BigDecimal.valueOf(3));
        }
        if (scoringDataDTO.getEmployment().getPosition().equals(EmploymentDTO.PositionEnum.MIDDLE_MANAGER)) {
            rate = rate.subtract(BigDecimal.valueOf(2));
        }
        if (scoringDataDTO.getEmployment().getPosition().equals(EmploymentDTO.PositionEnum.TOP_MANAGER)) {
            rate = rate.subtract(BigDecimal.valueOf(4));
        }
        if (scoringDataDTO.getMaritalStatus().equals(ScoringDataDTO.MaritalStatusEnum.MARRIED)) {
            rate = rate.subtract(BigDecimal.valueOf(3));
        }
        if (scoringDataDTO.getMaritalStatus().equals(ScoringDataDTO.MaritalStatusEnum.DIVORCED)) {
            rate = rate.add(BigDecimal.valueOf(1));
        }
        if (scoringDataDTO.getDependentAmount() > 1) {
            rate = rate.add(BigDecimal.valueOf(1));
        }
        if (scoringDataDTO.getGender().equals(ScoringDataDTO.GenderEnum.FEMALE) && age >= 35 && age < 60) {
            rate = rate.subtract(BigDecimal.valueOf(3));
        }
        if (scoringDataDTO.getGender().equals(ScoringDataDTO.GenderEnum.MALE) && age >= 30 && age < 55) {
            rate = rate.subtract(BigDecimal.valueOf(3));
        }
        if (scoringDataDTO.getGender().equals(ScoringDataDTO.GenderEnum.NON_BINARY)) {
            rate = rate.add(BigDecimal.valueOf(3));
        }
        return rate;
    }

    private BigDecimal calculatePsk(BigDecimal monthlyPayment, BigDecimal amount, Integer term) {
        return monthlyPayment.multiply(new BigDecimal(term))
                .divide(amount, MathContext.DECIMAL128)
                .subtract(new BigDecimal("1"))
                .multiply(MONTH_IN_YEAR)
                .divide(new BigDecimal(term), MathContext.DECIMAL128)
                .multiply(new BigDecimal("100"))
                .setScale(2, RoundingMode.HALF_UP);
    }

    private List<PaymentScheduleElement> calculatePaymentScheduleElement(Integer term, BigDecimal monthlyPayment,
                                                                         BigDecimal rate) {
        List<PaymentScheduleElement> paymentScheduleElements = new ArrayList<>();
        BigDecimal remainingDebt = monthlyPayment.multiply(BigDecimal.valueOf(term));
        BigDecimal interestPayment;
        BigDecimal debtPayment;
        LocalDate date = LocalDate.now();

        for (int i = 1; i <= term; i++) {
            date = date.plusMonths(1);
            interestPayment = getInterestPayment(remainingDebt, rate);
            debtPayment = monthlyPayment.subtract(interestPayment).setScale(2, RoundingMode.HALF_DOWN);
            remainingDebt = remainingDebt.subtract(monthlyPayment);
            paymentScheduleElements.add(new PaymentScheduleElement()
                    .number(i)
                    .date(date)
                    .totalPayment(monthlyPayment)
                    .interestPayment(interestPayment)
                    .debtPayment(debtPayment)
                    .remainingDebt(remainingDebt));
        }
        return paymentScheduleElements;
    }

    private BigDecimal getInterestPayment(BigDecimal remainingDebt, BigDecimal rate) {
        return rate.divide(BigDecimal.valueOf(100).multiply(MONTH_IN_YEAR), 10, RoundingMode.HALF_UP)
                .multiply(remainingDebt)
                .setScale(2, RoundingMode.HALF_DOWN);
    }
}
