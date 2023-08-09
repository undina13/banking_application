package com.undina.deal.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.undina.deal.model.EmploymentDTOData.employmentDTO;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScoringDataDTOData {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static ScoringDataDTO scoringDataDTO1 = new ScoringDataDTO(BigDecimal.valueOf(10120),
            12, "Sidor", "Sidorov", "Petrovich", Gender.MALE,
            LocalDate.parse("2005-08-09", formatter), "9999", "623456",
            LocalDate.parse("2022-07-13", formatter), "556-876", MaritalStatus.MARRIED, 2,
            employmentDTO, "account", true, true);
}
