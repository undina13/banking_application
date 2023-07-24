package com.undina.conveyor.service;

import com.undina.conveyor.exception.RejectionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static com.undina.conveyor.model.CreditDTOData.creditDTO1;
import static com.undina.conveyor.model.ScoringDataDTOData.scoringDataDTO2;
import static com.undina.conveyor.model.ScoringDataDTOData.scoringDataDTOOldAge;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreditServiceTest {
    @Mock
    private ScoringService scoringService;
    @InjectMocks
    private CreditService creditService;

    @BeforeEach
    void beforeEach() {
        creditService.setBaseRate(BigDecimal.valueOf(13));
    }

    @Test
    void getCalculationTestOk() {
        when(scoringService.calculateRate(any(), any(), any())).thenReturn(BigDecimal.valueOf(7));
        when(scoringService.evaluateTotalAmountByServices(any(), any(), any())).thenReturn(BigDecimal.valueOf(100000));
        when(scoringService.calculateMonthlyPayment(any(), any(), any())).thenReturn(BigDecimal.valueOf(8652.67));
        Assertions.assertEquals(creditService.getCalculation(scoringDataDTO2), creditDTO1);
    }

    @Test
    void getCalculationTestReject() {
        assertThrows(RejectionException.class, () -> creditService.getCalculation(scoringDataDTOOldAge));
    }
}
