package com.undina.deal.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Passport implements Serializable {

    private Long passportId;

    private String series;

    private String number;

    private String issueBranch;

    private LocalDate issueDate;
}
