package com.undina.conveyor.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class EmploymentDTO {
    @NotNull
    private final EmploymentStatus employmentStatus;

    @NotBlank
    private final String employerINN;

    @NotNull
    private final BigDecimal salary;

    @NotNull
    private final Position position;

    @NotNull
    private final Integer workExperienceTotal;

    @NotNull
    private final Integer workExperienceCurrent;
}
