package com.undina.conveyor.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmploymentDTOData {
    public static EmploymentDTO employmentDTO = EmploymentDTO
            .builder()
            .employmentStatus(EmploymentStatus.SELF_EMPLOYED)
            .employerINN("567676686767")
            .salary(BigDecimal.valueOf(50000))
            .position(Position.MANAGER)
            .workExperienceTotal(17)
            .workExperienceCurrent(5)
            .build();

    public static EmploymentDTO employmentDTO2 = EmploymentDTO
            .builder()
            .employmentStatus(EmploymentStatus.UNEMPLOYED)
            .employerINN("567676686767")
            .salary(BigDecimal.valueOf(50000))
            .position(Position.MANAGER)
            .workExperienceTotal(17)
            .workExperienceCurrent(5)
            .build();

    public static EmploymentDTO employmentDTO3 = EmploymentDTO
            .builder()
            .employmentStatus(EmploymentStatus.BUSINESS_OWNER)
            .employerINN("567676686767")
            .salary(BigDecimal.valueOf(80000))
            .position(Position.TOP_MANAGER)
            .workExperienceTotal(100)
            .workExperienceCurrent(50)
            .build();
}
