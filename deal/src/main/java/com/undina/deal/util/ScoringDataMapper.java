package com.undina.deal.util;

import com.undina.deal.model.Client;
import com.undina.deal.model.ScoringDataDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.FinishRegistrationRequestDTO;
import org.openapitools.model.LoanOfferDTO;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ScoringDataMapper {
    @Mapping(target = "amount", source = "loanOfferDTO.totalAmount")
    @Mapping(target = "term", source = "loanOfferDTO.term")

    @Mapping(target = "gender", source = "finishRegistrationRequestDTO.gender")
    @Mapping(target = "birthdate", source = "client.date")
    @Mapping(target = "passportSeries", source = "client.passport.series")
    @Mapping(target = "passportNumber", source = "client.passport.number")
    @Mapping(target = "maritalStatus", source = "finishRegistrationRequestDTO.maritalStatus")
    @Mapping(target = "dependentAmount", source = "finishRegistrationRequestDTO.dependentAmount")
    @Mapping(target = "employment", source = "finishRegistrationRequestDTO.employment")
    @Mapping(target = "account", source = "finishRegistrationRequestDTO.account")
    @Mapping(target = "isInsuranceEnabled", source = "loanOfferDTO.isInsuranceEnabled")
    @Mapping(target = "isSalaryClient", source = "loanOfferDTO.isSalaryClient")
    @Mapping(target = "firstName", source = "client.firstName")
    @Mapping(target = "lastName", source = "client.lastName")
    @Mapping(target = "middleName", source = "client.middleName")
    ScoringDataDTO toScoringDataDTO(FinishRegistrationRequestDTO finishRegistrationRequestDTO, Client client,
                                    LoanOfferDTO loanOfferDTO);
}
