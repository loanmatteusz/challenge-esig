package com.esig.todo.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class PriorityValidator implements ConstraintValidator<ValidPriority, String> {

    private final List<String> validPriorities = Arrays.asList("LOW", "MEDIUM", "HIGH");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return false;
        return validPriorities.contains(value.toUpperCase());
    }
}
