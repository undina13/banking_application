package com.undina.gateway.controller;


import com.undina.gateway.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.GatewayUserControllerApi;
import org.openapitools.model.JWTToken;
import org.openapitools.model.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController implements GatewayUserControllerApi {

    private final UserService userService;

    @Override
    public ResponseEntity<JWTToken> authenticateUser(@RequestBody LoginRequest loginRequest) {
        log.info("authenticateUser - start: LoginRequest = {}", loginRequest);
        JWTToken jwtToken = userService.login(loginRequest);
        log.info("authenticateUser - result: jwtToken = {}", jwtToken);
        return ResponseEntity.ok(jwtToken);
    }

    @Override
    public ResponseEntity<JWTToken> registerUser(@RequestBody LoginRequest loginRequest) {
        log.info("registerUser - start: LoginRequest = {}", loginRequest);
        JWTToken jwtToken = userService.signup(loginRequest);
        log.info("registerUser - result: jwtToken = {}", jwtToken);
        return ResponseEntity.ok(jwtToken);
    }
}
