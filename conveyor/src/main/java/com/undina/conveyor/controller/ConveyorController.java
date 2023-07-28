package com.undina.conveyor.controller;

import com.undina.conveyor.service.ConveyorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.CreditDTO;
import org.openapitools.model.LoanApplicationRequestDTO;
import org.openapitools.model.LoanOfferDTO;
import org.openapitools.model.ScoringDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/conveyor")
@RequiredArgsConstructor
@Validated
@Tag(name = "ConveyorController", description = "Контроллер ConveyorController")
public class ConveyorController {
    private final ConveyorService conveyorService;

    @PostMapping("/offers")
    public ResponseEntity<List<LoanOfferDTO>> getLoanOffers(@RequestBody @Valid LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.info("getLoanOffers: amount - {}, email - {}, term - {} , birthdate - {}",
                loanApplicationRequestDTO.getAmount(), loanApplicationRequestDTO.getEmail(),
                loanApplicationRequestDTO.getTerm(), loanApplicationRequestDTO.getBirthdate());
        List<LoanOfferDTO> loanOfferDTOS = conveyorService.getLoanOffers(loanApplicationRequestDTO);
        log.info("getLoanOffers result: " + loanOfferDTOS);
        return ResponseEntity.ok()
                .body(loanOfferDTOS);
    }

    @PostMapping("/calculation")
    public ResponseEntity<CreditDTO> getCalculation(@RequestBody @Valid ScoringDataDTO scoringDataDTO) {
        log.info("getCalculation scoringDataDTO : amount={} , term={}, gender={}, birthdate={}, maritalStatus={}" +
                        "dependentAmount={},isInsuranceEnabled={}, isSalaryClient={}, employmentStatus={}, " +
                        "salary={}, position={}, workExperienceTotal={}, workExperienceCurrent={}",
                scoringDataDTO.getAmount(), scoringDataDTO.getTerm(), scoringDataDTO.getGender(),
                scoringDataDTO.getBirthdate(), scoringDataDTO.getMaritalStatus(), scoringDataDTO.getDependentAmount(),
                scoringDataDTO.getIsInsuranceEnabled(), scoringDataDTO.getIsSalaryClient(),
                scoringDataDTO.getEmployment().getEmploymentStatus(), scoringDataDTO.getEmployment().getSalary(),
                scoringDataDTO.getEmployment().getPosition(), scoringDataDTO.getEmployment().getWorkExperienceTotal(),
                scoringDataDTO.getEmployment().getWorkExperienceCurrent());
        CreditDTO creditDTO = conveyorService.getCalculation(scoringDataDTO);
        log.info("getCalculation result: " + creditDTO);
        return ResponseEntity.ok()
                .body(creditDTO);
    }
}
