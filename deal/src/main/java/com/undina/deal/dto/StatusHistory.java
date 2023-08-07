package com.undina.deal.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StatusHistory {
    private ApplicationStatus status;

    private LocalDateTime time;

    private ChangeType changeType;
}
