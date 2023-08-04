package com.undina.deal.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StatusHistory {
    private String status;

    private LocalDate time;

    private ChangeType changeType;
}
