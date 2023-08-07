package com.undina.deal.util;

import com.undina.deal.dto.LoanApplicationRequestDTO;
import com.undina.deal.model.Client;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClientMapper {
    @Mapping(target = "date", source = "birthdate")
    Client toClient(LoanApplicationRequestDTO loanApplicationRequestDTO);
}
