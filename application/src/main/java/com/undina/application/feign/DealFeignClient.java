package com.undina.application.feign;


import org.openapitools.model.LoanApplicationRequestDTO;
import org.openapitools.model.LoanOfferDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "${feign.deal.url}", name = "DEAL-FEIGN-CLIENT")
public interface DealFeignClient {
    @PostMapping("/application")
    ResponseEntity<List<LoanOfferDTO>> getOffers(@RequestBody LoanApplicationRequestDTO request);

    @PostMapping("/offer")
    ResponseEntity<Void> applyLoanOffer(@RequestBody LoanOfferDTO loanOffer);
}
