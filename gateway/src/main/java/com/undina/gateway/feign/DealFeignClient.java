package com.undina.gateway.feign;


import org.openapitools.model.ApplicationDTO;
import org.openapitools.model.FinishRegistrationRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "${feign.deal.url}", name = "DEAL-FEIGN-CLIENT")
public interface DealFeignClient {
    @PutMapping("/calculate/{applicationId}")
    ResponseEntity<Void> getCalculation(@PathVariable Long applicationId,
                                        @RequestBody FinishRegistrationRequestDTO finishRegistrationRequestDTO);
    @PostMapping("/document/{applicationId}/send")
    ResponseEntity<Void> sendDocuments(@PathVariable Long applicationId);

    @PostMapping("/document/{applicationId}/sign")
    ResponseEntity<Void> signDocuments(@PathVariable Long applicationId);

    @PostMapping("/document/{applicationId}/code")
    ResponseEntity<Void> codeDocuments(@PathVariable Long applicationId, @RequestBody Integer sesCode);

    @GetMapping("/admin/application")
    ResponseEntity<List<ApplicationDTO>> getAllApplications();

    @GetMapping("/admin/application/{applicationId}")
    ResponseEntity<ApplicationDTO> getApplication(@PathVariable Long applicationId) ;

}
