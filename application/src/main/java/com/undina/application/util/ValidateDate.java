package com.undina.application.util;

import javax.validation.Constraint;
import javax.validation.constraints.Past;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidateDateValidator.class)
@Past
@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
public @interface ValidateDate {
    String message() default "Вам должно быть 18 лет";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
