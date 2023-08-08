package com.undina.deal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class LoanApplicationRequestDTO {
    @Min(10000)
    @NotNull
    private BigDecimal amount;

    @Min(6)
    @NotNull
    private Integer term;

    @Pattern(regexp = "^[a-zA-Z]{2,30}$")
    @NotBlank
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]{2,30}$")
    @NotBlank
    private String lastName;

    @Pattern(regexp = "^[a-zA-Z]{2,30}$")
    private String middleName;

    @Pattern(regexp = "^[\\w\\.]{2,50}@[\\w\\.]{2,20}$")
    @NotBlank
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate birthdate;

    @Pattern(regexp = "^[0-9]{4}$")
    @NotBlank
    private String passportSeries;

    @Pattern(regexp = "^[0-9]{6}$")
    @NotBlank
    private String passportNumber;

    @Override
    public String toString() {
        return "LoanApplicationRequestDTO{" +
                "amount=" + amount +
                ", term=" + term +
                ", email='" + email + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
