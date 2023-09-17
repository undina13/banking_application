package com.undina.deal.mapper;

import com.undina.deal.entity.Client;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.openapitools.model.ClientDTO;
import org.openapitools.model.FinishRegistrationRequestDTO;
import org.openapitools.model.LoanApplicationRequestDTO;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {PassportMapper.class, EmploymentMapper.class, ClientMapper.class})
public interface ClientMapper {
    @Mapping(target = "date", source = "birthdate")
    @Mapping(target = "passport.series", source = "passportSeries")
    @Mapping(target = "passport.number", source = "passportNumber")
    Client toClient(LoanApplicationRequestDTO loanApplicationRequestDTO);

    ClientDTO toClientDTO(Client client);


        @Mapping(target = "gender", source = "finishRegistrationRequestDTO.gender")
    @Mapping(target = "maritalStatus", source = "finishRegistrationRequestDTO.maritalStatus")
    @Mapping(target = "dependentAmount", source = "finishRegistrationRequestDTO.dependentAmount")
    @Mapping(target = "employment", source = "finishRegistrationRequestDTO.employment")
    @Mapping(target = "account", source = "finishRegistrationRequestDTO.account")

          @Mapping(target = "passport.issueDate", source = "finishRegistrationRequestDTO.passportIssueDate")
       @Mapping(target = "passport.issueBranch", source = "finishRegistrationRequestDTO.passportIssueBranch")

        Client toClientFromFinishRegistrationRequestDTO(@MappingTarget Client client, FinishRegistrationRequestDTO finishRegistrationRequestDTO);
}


