package com.undina.conveyor.model;

import java.math.BigDecimal;

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
}
