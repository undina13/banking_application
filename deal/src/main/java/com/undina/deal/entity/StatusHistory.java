package com.undina.deal.entity;

import com.undina.deal.enums.ApplicationStatus;
import com.undina.deal.enums.ChangeType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StatusHistory {
    private ApplicationStatus status;

    private LocalDateTime time;

    private ChangeType changeType;
}
