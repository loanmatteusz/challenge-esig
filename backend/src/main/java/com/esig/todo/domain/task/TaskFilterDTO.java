package com.esig.todo.domain.task;

public record TaskFilterDTO(
                Long id,
                String query,
                String responsibleId,
                TaskStatus status) {
}
