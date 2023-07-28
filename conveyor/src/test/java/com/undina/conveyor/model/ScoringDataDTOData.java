package com.undina.conveyor.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.client.model.ScoringDataDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.undina.conveyor.model.EmploymentDTOData.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScoringDataDTOData {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static ScoringDataDTO scoringDataDTO1 = new ScoringDataDTO()
            .term(6)
            .amount(BigDecimal.valueOf(10000))
            .firstName("Ivan")
            .lastName("Ivanov")
            .middleName("Ivanovich")
            .gender(ScoringDataDTO.GenderEnum.MALE)
            .birthdate(LocalDate.parse("2000-07-13", formatter))
            .passportSeries("4444")
            .passportNumber("666666")
            .passportIssueDate(LocalDate.parse("2022-07-13", formatter))
            .passportIssueBranch("556-876")
            .maritalStatus(ScoringDataDTO.MaritalStatusEnum.MARRIED)
            .dependentAmount(2)
            .employment(employmentDTO)
            .account("account")
            .isInsuranceEnabled(false)
            .isSalaryClient(false);


    public static ScoringDataDTO scoringDataDTOUnemployed = new ScoringDataDTO()
            .term(6)
            .amount(BigDecimal.valueOf(10000))
            .firstName("Ivan")
            .lastName("Ivanov")
            .middleName("Ivanovich")
            .gender(ScoringDataDTO.GenderEnum.MALE)
            .birthdate(LocalDate.parse("2000-07-13", formatter))
            .passportSeries("4444")
            .passportNumber("666666")
            .passportIssueDate(LocalDate.parse("2022-07-13", formatter))
            .passportIssueBranch("556-876")
            .maritalStatus(ScoringDataDTO.MaritalStatusEnum.MARRIED)
            .dependentAmount(2)
            .employment(employmentDTO2)
            .account("account")
            .isInsuranceEnabled(false)
            .isSalaryClient(false);

    public static ScoringDataDTO scoringDataDTO2 = new ScoringDataDTO()
            .term(12)
            .amount(BigDecimal.valueOf(100000))
            .firstName("Anna")
            .lastName("Petrova")
            .middleName("Ivanovna")
            .gender(ScoringDataDTO.GenderEnum.FEMALE)
            .birthdate(LocalDate.parse("1983-05-13", formatter))
            .passportSeries("4444")
            .passportNumber("666666")
            .passportIssueDate(LocalDate.parse("2022-07-13", formatter))
            .passportIssueBranch("556-876")
            .maritalStatus(ScoringDataDTO.MaritalStatusEnum.DIVORCED)
            .dependentAmount(1)
            .employment(employmentDTO3)
            .account("account")
            .isInsuranceEnabled(false)
            .isSalaryClient(true);

    public static ScoringDataDTO scoringDataDTOOldAge = new ScoringDataDTO()
            .term(12)
            .amount(BigDecimal.valueOf(100000))
            .firstName("Anna")
            .lastName("Petrova")
            .middleName("Ivanovna")
            .gender(ScoringDataDTO.GenderEnum.FEMALE)
            .birthdate(LocalDate.parse("1913-05-13", formatter))
            .passportSeries("4444")
            .passportNumber("666666")
            .passportIssueDate(LocalDate.parse("2022-07-13", formatter))
            .passportIssueBranch("556-876")
            .maritalStatus(ScoringDataDTO.MaritalStatusEnum.DIVORCED)
            .dependentAmount(1)
            .employment(employmentDTO3)
            .account("account")
            .isInsuranceEnabled(false)
            .isSalaryClient(true);
}
