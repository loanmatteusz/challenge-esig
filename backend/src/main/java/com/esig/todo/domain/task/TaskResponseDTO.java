package com.esig.todo.domain.task;

import java.time.LocalDate;

public record TaskResponseDTO(
        String id,
        String title,
        String description,
        TaskPriority priority,
        LocalDate deadline,
        String ownerId) {

    public static TaskResponseDTO fromEntity(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getPriority(),
                task.getDeadline(),
                task.getOwnerId());
    }
}
