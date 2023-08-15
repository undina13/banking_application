package com.undina.deal.mapper;

import com.undina.deal.entity.Client;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.LoanApplicationRequestDTO;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClientMapper {
    @Mapping(target = "date", source = "birthdate")
    @Mapping(target = "passport.series", source = "passportSeries")
    @Mapping(target = "passport.number", source = "passportNumber")
    Client toClient(LoanApplicationRequestDTO loanApplicationRequestDTO);
}


