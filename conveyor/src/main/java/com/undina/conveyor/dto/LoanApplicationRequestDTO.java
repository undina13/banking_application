package com.undina.conveyor.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import util.ValidateDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class LoanApplicationRequestDTO {
    @Min(10000)
    @NotNull
    private final BigDecimal amount;

    @Min(6)
    @NotNull
    private final Integer term;

    @Pattern(regexp = "^[a-zA-Z]{2,30}$")
    @NotBlank
    private final String firstName;

    @Pattern(regexp = "^[a-zA-Z]{2,30}$")
    @NotBlank
    private final String lastName;

    @Pattern(regexp = "^[a-zA-Z]{2,30}$")
    private final String middleName;

    @Pattern(regexp = "^[\\w\\.]{2,50}@[\\w\\.]{2,20}$")
    @NotBlank
    private final String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ValidateDate
    @NotNull
    private final LocalDate birthdate;

    @Pattern(regexp = "^[0-9]{4}$")
    @NotBlank
    private final String passportSeries;

    @Pattern(regexp = "^[0-9]{6}$")
    @NotBlank
    private final String passportNumber;
}
