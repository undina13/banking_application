package com.undina.deal.mapper;

import com.undina.deal.entity.StatusHistory;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.openapitools.model.StatusHistoryDTO;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StatusHistoryMapper {
    StatusHistoryDTO toStatusHistoryDTO(StatusHistory statusHistory);
}


