package com.undina.gateway.feign;


import org.openapitools.model.LoanApplicationRequestDTO;
import org.openapitools.model.LoanOfferDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${feign.application.url}", name = "APPLICATION-FEIGN-CLIENT")
public interface ApplicationFeignClient {
    @PostMapping
    ResponseEntity<Void> createApplication(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO);

    @PutMapping("/offer")
    ResponseEntity<Void> sendLoanOffer(@RequestBody LoanOfferDTO loanOfferDTO);
}
