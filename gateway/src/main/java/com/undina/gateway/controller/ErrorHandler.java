package com.undina.gateway.controller;


import com.undina.gateway.exception.ErrorResponse;
import com.undina.gateway.exception.FeignGatewayException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FeignGatewayException.class)
    public ResponseEntity<ErrorResponse> feignException(final FeignGatewayException e) {
        log.error("feignException - error: message = {}", e.getMessage());
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse(e.getMessage()));
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ErrorResponse> securityException(final SecurityException e) {
        log.error("feignException - error: message = {}", e.getMessage());
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse(e.getMessage()));
    }
}
