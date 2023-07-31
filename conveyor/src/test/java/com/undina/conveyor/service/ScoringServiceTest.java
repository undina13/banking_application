package com.undina.conveyor.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class ScoringServiceTest {
    @InjectMocks
    private ScoringService scoringService;

    @BeforeEach
    void before() {
        ReflectionTestUtils.setField(scoringService, "insuranceRate", BigDecimal.valueOf(2));
        ReflectionTestUtils.setField(scoringService, "salaryRate", BigDecimal.valueOf(3));
        ReflectionTestUtils.setField(scoringService, "insurancePercentPrice", BigDecimal.valueOf(0.1));
    }

    @ParameterizedTest
    @MethodSource({"testByEvaluateTotalAmount"})
    void evaluateTotalAmountTest(BigDecimal amount, Boolean isInsuranceEnabled, Integer term, BigDecimal result) {
        assertThat(scoringService.evaluateTotalAmountByServices(amount, isInsuranceEnabled, term)).isEqualTo(result);
    }

    private static Stream<Arguments> testByEvaluateTotalAmount() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(50000), true, 7, BigDecimal.valueOf(50350.00)
                        .setScale(2, RoundingMode.HALF_UP)),
                Arguments.of(BigDecimal.valueOf(500000), false, 7, BigDecimal.valueOf(500000),
                        Arguments.of(BigDecimal.valueOf(1000000), true, 7, BigDecimal.valueOf(1007000.00)
                                .setScale(2, RoundingMode.HALF_UP))));
    }

    @ParameterizedTest
    @MethodSource({"testByCalculateRate"})
    void calculateRateTest(Boolean isInsuranceEnabled, Boolean isSalaryClient, BigDecimal rate, BigDecimal result) {
        assertThat(scoringService.calculateRate(isInsuranceEnabled, isSalaryClient, rate)).isEqualTo(result);
    }

    private static Stream<Arguments> testByCalculateRate() {
        return Stream.of(
                Arguments.of(true, true, BigDecimal.valueOf(13), BigDecimal.valueOf(8)),
                Arguments.of(true, false, BigDecimal.valueOf(13), BigDecimal.valueOf(11)),
                Arguments.of(false, true, BigDecimal.valueOf(13), BigDecimal.valueOf(10)),
                Arguments.of(false, false, BigDecimal.valueOf(13), BigDecimal.valueOf(13)));
    }

    @ParameterizedTest
    @MethodSource({"testByCalculateMonthlyPayment"})
    void calculateMonthlyPaymentTest(BigDecimal totalAmount, Integer term, BigDecimal rate, BigDecimal result) {
        assertThat(scoringService.calculateMonthlyPayment(totalAmount, term, rate)).isEqualTo(result);
    }

    private static Stream<Arguments> testByCalculateMonthlyPayment() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(50000), 7, BigDecimal.valueOf(8),
                        BigDecimal.valueOf(7334.60).setScale(2, RoundingMode.HALF_UP)),
                Arguments.of(BigDecimal.valueOf(50000), 7, BigDecimal.valueOf(13),
                        BigDecimal.valueOf(7455.72).setScale(2, RoundingMode.HALF_UP)),
                Arguments.of(BigDecimal.valueOf(50000), 7, BigDecimal.valueOf(10),
                        BigDecimal.valueOf(7382.93).setScale(2, RoundingMode.HALF_UP)));
    }
}
