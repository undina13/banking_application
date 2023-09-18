package com.undina.gateway.service;

import com.undina.gateway.feign.ApplicationFeignClient;
import com.undina.gateway.util.ModelFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.LoanApplicationRequestDTO;
import org.openapitools.model.LoanOfferDTO;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationFeignClient applicationFeignClient;

    public void applyLoanOffer(LoanOfferDTO loanOfferDTO) {
        log.info("applyLoanOffer - start: loanOfferDTO = {}", loanOfferDTO);
        applicationFeignClient.sendLoanOffer(loanOfferDTO);
        log.info("applyLoanOffer - end");
    }

    public void createApplication(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.info("createApplication - start: loanApplicationRequestDTO = {}",
                ModelFormatter.toLogFormat(loanApplicationRequestDTO));
        applicationFeignClient.createApplication(loanApplicationRequestDTO);
        log.info("createApplication - end");
    }
}
