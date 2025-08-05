package com.esig.todo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esig.todo.domain.task.Task;
import com.esig.todo.domain.task.TaskRequestDTO;
import com.esig.todo.domain.task.TaskResponseDTO;
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
}
