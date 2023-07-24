package com.undina.conveyor.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
@Slf4j
@Setter
public class ScoringService {

    @Value("${insurance_rate}")
    private BigDecimal insuranceRate;
    @Value("${salary_rate}")
    private BigDecimal salaryRate;
    @Value("${insurance_sum_in_month}")
    private BigDecimal insurancePercentPrice;

    public BigDecimal evaluateTotalAmountByServices(BigDecimal amount, Boolean isInsuranceEnabled, Integer term) {
        log.info("evaluateTotalAmountByServices - amount: {}, isInsuranceEnabled: {}, term: {}", amount,
                isInsuranceEnabled, term);
        if (isInsuranceEnabled) {
            BigDecimal insurancePrice = amount.multiply(insurancePercentPrice.divide(BigDecimal.valueOf(100), MathContext.DECIMAL128)).multiply(BigDecimal.valueOf(term));
            insurancePrice = insurancePrice.setScale(2, RoundingMode.HALF_UP);
            return amount.add(insurancePrice);
        } else {
            return amount;
        }
    }

    public BigDecimal calculateRate(Boolean isInsuranceEnabled, Boolean isSalaryClient, BigDecimal rate) {
        log.info("calculateRate - isInsuranceEnabled: {}, isSalaryClient: {}", isInsuranceEnabled, isSalaryClient);

        if (isInsuranceEnabled) {
            rate = rate.subtract(insuranceRate);
        }
        if (isSalaryClient) {
            rate = rate.subtract(salaryRate);
        }
        return rate;
    }

    public BigDecimal calculateMonthlyPayment(BigDecimal totalAmount, Integer term, BigDecimal rate) {
        log.info("calculateMonthlyPayment - totalAmount: {}, term: {}, rate: {}", totalAmount, term, rate);
        BigDecimal monthlyRate = rate.divide(new BigDecimal("100"), MathContext.DECIMAL128)
                .divide(new BigDecimal("12"), MathContext.DECIMAL128);


        return monthlyRate.divide((new BigDecimal("1").add(monthlyRate)).pow(term)
                        .subtract(new BigDecimal("1")), MathContext.DECIMAL128)
                .add(monthlyRate)
                .multiply(totalAmount, MathContext.DECIMAL128)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
