package com.esig.todo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.esig.todo.domain.task.Task;
import com.esig.todo.domain.task.TaskSpecifications;
import com.esig.todo.domain.task.dtos.TaskFilterDTO;
import com.esig.todo.domain.task.dtos.TaskRequestDTO;
import com.esig.todo.domain.task.dtos.UpdateTaskRequestDTO;
import com.esig.todo.exceptions.customs.TaskNotFoundException;
import com.esig.todo.repositories.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task createTask(TaskRequestDTO dto, String ownerId) {
        Task task = dto.toEntity(ownerId);
        return this.taskRepository.save(task);
    }

    public Task getTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);
    }

    public Page<Task> getTasks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return taskRepository.findAll(pageable);
    }

    public Page<Task> getTasksWithFilters(TaskFilterDTO filters, int page, int size) {
        Specification<Task> spec = TaskSpecifications.withFilters(filters);
        Pageable pageable = PageRequest.of(page, size);
        return taskRepository.findAll(spec, pageable);
    }

    public Task updateTask(Long id, String ownerId, UpdateTaskRequestDTO fields) {
        Task task = taskRepository.findByIdAndOwnerId(id, ownerId)
                .orElseThrow(TaskNotFoundException::new);
        task.updateFrom(fields);
        return taskRepository.save(task);
    }

    public void deleteTask(Long id, String ownerId) {
        Task task = taskRepository.findByIdAndOwnerId(id, ownerId)
                .orElseThrow(TaskNotFoundException::new);
        taskRepository.delete(task);
    }
}
