package com.undina.deal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class FinishRegistrationRequestDTO {
    private Gender gender;

    private MaritalStatus maritalStatus;

    private Integer dependentAmount;

    private LocalDate passportIssueDate;

    private String passportIssueBranch;

    private EmploymentDTO employment;

    private String account;
}
