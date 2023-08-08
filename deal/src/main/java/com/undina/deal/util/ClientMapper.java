package com.undina.deal.util;

import com.undina.deal.dto.LoanApplicationRequestDTO;
import com.undina.deal.dto.Passport;
import com.undina.deal.model.Client;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = PassportMapper.class)
public interface ClientMapper {
    @Mapping(target = "date", source = "birthdate")
    Client toClient1(LoanApplicationRequestDTO loanApplicationRequestDTO);

    default Client toClient(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        Client client = toClient1(loanApplicationRequestDTO);
        client.setPassport(toPassword(loanApplicationRequestDTO));
        return client;
    }

    @Mappings({
            @Mapping(target = "series", source = "passportSeries"),
            @Mapping(target = "number", source = "passportNumber")
    })
    Passport toPassword(LoanApplicationRequestDTO loanApplicationRequestDTO);
}
