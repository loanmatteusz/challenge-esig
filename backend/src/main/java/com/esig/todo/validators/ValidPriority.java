package com.esig.todo.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PriorityValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPriority {
    String message() default "Priority must be one of LOW, MEDIUM, HIGH";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
