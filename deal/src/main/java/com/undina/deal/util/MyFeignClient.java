package com.undina.deal.util;

import com.undina.deal.dto.CreditDTO;
import com.undina.deal.dto.LoanApplicationRequestDTO;
import com.undina.deal.dto.LoanOfferDTO;
import com.undina.deal.dto.ScoringDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "${conveyor.url}", name = "FEIGN-CLIENT")
public interface MyFeignClient {
    @PostMapping("/offers")
    ResponseEntity<List<LoanOfferDTO>> getOffers(@RequestBody LoanApplicationRequestDTO request);

    @PostMapping("/calculation")
    ResponseEntity<CreditDTO> calculateCredit(@RequestBody ScoringDataDTO scoringData);
}
