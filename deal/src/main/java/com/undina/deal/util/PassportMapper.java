package com.undina.deal.util;

import com.undina.deal.dto.LoanApplicationRequestDTO;
import com.undina.deal.dto.Passport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring" )
public interface PassportMapper {
//    @Mapping(target = "date", source = "birthdate")
//    Client toClient(LoanApplicationRequestDTO loanApplicationRequestDTO);

    @Mappings({
            @Mapping(target = "series", source = "passportSeries"),
            @Mapping(target = "number", source = "passportNumber")
    })
    Passport toPassword(LoanApplicationRequestDTO loanApplicationRequestDTO);
}
