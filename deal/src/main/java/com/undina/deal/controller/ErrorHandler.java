package com.undina.deal.controller;


import com.undina.deal.exception.ErrorResponse;
import com.undina.deal.exception.NotFoundException;
import com.undina.deal.exception.RejectionException;
import com.undina.deal.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> validationException(ValidationException e) {
        log.error("validationException " + e.getMessage());
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(e.getMessage()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RejectionException.class)
    public ResponseEntity<ErrorResponse> rejectionException(final RejectionException e) {
        log.error("rejectionException " + e.getMessage());
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse(e.getMessage()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(final NotFoundException e) {
        log.error("notFoundException " + e.getMessage());
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse(e.getMessage()));
    }
}
