package com.esig.todo.exceptions.customs;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
        super("Task not found or does not belong to user");
    }
}
