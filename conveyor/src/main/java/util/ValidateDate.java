package util;

import javax.validation.Constraint;
import javax.validation.constraints.Past;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidateDateValidator.class)
@Past
public @interface ValidateDate {
    String message() default "Вам должно быть 18 лет";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
