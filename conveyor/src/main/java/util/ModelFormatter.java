package util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.model.LoanApplicationRequestDTO;
import org.openapitools.model.ScoringDataDTO;

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
}
