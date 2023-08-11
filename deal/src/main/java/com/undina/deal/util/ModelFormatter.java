package com.undina.deal.util;

import com.undina.deal.dto.ScoringDataDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.model.FinishRegistrationRequestDTO;
import org.openapitools.model.LoanApplicationRequestDTO;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModelFormatter {

    public static String toLogFormat(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        return String.format("LoanApplicationRequestDTO : amount - %s, email - %s, term - %d , birthdate - %s",
                loanApplicationRequestDTO.getAmount(),
                loanApplicationRequestDTO.getEmail(),
                loanApplicationRequestDTO.getTerm(), loanApplicationRequestDTO.getBirthdate());

    }

    public static String toLogFormat(ScoringDataDTO scoringDataDTO) {
        return String.format("ScoringDataDTO : amount=%s , term=%d, gender=%s, birthdate=%s, maritalStatus=%s" +
                        "dependentAmount=%d,isInsuranceEnabled=%s, isSalaryClient=%s, employmentStatus=%s, " +
                        "salary=%s, position=%s, workExperienceTotal=%d, workExperienceCurrent=%d",
                scoringDataDTO.getAmount(), scoringDataDTO.getTerm(), scoringDataDTO.getGender(),
                scoringDataDTO.getBirthdate(), scoringDataDTO.getMaritalStatus(), scoringDataDTO.getDependentAmount(),
                scoringDataDTO.getIsInsuranceEnabled(), scoringDataDTO.getIsSalaryClient(),
                scoringDataDTO.getEmployment().getEmploymentStatus(), scoringDataDTO.getEmployment().getSalary(),
                scoringDataDTO.getEmployment().getPosition(), scoringDataDTO.getEmployment().getWorkExperienceTotal(),
                scoringDataDTO.getEmployment().getWorkExperienceCurrent());

    }

    public static String toLogFormat(FinishRegistrationRequestDTO finishRegistrationRequest) {
        return String.format("FinishRegistrationRequestDTO : gender=%s , maritalStatus=%s, dependentAmount=%d " +
                        "employmentStatus=%s,salary=%s, position=%s, workExperienceTotal=%d, workExperienceCurrent=%d" +
                        "account=%s", finishRegistrationRequest.getGender(), finishRegistrationRequest.getMaritalStatus(),
                finishRegistrationRequest.getDependentAmount(),
                finishRegistrationRequest.getEmployment().getEmploymentStatus(),
                finishRegistrationRequest.getEmployment().getSalary(),
                finishRegistrationRequest.getEmployment().getPosition(), finishRegistrationRequest.getEmployment()
                        .getWorkExperienceCurrent(), finishRegistrationRequest.getEmployment().getWorkExperienceTotal(),
                finishRegistrationRequest.getAccount());
    }
}
