package com.undina.gateway.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.model.LoanApplicationRequestDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoanApplicationRequestDTOHelper {
    public static LoanApplicationRequestDTO loanApplicationRequestDTO1 =  LoanApplicationRequestDTO
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
}