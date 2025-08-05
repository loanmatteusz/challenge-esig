package com.esig.todo.services;

import org.springframework.stereotype.Service;

import com.esig.todo.domain.task.Task;
import com.esig.todo.domain.task.TaskPriority;
import com.esig.todo.domain.task.TaskRequestDTO;
import com.esig.todo.exceptions.customs.TaskNotFoundException;
import com.esig.todo.repositories.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task createTask(TaskRequestDTO dto, String ownerId) {
        TaskPriority priorityEnum = TaskPriority.valueOf(dto.priority().toUpperCase());
        Task task = new Task(
                null,
                dto.title(),
                dto.description(),
                ownerId,
                priorityEnum,
                dto.deadline());
        return this.taskRepository.save(task);
    }

    public Task getTask(String taskId, String ownerId) {
        return taskRepository.findByIdAndOwnerId(taskId, ownerId)
                .orElseThrow(TaskNotFoundException::new);
    }
}
