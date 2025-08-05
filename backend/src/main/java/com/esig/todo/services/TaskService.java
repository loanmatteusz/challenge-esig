package com.esig.todo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.esig.todo.domain.task.Task;
import com.esig.todo.domain.task.TaskRequestDTO;
import com.esig.todo.domain.task.UpdateTaskRequestDTO;
import com.esig.todo.exceptions.customs.TaskNotFoundException;
import com.esig.todo.repositories.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task createTask(TaskRequestDTO dto, String ownerId) {
        Task task = new Task(
                null,
                dto.title(),
                dto.description(),
                ownerId,
                dto.priority(),
                dto.deadline());
        return this.taskRepository.save(task);
    }

    public Task getTask(String id, String ownerId) {
        return taskRepository.findByIdAndOwnerId(id, ownerId)
                .orElseThrow(TaskNotFoundException::new);
    }

    public Page<Task> getTasks(String ownerId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return taskRepository.findByOwnerId(ownerId, pageable);
    }

    public Task updateTask(String id, String ownerId, UpdateTaskRequestDTO fields) {
        Task task = taskRepository.findByIdAndOwnerId(id, ownerId)
                .orElseThrow(TaskNotFoundException::new);
        task.updateFrom(fields);
        return taskRepository.save(task);
    }

    public void deleteTask(String id, String ownerId) {
        Task task = taskRepository.findByIdAndOwnerId(id, ownerId)
                .orElseThrow(TaskNotFoundException::new);
        taskRepository.delete(task);
    }
}
