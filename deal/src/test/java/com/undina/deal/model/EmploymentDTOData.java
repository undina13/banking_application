package com.undina.deal.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.model.EmploymentDTO;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmploymentDTOData {
    public static EmploymentDTO employmentDTO = new EmploymentDTO()
            .employmentStatus(EmploymentDTO.EmploymentStatusEnum.SELF_EMPLOYED)
            .employerINN("567676686767")
            .salary(BigDecimal.valueOf(50000))
            .position(EmploymentDTO.PositionEnum.MANAGER)
            .workExperienceTotal(17)
            .workExperienceCurrent(5);


    public static EmploymentDTO employmentDTO2 = new EmploymentDTO()
            .employmentStatus(EmploymentDTO.EmploymentStatusEnum.UNEMPLOYED)
            .employerINN("567676686767")
            .salary(BigDecimal.valueOf(50000))
            .position(EmploymentDTO.PositionEnum.MANAGER)
            .workExperienceTotal(17)
            .workExperienceCurrent(5);

    public static EmploymentDTO employmentDTO3 = new EmploymentDTO()
            .employmentStatus(EmploymentDTO.EmploymentStatusEnum.BUSINESS_OWNER)
            .employerINN("567676686767")
            .salary(BigDecimal.valueOf(80000))
            .position(EmploymentDTO.PositionEnum.TOP_MANAGER)
            .workExperienceTotal(100)
            .workExperienceCurrent(50);
}
