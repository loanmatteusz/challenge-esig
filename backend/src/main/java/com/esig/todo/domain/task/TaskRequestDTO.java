package com.esig.todo.domain.task;

import java.time.LocalDate;

import com.esig.todo.validators.ValidPriority;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskRequestDTO(
                @NotBlank(message = "Title cannot be empty") String title,
                String description,
                @NotNull(message = "Priority cannot be empty") @ValidPriority TaskPriority priority,
                @Future(message = "Deadline must be a future date") @NotNull(message = "Deadline is required") LocalDate deadline) {
}
