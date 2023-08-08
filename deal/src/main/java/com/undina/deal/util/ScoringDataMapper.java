package com.undina.deal.util;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ScoringDataMapper {
  //  @Mapping(target = "date", source = "birthdate")
  //  ScoringDataDTO toScoringDataDTO(FinishRegistrationRequestDTO finishRegistrationRequestDTO, Client client);
}
