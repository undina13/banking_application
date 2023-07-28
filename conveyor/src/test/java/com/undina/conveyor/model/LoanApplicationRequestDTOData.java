package com.undina.conveyor.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.model.LoanApplicationRequestDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoanApplicationRequestDTOData {
    public static LoanApplicationRequestDTO loanApplicationRequestDTO1 = new LoanApplicationRequestDTO()
            .amount(BigDecimal.valueOf(10000))
            .term(12)
            .firstName("Ivan")
            .lastName("Ivanov")
            .middleName("Ivanovich")
            .email("ivanov@mail.ru")
            .birthdate(LocalDate.now().minusYears(18))
            .passportSeries("1234")
            .passportNumber("123456");

    public static LoanApplicationRequestDTO loanApplicationRequestDTOSmallAmount =  new LoanApplicationRequestDTO()
            .amount(BigDecimal.valueOf(1000))
            .term(6)
            .firstName("Ivan")
            .lastName("Ivanov")
            .middleName("Ivanovich")
            .email("ivanov@mail.ru")
            .birthdate(LocalDate.now().minusYears(18))
            .passportSeries("1234")
            .passportNumber("123456");

    public static LoanApplicationRequestDTO loanApplicationRequestDTOTooYoung =  new LoanApplicationRequestDTO()
            .amount(BigDecimal.valueOf(10000))
            .term(6)
            .firstName("Ivan")
            .lastName("Ivanov")
            .middleName("Ivanovich")
            .email("ivanov@mail.ru")
            .birthdate(LocalDate.now().minusYears(13))
            .passportSeries("1234")
            .passportNumber("123456");

}