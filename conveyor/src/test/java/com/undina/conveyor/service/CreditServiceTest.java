package com.undina.conveyor.service;

import com.undina.conveyor.exception.RejectionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

import static com.undina.conveyor.model.CreditDTOData.creditDTO1;
import static com.undina.conveyor.model.ScoringDataDTOData.scoringDataDTO2;
import static com.undina.conveyor.model.ScoringDataDTOData.scoringDataDTOOldAge;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditServiceTest {
    @Mock
    private ScoringService scoringService;
    @InjectMocks
    private CreditService creditService;

    @BeforeEach
    void beforeEach() {
        ReflectionTestUtils.setField(creditService, "baseRate", BigDecimal.valueOf(13));
    }

    @Test
    void getCalculationTestOk() {
        when(scoringService.calculateRate(anyBoolean(), anyBoolean(), any(BigDecimal.class)))
                .thenReturn(BigDecimal.valueOf(7));
        when(scoringService.evaluateTotalAmountByServices(any(BigDecimal.class), anyBoolean(), anyInt()))
                .thenReturn(BigDecimal.valueOf(100000));
        when(scoringService.calculateMonthlyPayment(any(BigDecimal.class), anyInt(), any(BigDecimal.class)))
                .thenReturn(BigDecimal.valueOf(8652.67));
        Assertions.assertEquals(creditService.getCalculation(scoringDataDTO2), creditDTO1);
        verify(scoringService, times(1)).calculateRate(false, true,
                BigDecimal.valueOf(10));
        verify(scoringService, times(1)).
                evaluateTotalAmountByServices(BigDecimal.valueOf(100000), false, 12);
        verify(scoringService, times(1)).
                calculateMonthlyPayment(BigDecimal.valueOf(100000), 12, BigDecimal.valueOf(7));

    }

    @Test
    void getCalculationTestReject() {
        verify(scoringService, never()).calculateRate(anyBoolean(), anyBoolean(), any(BigDecimal.class));
        verify(scoringService, never()).
                evaluateTotalAmountByServices(any(BigDecimal.class), anyBoolean(), anyInt());
        verify(scoringService, never()).
                calculateMonthlyPayment(any(BigDecimal.class), anyInt(), any(BigDecimal.class));
        assertThrows(RejectionException.class, () -> creditService.getCalculation(scoringDataDTOOldAge));
    }
}
