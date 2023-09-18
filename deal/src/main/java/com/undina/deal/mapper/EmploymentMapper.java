package com.undina.deal.mapper;

import com.undina.deal.entity.Employment;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.EmploymentDTO;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EmploymentMapper {
    @Mapping(target = "status", source = "employmentStatus")
    Employment toEmployment(EmploymentDTO employmentDTO);

    @Mapping(target = "employmentStatus", source = "status")
    EmploymentDTO toEmploymentDTO(Employment employment);
}


