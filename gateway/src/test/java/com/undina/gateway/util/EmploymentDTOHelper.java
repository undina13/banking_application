package com.undina.gateway.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.model.EmploymentDTO;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmploymentDTOHelper {
    public static EmploymentDTO employmentDTO = new EmploymentDTO(
            EmploymentDTO.EmploymentStatusEnum.BUSINESS_OWNER,
            "567676686767",
            BigDecimal.valueOf(50000),
            EmploymentDTO.PositionEnum.MANAGER,
            17,
            5);
}
