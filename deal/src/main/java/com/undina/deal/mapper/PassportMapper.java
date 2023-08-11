package com.undina.deal.mapper;

import com.undina.deal.entity.Passport;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.LoanApplicationRequestDTO;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PassportMapper {


    @Mapping(target = "series", source = "passportSeries")
    @Mapping(target = "number", source = "passportNumber")
    Passport toPassword(LoanApplicationRequestDTO loanApplicationRequestDTO);
}
