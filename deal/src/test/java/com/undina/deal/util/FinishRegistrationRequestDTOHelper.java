package com.undina.deal.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.model.FinishRegistrationRequestDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.undina.deal.util.EmploymentDTOHelper.employmentDTO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FinishRegistrationRequestDTOHelper {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static FinishRegistrationRequestDTO finishRegistrationRequest = new FinishRegistrationRequestDTO()
            .gender(FinishRegistrationRequestDTO.GenderEnum.MALE)
            .maritalStatus(FinishRegistrationRequestDTO.MaritalStatusEnum.MARRIED)
            .dependentAmount(2)
            .passportIssueDate(LocalDate.parse("2022-07-13", formatter))
            .passportIssueBranch("556-876")
            .employment(employmentDTO)
            .account("account");
}
