package com.undina.conveyor.service;

import com.undina.conveyor.model.*;
import com.undina.conveyor.exception.RejectionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public CreditDTO getCalculation(ScoringDataDTO scoringDataDTO) {
        log.info("getCalculation - scoringDataDTO: {}", scoringDataDTO.toString());
        Integer age = Period.between(scoringDataDTO.getBirthdate(), LocalDate.now()).getYears();
        checkScoringData(scoringDataDTO, age);
        BigDecimal personalRate = getPersonalRate(scoringDataDTO, age);
        BigDecimal rate = scoringService.calculateRate(scoringDataDTO.getIsInsuranceEnabled(),
                scoringDataDTO.getIsSalaryClient(), personalRate);
        BigDecimal totalAmount = scoringService.evaluateTotalAmountByServices(scoringDataDTO.getAmount(),
                scoringDataDTO.getIsInsuranceEnabled(), scoringDataDTO.getTerm());
        BigDecimal monthlyPayment = scoringService.calculateMonthlyPayment(totalAmount, scoringDataDTO.getTerm(), rate);
        BigDecimal psk = calculatePsk(monthlyPayment, totalAmount, scoringDataDTO.getTerm());
        List<PaymentScheduleElement> paymentScheduleElements = calculatePaymentScheduleElement(scoringDataDTO.getAmount(),
                scoringDataDTO.getTerm(), monthlyPayment, totalAmount);
        return CreditDTO.builder()
                .amount(scoringDataDTO.getAmount())
                .term(scoringDataDTO.getTerm())
                .monthlyPayment(monthlyPayment)
                .rate(rate)
                .psk(psk)
                .isInsuranceEnabled(scoringDataDTO.getIsInsuranceEnabled())
                .isSalaryClient(scoringDataDTO.getIsSalaryClient())
                .paymentSchedule(paymentScheduleElements)
                .build();
    }

    private void checkScoringData(ScoringDataDTO scoringDataDTO, Integer age) {
        EmploymentDTO employment = scoringDataDTO.getEmployment();
        if (employment.getEmploymentStatus().equals(EmploymentStatus.UNEMPLOYED)
                || scoringDataDTO.getAmount().compareTo(employment.getSalary().multiply(BigDecimal.valueOf(20))) > 0
                || age > 60 || age < 20
                || employment.getWorkExperienceTotal() < 12
                || employment.getWorkExperienceCurrent() < 3) {
            throw new RejectionException("Вам отказано в кредите");
        }
    }

    private BigDecimal getPersonalRate(ScoringDataDTO scoringDataDTO, Integer age) {
        BigDecimal rate = baseRate;
        if (scoringDataDTO.getEmployment().getEmploymentStatus().equals(EmploymentStatus.SELF_EMPLOYED)) {
            rate = rate.add(BigDecimal.valueOf(1));
        }
        if (scoringDataDTO.getEmployment().getEmploymentStatus().equals(EmploymentStatus.BUSINESS_OWNER)) {
            rate = rate.add(BigDecimal.valueOf(3));
        }
        if (scoringDataDTO.getEmployment().getPosition().equals(Position.MIDDLE_MANAGER)) {
            rate = rate.subtract(BigDecimal.valueOf(2));
        }
        if (scoringDataDTO.getEmployment().getPosition().equals(Position.TOP_MANAGER)) {
            rate = rate.subtract(BigDecimal.valueOf(4));
        }
        if (scoringDataDTO.getMaritalStatus().equals(MaritalStatus.MARRIED)) {
            rate = rate.subtract(BigDecimal.valueOf(3));
        }
        if (scoringDataDTO.getMaritalStatus().equals(MaritalStatus.DIVORCED)) {
            rate = rate.add(BigDecimal.valueOf(1));
        }
        if (scoringDataDTO.getDependentAmount() > 1) {
            rate = rate.add(BigDecimal.valueOf(1));
        }
        if (scoringDataDTO.getGender().equals(Gender.FEMALE) && age >= 35 && age < 60) {
            rate = rate.subtract(BigDecimal.valueOf(3));
        }
        if (scoringDataDTO.getGender().equals(Gender.MALE) && age >= 30 && age < 55) {
            rate = rate.subtract(BigDecimal.valueOf(3));
        }
        if (scoringDataDTO.getGender().equals(Gender.NON_BINARY)) {
            rate = rate.add(BigDecimal.valueOf(3));
        }
        return rate;
    }

    private BigDecimal calculatePsk(BigDecimal monthlyPayment, BigDecimal totalAmount, Integer term) {
        return monthlyPayment.multiply(new BigDecimal(term))
                .divide(totalAmount, MathContext.DECIMAL128)
                .subtract(new BigDecimal("1"))
                .multiply(new BigDecimal("12"))
                .divide(new BigDecimal(term), MathContext.DECIMAL128)
                .multiply(new BigDecimal("100"))
                .setScale(2, RoundingMode.HALF_UP);
    }

   private List<PaymentScheduleElement> calculatePaymentScheduleElement(BigDecimal amount, Integer term,
                                                                 BigDecimal monthlyPayment, BigDecimal totalAmount) {
        List<PaymentScheduleElement> paymentScheduleElements = new ArrayList<>();
        BigDecimal interestPayment = monthlyPayment.subtract(amount.divide(BigDecimal.valueOf(term),
                MathContext.DECIMAL128)).setScale(2, RoundingMode.HALF_DOWN);
        BigDecimal debtPayment = monthlyPayment.subtract(interestPayment).setScale(2, RoundingMode.HALF_DOWN);
        LocalDate date = LocalDate.now();
        BigDecimal remainingDebt = monthlyPayment.multiply(BigDecimal.valueOf(term));
        for (int i = 1; i < term; i++) {
            date = date.plusMonths(1);
            remainingDebt = remainingDebt.subtract(monthlyPayment);
            paymentScheduleElements.add(PaymentScheduleElement.builder()
                    .number(i)
                    .date(date)
                    .totalPayment(monthlyPayment)
                    .interestPayment(interestPayment)
                    .debtPayment(debtPayment)
                    .remainingDebt(remainingDebt)
                    .build());
        }


            paymentScheduleElements.add(PaymentScheduleElement.builder()
                    .number(term)
                    .date(date.plusMonths(1))
                    .totalPayment(remainingDebt)
                    .interestPayment(interestPayment)
                    .debtPayment(remainingDebt.subtract(interestPayment).setScale(2, RoundingMode.HALF_DOWN))
                    .remainingDebt(BigDecimal.valueOf(0))
                    .build());

        return paymentScheduleElements;
    }
}
