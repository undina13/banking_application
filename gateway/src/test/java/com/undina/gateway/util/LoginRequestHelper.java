package com.undina.gateway.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.model.LoginRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequestHelper {
    public static LoginRequest loginSignupRequest = new LoginRequest("user123@bk.ru", "qwerty");
    public static LoginRequest loginRequest = new LoginRequest("user@mail.ru", "password");
}
