package com.undina.deal.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ScoringDataDTO {
    @Min(10000)
    @NotNull
    private final BigDecimal amount;

    @Min(6)
    @NotNull
    private final Integer term;

    @Pattern(regexp = "^[a-zA-Z]{2,30}$")
    @NotBlank
    private final String firstName;

    @Pattern(regexp = "^[a-zA-Z]{2,30}$")
    @NotBlank
    private final String lastName;

    @Pattern(regexp = "^[a-zA-Z]{2,30}$")
    private final String middleName;

    @NotNull
    private final Gender gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private final LocalDate birthdate;

    @Pattern(regexp = "^[0-9]{4}$")
    @NotBlank
    private final String passportSeries;

    @Pattern(regexp = "^[0-9]{6}$")
    @NotBlank
    private final String passportNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private final LocalDate passportIssueDate;

    @NotBlank
    private final String passportIssueBranch;

    @NotNull
    private final MaritalStatus maritalStatus;

    @NotNull
    private Integer dependentAmount;

    @NotNull
    @Valid
    private final EmploymentDTO employment;

    @NotBlank
    private final String account;

    @NotNull
    private final Boolean isInsuranceEnabled;

    @NotNull
    private final Boolean isSalaryClient;


    @Override
    public String toString() {
        return "ScoringDataDTO{" +
                "amount=" + amount +
                ", term=" + term +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", gender=" + gender +
                ", birthdate=" + birthdate +
                ", passportSeries='" + passportSeries + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", passportIssueDate=" + passportIssueDate +
                ", passportIssueBranch='" + passportIssueBranch + '\'' +
                ", maritalStatus=" + maritalStatus +
                ", dependentAmount=" + dependentAmount +
                ", employment=" + employment +
                ", account='" + account + '\'' +
                ", isInsuranceEnabled=" + isInsuranceEnabled +
                ", isSalaryClient=" + isSalaryClient +
                '}';
    }
}