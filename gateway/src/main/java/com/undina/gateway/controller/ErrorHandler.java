package com.undina.gateway.controller;


import com.undina.gateway.exception.ErrorResponse;

import feign.FeignException;
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
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> feignException(final FeignException e) {
        log.error("feignException - error: message = {}", e.getMessage());
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse(e.getMessage()));
    }
}
