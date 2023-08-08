package com.undina.deal.util;

import com.undina.deal.model.Client;
import com.undina.deal.model.Passport;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.openapitools.model.LoanApplicationRequestDTO;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
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
