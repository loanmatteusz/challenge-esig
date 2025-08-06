package com.esig.todo.domain.task.dtos;

import java.time.LocalDate;

import com.esig.todo.domain.task.Task;
import com.esig.todo.domain.task.enums.TaskPriority;
import com.esig.todo.domain.task.enums.TaskStatus;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        String responsibleId,
        TaskStatus status,
        TaskPriority priority,
        LocalDate deadline,
        String ownerId) {

    public static TaskResponseDTO fromEntity(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getResponsibleId(),
                task.getStatus(),
                task.getPriority(),
                task.getDeadline(),
                task.getOwnerId());
    }
}
