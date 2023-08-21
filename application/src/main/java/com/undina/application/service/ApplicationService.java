package com.undina.application.service;

import com.undina.application.feign.DealFeignClient;
import com.undina.application.util.ModelFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.LoanApplicationRequestDTO;
import org.openapitools.model.LoanOfferDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplicationService {
    private final DealFeignClient dealFeignClient;

    public List<LoanOfferDTO> getLoanOffers(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.info("getLoanOffers - start: {}", ModelFormatter.toLogFormat(loanApplicationRequestDTO));
        List<LoanOfferDTO> loanOfferDTOs = dealFeignClient.getOffers(loanApplicationRequestDTO).getBody();
        log.info("getLoanOffers - result: {}", loanOfferDTOs);
        return loanOfferDTOs;
    }

    public void getCalculation(LoanOfferDTO loanOfferDTO) {
        log.info("getCalculation - start: {}", loanOfferDTO);
        dealFeignClient.applyLoanOffer(loanOfferDTO);
        log.info("getCalculation - end");
    }
}
