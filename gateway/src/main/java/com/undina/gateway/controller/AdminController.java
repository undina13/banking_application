package com.undina.gateway.controller;

import com.undina.gateway.security.SecurityUser;
import com.undina.gateway.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.GatewayAdminControllerApi;
import org.openapitools.model.ApplicationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminController implements GatewayAdminControllerApi {
    private  final AdminService adminService;

    @Override
    public ResponseEntity<List<ApplicationDTO>> getAllApplications() {
        log.info("getAllApplications - start");
        getAuth();
        List<ApplicationDTO> applicationDTOList = adminService.getApplications();
        log.info("getAllApplications - result: applicationDTOList = {}", applicationDTOList);
        return ResponseEntity.ok(applicationDTOList);
    }

    @Override
    public ResponseEntity<ApplicationDTO> getApplication(Long applicationId) {
        log.info("getApplication - start: applicationId = {}", applicationId);
        getAuth();
        ApplicationDTO applicationDTO = adminService.getApplication(applicationId);
        log.info("getApplication - result: applicationDTO = {}", applicationDTO);
        return ResponseEntity.ok(applicationDTO);
    }

    private boolean getAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof SecurityUser) {
                SecurityUser user = (SecurityUser) principal;

                if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                    return true;
                }
            }
        }
        throw new SecurityException("you not admin");
    }
}
