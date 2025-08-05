package com.esig.todo.domain.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskPriority {
    LOW,
    MEDIUM,
    HIGH;

    @JsonCreator
    public static TaskPriority from(String value) {
        return TaskPriority.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
