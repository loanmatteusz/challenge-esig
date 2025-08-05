package com.esig.todo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esig.todo.domain.task.Task;
import com.esig.todo.domain.task.TaskRequestDTO;
import com.esig.todo.domain.task.TaskResponseDTO;
import com.esig.todo.domain.task.UpdateTaskRequestDTO;
import com.esig.todo.domain.user.User;
import com.esig.todo.services.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody @Valid TaskRequestDTO body,
            @AuthenticationPrincipal User authenticatedUser) {
        String ownerId = authenticatedUser.getId();
        Task task = this.taskService.createTask(body, ownerId);
        return ResponseEntity.ok(TaskResponseDTO.fromEntity(task));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTask(@PathVariable String id,
            @AuthenticationPrincipal User authenticatedUser) {
        Task task = this.taskService.getTask(id, authenticatedUser.getId());
        return ResponseEntity.ok(TaskResponseDTO.fromEntity(task));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks(@AuthenticationPrincipal User authenticatedUser) {
        List<Task> tasks = this.taskService.getTasks(authenticatedUser.getId());
        List<TaskResponseDTO> response = tasks.stream()
                .map(TaskResponseDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable String id,
            @RequestBody @Valid UpdateTaskRequestDTO body, @AuthenticationPrincipal User authenticatedUser) {
        Task task = this.taskService.updateTask(id, authenticatedUser.getId(), body);
        return ResponseEntity.ok(TaskResponseDTO.fromEntity(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id,
            @AuthenticationPrincipal User authenticatedUser) {
        taskService.deleteTask(id, authenticatedUser.getId());
        return ResponseEntity.noContent().build();
    }
}
