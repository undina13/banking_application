package com.undina.deal.mapper;

import com.undina.deal.entity.Application;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.openapitools.model.ApplicationDTO;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {ClientMapper.class, CreditMapper.class, StatusHistoryMapper.class})
public interface ApplicationMapper {
    Application toApplication(ApplicationDTO applicationDTO);

    ApplicationDTO toApplicationDTO(Application application);

   List<ApplicationDTO> toListApplicationDTO(List<Application> applicationList);
}


