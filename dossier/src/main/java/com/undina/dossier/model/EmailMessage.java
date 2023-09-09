package com.undina.dossier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailMessage {
    private String address;

    private Theme theme;

    private Long applicationId;

    private String text;
}
