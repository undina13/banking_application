package com.undina.application.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class ValidateDateValidator implements ConstraintValidator<ValidateDate, LocalDate> {
    private LocalDate minimumDate;

    @Override
    public void initialize(ValidateDate constraintAnnotation) {
        minimumDate = LocalDate.now().minusYears(18);
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return value == null || !value.isAfter(minimumDate);
    }
}