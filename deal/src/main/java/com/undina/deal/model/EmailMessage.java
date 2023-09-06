package com.undina.deal.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class EmailMessage {
    private String address;

    private Theme theme;

    private Long applicationId;

    private String text;
}
