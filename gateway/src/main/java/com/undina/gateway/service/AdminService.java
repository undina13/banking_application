package com.undina.gateway.service;

import com.undina.gateway.feign.DealFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.ApplicationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final DealFeignClient dealFeignClient;

    public List<ApplicationDTO> getApplications() {
        log.info("getApplications - start");
        List<ApplicationDTO> applicationDTOList = dealFeignClient.getAllApplications().getBody();
        log.info("getAllApplications - result: applicationDTOList = {}", applicationDTOList);
        return applicationDTOList;
    }

    public ApplicationDTO getApplication(Long applicationId) {
        log.info("getApplication - start: applicationId = {}", applicationId);
        ApplicationDTO applicationDTO = dealFeignClient.getApplication(applicationId).getBody();
        log.info("getApplication - result: applicationDTO = {}", applicationDTO);
        return applicationDTO;
    }
}
