package com.undina.gateway.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.model.EmploymentDTO;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmploymentDTOHelper {
    public static EmploymentDTO employmentDTO = EmploymentDTO
            .builder()
            .employmentStatus(EmploymentDTO.EmploymentStatusEnum.BUSINESS_OWNER)
            .employerINN("567676686767")
            .salary(BigDecimal.valueOf(50000))
            .position(EmploymentDTO.PositionEnum.MANAGER)
            .workExperienceCurrent(5)
            .workExperienceTotal(17)
            .build();
}
