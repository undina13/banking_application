package com.undina.deal.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.model.ScoringDataDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.undina.deal.util.EmploymentDTOHelper.employmentDTO;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScoringDataDTOHelper {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static ScoringDataDTO scoringDataDTO1 = new ScoringDataDTO()
            .amount(BigDecimal.valueOf(10120).setScale(1))
            .term(12)
            .firstName("Sidor")
            .lastName("Sidorov")
            .middleName("Petrovich")
            .gender(ScoringDataDTO.GenderEnum.MALE)
            .birthdate(LocalDate.parse("2005-08-09", formatter))
            .passportNumber("623456")
            .passportSeries("9999")
            .passportIssueBranch("556-876")
            .passportIssueDate(LocalDate.parse("2022-07-13", formatter))
            .maritalStatus(ScoringDataDTO.MaritalStatusEnum.MARRIED)
            .dependentAmount(2)
            .employment(employmentDTO)
            .account("account")
            .isInsuranceEnabled(true)
            .isSalaryClient(true);
}
