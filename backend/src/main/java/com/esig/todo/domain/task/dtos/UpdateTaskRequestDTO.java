package com.esig.todo.domain.task.dtos;

import java.time.LocalDate;

import com.esig.todo.validators.ValidPriority;
import com.esig.todo.validators.ValidStatus;

public record UpdateTaskRequestDTO(
                String title,
                String description,
                String responsibleId,
                @ValidStatus String status,
                @ValidPriority String priority,
                LocalDate deadline) {
}
