package com.undina.conveyor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.CreditDTO;
import org.openapitools.model.LoanApplicationRequestDTO;
import org.openapitools.model.LoanOfferDTO;
import org.openapitools.model.ScoringDataDTO;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConveyorService {
    private final OfferService offerService;

    private final CreditService creditService;

    public List<LoanOfferDTO> getLoanOffers(@Valid LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.info("getLoanOffers: amount - {}, email - {}, term - {} , birthdate - {}",
                loanApplicationRequestDTO.getAmount(), loanApplicationRequestDTO.getEmail(),
                loanApplicationRequestDTO.getTerm(), loanApplicationRequestDTO.getBirthdate());
        List<LoanOfferDTO> loanOfferDTOS = offerService.generateOffers(loanApplicationRequestDTO);
        log.info("getLoanOffers result: " + loanOfferDTOS);
        return loanOfferDTOS;
    }

    public CreditDTO getCalculation(ScoringDataDTO scoringDataDTO) {
        log.info("getCalculation scoringDataDTO : amount={} , term={}, gender={}, birthdate={}, maritalStatus={}" +
                        "dependentAmount={},isInsuranceEnabled={}, isSalaryClient={}, employmentStatus={}, " +
                        "salary={}, position={}, workExperienceTotal={}, workExperienceCurrent={}",
                scoringDataDTO.getAmount(), scoringDataDTO.getTerm(), scoringDataDTO.getGender(),
                scoringDataDTO.getBirthdate(), scoringDataDTO.getMaritalStatus(), scoringDataDTO.getDependentAmount(),
                scoringDataDTO.getIsInsuranceEnabled(), scoringDataDTO.getIsSalaryClient(),
                scoringDataDTO.getEmployment().getEmploymentStatus(), scoringDataDTO.getEmployment().getSalary(),
                scoringDataDTO.getEmployment().getPosition(), scoringDataDTO.getEmployment().getWorkExperienceTotal(),
                scoringDataDTO.getEmployment().getWorkExperienceCurrent());
        CreditDTO creditDTO = creditService.getCalculation(scoringDataDTO);
        log.info("getCalculation result: " + creditDTO);
        return creditDTO;
    }
}
