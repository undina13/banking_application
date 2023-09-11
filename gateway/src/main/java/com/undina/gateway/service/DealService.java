package com.undina.gateway.service;

import com.undina.gateway.exception.FeignGatewayException;
import com.undina.gateway.feign.DealFeignClient;
import com.undina.gateway.util.ModelFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.FinishRegistrationRequestDTO;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DealService {
    private final DealFeignClient dealFeignClient;

    public void codeDocuments(Long applicationId, Integer sesCode) {
        log.info("codeDocuments - start: applicationId = {}", applicationId);
        dealFeignClient.codeDocuments(applicationId, sesCode);
        log.info("codeDocuments - end");
    }

    public void signDocuments(Long applicationId) {
        log.info("signDocuments - start: applicationId = {}", applicationId);
        dealFeignClient.signDocuments(applicationId);
        log.info("signDocuments - end");
    }

    public void sendDocuments(Long applicationId) {
        log.info("sendDocuments - start: applicationId = {}", applicationId);
        dealFeignClient.sendDocuments(applicationId);
        log.info("sendDocuments - end");
    }

    public void calculateCredit(Long applicationId, FinishRegistrationRequestDTO finishRegistrationRequestDTO) {
        log.info("getCalculation - start: applicationId = {}, finishRegistrationRequestDTO = {}", applicationId,
                ModelFormatter.toLogFormat(finishRegistrationRequestDTO));
        try {
            dealFeignClient.getCalculation(applicationId, finishRegistrationRequestDTO);
        } catch (Exception e) {
            log.info("calculateCredit - exception:    {}", e.getMessage());
            if (e.getMessage().contains("Вам отказано в кредите")) {
                throw new FeignGatewayException("Вам отказано в кредите");
            }
            throw new FeignGatewayException(e.getMessage());
        }
        dealFeignClient.getCalculation(applicationId, finishRegistrationRequestDTO);
        log.info("getCalculation - end");
    }
}
