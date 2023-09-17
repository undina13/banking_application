package com.undina.gateway.controller;

import com.undina.gateway.service.DealService;
import com.undina.gateway.util.ModelFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.GatewayDealControllerApi;
import org.openapitools.model.FinishRegistrationRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DealController implements GatewayDealControllerApi {

    private final DealService dealService;

    @Override
    public ResponseEntity<Void> getCalculation(Long applicationId, FinishRegistrationRequestDTO finishRegistrationRequestDTO) {
        log.info("getCalculation - start: applicationId = {}, finishRegistrationRequestDTO = {}", applicationId,
                ModelFormatter.toLogFormat(finishRegistrationRequestDTO));
        dealService.calculateCredit(applicationId, finishRegistrationRequestDTO);
        log.info("getCalculation - end");
        return ResponseEntity.ok().build();
    }


}
