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

    @Override
    public ResponseEntity<Void> codeDocuments(Long applicationId, Integer sesCode) {
        log.info("codeDocuments - start: applicationId = {}", applicationId);
        dealService.codeDocuments(applicationId, sesCode);
        log.info("codeDocuments - end");
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> sendDocuments(Long applicationId) {
        log.info("sendDocuments - start: applicationId = {}", applicationId);
        dealService.sendDocuments(applicationId);
        log.info("sendDocuments - end");
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> signDocuments(Long applicationId) {
        log.info("signDocuments - start: applicationId = {}", applicationId);
        dealService.signDocuments(applicationId);
        log.info("signDocuments - end");
        return ResponseEntity.ok().build();
    }
}
