package com.undina.deal.mapper;

import com.undina.deal.entity.Employment;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.openapitools.model.EmploymentDTO;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EmploymentMapper {
    Employment toEmployment(EmploymentDTO employmentDTO);
}


