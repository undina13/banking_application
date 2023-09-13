package com.undina.gateway.controller;


import com.undina.gateway.entity.LoginRequest;
import com.undina.gateway.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@Slf4j
@RequestMapping(value = UserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class UserController {

    static final String REST_URL = "/users";
    private final UserService userService;



    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(HttpServletRequest request, @RequestBody LoginRequest loginRequest, Errors errors) {
        log.info("authenticate {}", loginRequest);
        if (errors.hasErrors()) {
            log.info("Validation error with request: " + request.getRequestURI());
          //  return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }
        return ResponseEntity.ok(userService.login(loginRequest));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(HttpServletRequest request, @RequestBody  LoginRequest loginRequest, Errors errors) {
        log.info("register {}", loginRequest);
        if (errors.hasErrors()) {
            log.info("Validation error with request: " + request.getRequestURI());
      //      return ResponseEntity.unprocessableEntity().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }
        return new ResponseEntity<>(userService.signup(loginRequest), HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<?> me() {
        return ResponseEntity.ok(userService.me());
    }

}
