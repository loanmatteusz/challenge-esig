package com.esig.todo.domain.task;

import java.time.LocalDate;

import com.esig.todo.validators.ValidPriority;

public record UpdateTaskRequestDTO(
                String title,
                String description,
                String responsibleId,
                TaskStatus status,
                @ValidPriority TaskPriority priority,
                LocalDate deadline) {
}
