package com.undina.conveyor.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ScoringService {
    public BigDecimal evaluateTotalAmountByServices(BigDecimal amount, Boolean isInsuranceEnabled) {
        return  null;
    }

    public BigDecimal calculateRate(Boolean isInsuranceEnabled, Boolean isSalaryClient) {
        return  null;
    }

    public BigDecimal calculateMonthlyPayment(BigDecimal totalAmount, Integer term, BigDecimal rate) {
        return  null;
    }
}
