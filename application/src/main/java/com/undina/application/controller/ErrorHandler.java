package com.undina.application.controller;

import com.undina.application.exception.ErrorResponse;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationException(final MethodArgumentNotValidException e) {
        log.error("validationException " + e.getParameter());
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(e.getMessage()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> feignException(final FeignException e) {
        log.error("feignException - error: message = {}", e.getMessage());
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse("feignException - error: message = " + e.getMessage()));
    }
}
