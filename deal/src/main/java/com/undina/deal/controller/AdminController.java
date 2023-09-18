package com.undina.deal.controller;

import com.undina.deal.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.AdminControllerApi;
import org.openapitools.model.ApplicationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminController implements AdminControllerApi {
    private  final AdminService adminService;

    @Override
    public ResponseEntity<List<ApplicationDTO>> getAllApplications() {
        log.info("getAllApplications - start");
        List<ApplicationDTO> applicationDTOList = adminService.getApplications();
        log.info("getAllApplications - result: applicationDTOList = {}", applicationDTOList);
        return ResponseEntity.ok(applicationDTOList);
    }

    @Override
    public ResponseEntity<ApplicationDTO> getApplication(Long applicationId) {
        log.info("getApplication - start: applicationId = {}", applicationId);
        ApplicationDTO applicationDTO = adminService.getApplication(applicationId);
        log.info("getApplication - result: applicationDTO = {}", applicationDTO);
        return ResponseEntity.ok(applicationDTO);
    }

}
