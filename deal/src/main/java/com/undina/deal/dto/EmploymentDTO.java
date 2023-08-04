package com.undina.deal.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
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

    @Override
    public String toString() {
        return "EmploymentDTO{" +
                "employmentStatus=" + employmentStatus +
                ", salary=" + salary +
                ", position=" + position +
                ", workExperienceTotal=" + workExperienceTotal +
                ", workExperienceCurrent=" + workExperienceCurrent +
                '}';
    }
}

