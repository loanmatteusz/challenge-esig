package com.esig.todo.domain.task.dtos;

import com.esig.todo.domain.task.enums.TaskStatus;

public record TaskFilterDTO(
        Long id,
        String query,
        String responsibleId,
        TaskStatus status) {
}
