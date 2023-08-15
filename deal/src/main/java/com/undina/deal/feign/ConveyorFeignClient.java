package com.undina.deal.feign;


import org.openapitools.model.CreditDTO;
import org.openapitools.model.ScoringDataDTO;
import org.openapitools.model.LoanApplicationRequestDTO;
import org.openapitools.model.LoanOfferDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "${feign.conveyor.url}", name = "FEIGN-CLIENT")
public interface ConveyorFeignClient {
    @PostMapping("/offers")
    ResponseEntity<List<LoanOfferDTO>> getOffers(@RequestBody LoanApplicationRequestDTO request);

    @PostMapping("/calculation")
    ResponseEntity<CreditDTO> calculateCredit(@RequestBody ScoringDataDTO scoringData);
}
