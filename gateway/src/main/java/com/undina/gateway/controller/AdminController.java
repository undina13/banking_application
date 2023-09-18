package com.undina.gateway.controller;

import com.undina.gateway.service.AdminService;
import com.undina.gateway.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.GatewayAdminControllerApi;
import org.openapitools.model.ApplicationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminController implements GatewayAdminControllerApi {
    private final AdminService adminService;

    private final SecurityService securityService;

    @Override
    public ResponseEntity<List<ApplicationDTO>> getAllApplications() {
        log.info("getAllApplications - start");
        securityService.getAuth();
        List<ApplicationDTO> applicationDTOList = adminService.getApplications();
        log.info("getAllApplications - result: applicationDTOList = {}", applicationDTOList);
        return ResponseEntity.ok(applicationDTOList);
    }

    @Override
    public ResponseEntity<ApplicationDTO> getApplication(Long applicationId) {
        log.info("getApplication - start: applicationId = {}", applicationId);
        securityService.getAuth();
        ApplicationDTO applicationDTO = adminService.getApplication(applicationId);
        log.info("getApplication - result: applicationDTO = {}", applicationDTO);
        return ResponseEntity.ok(applicationDTO);
    }
}
