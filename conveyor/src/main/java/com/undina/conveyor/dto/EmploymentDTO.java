package com.undina.conveyor.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmploymentDTO {
    private final EmploymentStatus employmentStatus;
    private final String employerINN;
    private final BigDecimal salary;
    private final Position position;
    private final Integer workExperienceTotal;
    private final Integer workExperienceCurrent;
}
