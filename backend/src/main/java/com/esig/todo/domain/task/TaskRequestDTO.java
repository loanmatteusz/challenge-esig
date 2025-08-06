package com.esig.todo.domain.task;

import java.time.LocalDate;

import com.esig.todo.validators.ValidPriority;
import com.esig.todo.validators.ValidStatus;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskRequestDTO(
                @NotBlank(message = "Title cannot be empty") String title,
                String description,
                String responsibleId,
                @NotNull(message = "Status cannot be empty") @ValidStatus String status,
                @NotNull(message = "Priority cannot be empty") @ValidPriority String priority,
                @Future(message = "Deadline must be a future date") @NotNull(message = "Deadline is required") LocalDate deadline) {

        public Task toEntity(String ownerId) {
                return new Task(
                                null,
                                this.title(),
                                this.description(),
                                ownerId,
                                this.responsibleId(),
                                TaskStatus.from(this.status().toUpperCase()),
                                TaskPriority.from(this.priority().toUpperCase()),
                                this.deadline());
        }
}
