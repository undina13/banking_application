package com.undina.deal.util;

import com.undina.deal.dto.ScoringDataDTO;
import com.undina.deal.enums.Gender;
import com.undina.deal.enums.MaritalStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.undina.deal.util.EmploymentDTOHelper.employmentDTO;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScoringDataDTOHelper {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static ScoringDataDTO scoringDataDTO1 = new ScoringDataDTO(BigDecimal.valueOf(10120).setScale(1),
            12, "Sidor", "Sidorov", "Petrovich", Gender.MALE,
            LocalDate.parse("2005-08-09", formatter), "9999", "623456",
            LocalDate.parse("2022-07-13", formatter), "556-876", MaritalStatus.MARRIED, 2,
            employmentDTO, "account", true, true);
}
