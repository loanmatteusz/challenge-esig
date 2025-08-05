package com.esig.todo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.esig.todo.domain.task.Task;
import com.esig.todo.domain.task.TaskPriority;
import com.esig.todo.domain.task.TaskRequestDTO;
import com.esig.todo.domain.task.TaskStatus;
import com.esig.todo.domain.task.UpdateTaskRequestDTO;
import com.esig.todo.exceptions.customs.TaskNotFoundException;
import com.esig.todo.repositories.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task createTask(TaskRequestDTO dto, String ownerId) {
        TaskStatus statusEnum = TaskStatus.valueOf(dto.status().toUpperCase());
        TaskPriority priorityEnum = TaskPriority.valueOf(dto.priority().toUpperCase());

        Task task = new Task(
                null,
                dto.title(),
                dto.description(),
                ownerId,
                dto.responsibleId(),
                statusEnum,
                priorityEnum,
                dto.deadline());
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
