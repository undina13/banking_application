package com.undina.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserTo {
    private String email;
    private String id;
}
