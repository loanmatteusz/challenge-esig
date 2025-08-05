package com.esig.todo.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StatusValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidStatus {
    String message()

    default "Status must be one of PENDING, IN_PROGRESS, COMPLETED";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
