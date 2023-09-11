package com.undina.deal.mapper;

import com.undina.deal.entity.Credit;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.openapitools.model.CreditDTO;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CreditMapper {
    Credit toCredit(CreditDTO creditDTO);

    CreditDTO toCreditDTO(Credit credit);
}


