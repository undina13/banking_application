package com.undina.conveyor.controller;

import com.undina.conveyor.exception.ErrorResponse;
import com.undina.conveyor.exception.RejectionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ErrorHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse validationException(final Exception e) {
        return new ErrorResponse(
                String.format("\"%s\"", e.getMessage())
        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RejectionException.class)
    public ErrorResponse rejectionException(final Exception e) {
        return new ErrorResponse(
                String.format("\"%s\"", e.getMessage())
        );
    }
}
