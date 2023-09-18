package com.undina.deal.mapper;

import com.undina.deal.entity.Passport;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.openapitools.model.PassportDTO;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PassportMapper {
    PassportDTO toPassportDTO(Passport passport);
}


