package com.esig.todo.domain.task;

import java.time.LocalDate;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        String responsibleId,
        TaskPriority priority,
        LocalDate deadline,
        String ownerId) {

    public static TaskResponseDTO fromEntity(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getResponsibleId(),
                task.getPriority(),
                task.getDeadline(),
                task.getOwnerId());
    }
}
