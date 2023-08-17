package com.undina.application.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.model.LoanApplicationRequestDTO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModelFormatter {

    public static String toLogFormat(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        return String.format("LoanApplicationRequestDTO : amount - %s, email - %s, term - %d , birthdate - %s",
                loanApplicationRequestDTO.getAmount(),
                loanApplicationRequestDTO.getEmail(),
                loanApplicationRequestDTO.getTerm(), loanApplicationRequestDTO.getBirthdate());

    }
}
