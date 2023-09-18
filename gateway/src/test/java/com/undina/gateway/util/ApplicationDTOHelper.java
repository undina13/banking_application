package com.undina.gateway.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.model.ApplicationDTO;
import org.openapitools.model.ClientDTO;
import org.openapitools.model.PassportDTO;
import org.openapitools.model.StatusHistoryDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.undina.gateway.util.CreditDTOHelper.creditDTO;
import static com.undina.gateway.util.EmploymentDTOHelper.employmentDTO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationDTOHelper {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static ApplicationDTO applicationDTO = new ApplicationDTO()
            .applicationId(1L)
            .client(new ClientDTO()
                    .clientId(1L)
                    .account("account")
                    .date(LocalDate.parse("2002-07-13", formatter))
                    .dependentAmount(10000)
                    .email("fgh@mail.ru")
                    .gender(ClientDTO.GenderEnum.MALE)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .middleName("Ivanovich")
                    .passport(new PassportDTO()
                            .number("123456")
                            .issueBranch("555-666")
                            .issueDate("2002-07-13")
                            .series("1234"))
                    .employment(employmentDTO))
            .status("CC_APPROVED")
            .statusHistory(List.of(new StatusHistoryDTO()
                    .status("CC_APPROVED")
                            .time(LocalDateTime.now().minusMinutes(15).toString())
                    .changeType(StatusHistoryDTO.ChangeTypeEnum.AUTOMATIC)))
            .credit(creditDTO);

    public static List<ApplicationDTO> applicationDTOList = List.of(applicationDTO);
}
