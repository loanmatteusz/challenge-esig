package com.esig.todo.validators;

import com.esig.todo.domain.task.TaskPriority;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriorityValidator implements ConstraintValidator<ValidPriority, TaskPriority> {

    @Override
    public boolean isValid(TaskPriority value, ConstraintValidatorContext context) {
        if (value == null)
            return false;
        return true;
    }
}
