package com.undina.deal.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.model.EmploymentDTO;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmploymentDTOData {
    public static EmploymentDTO employmentDTO = new EmploymentDTO()
            .employmentStatus(EmploymentDTO.EmploymentStatusEnum.BUSINESS_OWNER)
            .employerINN("567676686767")
            .salary(BigDecimal.valueOf(50000))
            .position(EmploymentDTO.PositionEnum.MANAGER)
            .workExperienceTotal(17)
            .workExperienceCurrent(5);
}
