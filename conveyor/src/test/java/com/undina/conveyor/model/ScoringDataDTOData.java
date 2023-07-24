package com.undina.conveyor.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.undina.conveyor.model.EmploymentDTOData.*;

public class ScoringDataDTOData {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static ScoringDataDTO scoringDataDTO1 = ScoringDataDTO
            .builder()
            .term(6)
            .amount(BigDecimal.valueOf(10000))
            .firstName("Ivan")
            .lastName("Ivanov")
            .middleName("Ivanovich")
            .gender(Gender.MALE)
            .birthdate(LocalDate.parse("2000-07-13", formatter))
            .passportSeries("4444")
            .passportNumber("666666")
            .passportIssueDate(LocalDate.parse("2022-07-13", formatter))
            .passportIssueBranch("556-876")
            .maritalStatus(MaritalStatus.MARRIED)
            .dependentAmount(2)
            .employment(employmentDTO)
            .account("account")
            .isInsuranceEnabled(false)
            .isSalaryClient(false)
            .build();

    public static ScoringDataDTO scoringDataDTOUnemployed = ScoringDataDTO
            .builder()
            .term(6)
            .amount(BigDecimal.valueOf(10000))
            .firstName("Ivan")
            .lastName("Ivanov")
            .middleName("Ivanovich")
            .gender(Gender.MALE)
            .birthdate(LocalDate.parse("2000-07-13", formatter))
            .passportSeries("4444")
            .passportNumber("666666")
            .passportIssueDate(LocalDate.parse("2022-07-13", formatter))
            .passportIssueBranch("556-876")
            .maritalStatus(MaritalStatus.MARRIED)
            .dependentAmount(2)
            .employment(employmentDTO2)
            .account("account")
            .isInsuranceEnabled(false)
            .isSalaryClient(false)
            .build();

    public static ScoringDataDTO scoringDataDTO2 = ScoringDataDTO
            .builder()
            .term(12)
            .amount(BigDecimal.valueOf(100000))
            .firstName("Anna")
            .lastName("Petrova")
            .middleName("Ivanovna")
            .gender(Gender.FEMALE)
            .birthdate(LocalDate.parse("1983-05-13", formatter))
            .passportSeries("4444")
            .passportNumber("666666")
            .passportIssueDate(LocalDate.parse("2022-07-13", formatter))
            .passportIssueBranch("556-876")
            .maritalStatus(MaritalStatus.DIVORCED)
            .dependentAmount(1)
            .employment(employmentDTO3)
            .account("account")
            .isInsuranceEnabled(false)
            .isSalaryClient(true)
            .build();

    public static ScoringDataDTO scoringDataDTOOldAge = ScoringDataDTO
            .builder()
            .term(12)
            .amount(BigDecimal.valueOf(100000))
            .firstName("Anna")
            .lastName("Petrova")
            .middleName("Ivanovna")
            .gender(Gender.FEMALE)
            .birthdate(LocalDate.parse("1913-05-13", formatter))
            .passportSeries("4444")
            .passportNumber("666666")
            .passportIssueDate(LocalDate.parse("2022-07-13", formatter))
            .passportIssueBranch("556-876")
            .maritalStatus(MaritalStatus.DIVORCED)
            .dependentAmount(1)
            .employment(employmentDTO3)
            .account("account")
            .isInsuranceEnabled(false)
            .isSalaryClient(true)
            .build();
}
