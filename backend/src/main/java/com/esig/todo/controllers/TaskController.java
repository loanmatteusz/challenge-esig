package com.esig.todo.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esig.todo.domain.common.PaginatedResponse;
import com.esig.todo.domain.common.PaginationUtils;
import com.esig.todo.domain.task.Task;
import com.esig.todo.domain.task.TaskFilterDTO;
import com.esig.todo.domain.task.TaskRequestDTO;
import com.esig.todo.domain.task.TaskResponseDTO;
import com.esig.todo.domain.task.TaskStatus;
import com.esig.todo.domain.task.UpdateTaskRequestDTO;
import com.esig.todo.domain.user.User;
import com.esig.todo.services.TaskService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Tasks")
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
    public ResponseEntity<TaskResponseDTO> getTask(@PathVariable Long id) {
        Task task = this.taskService.getTask(id);
        return ResponseEntity.ok(TaskResponseDTO.fromEntity(task));
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<TaskResponseDTO>> getAllTasks(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String responsibleId,
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        TaskFilterDTO filters = new TaskFilterDTO(id, query, responsibleId, status);
        Page<Task> taskPage = this.taskService.getTasksWithFilters(filters, page, size);
        PaginatedResponse<TaskResponseDTO> response = PaginationUtils.toPaginatedResponse(taskPage,
                TaskResponseDTO::fromEntity);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id,
            @RequestBody @Valid UpdateTaskRequestDTO body, @AuthenticationPrincipal User authenticatedUser) {
        Task task = this.taskService.updateTask(id, authenticatedUser.getId(), body);
        return ResponseEntity.ok(TaskResponseDTO.fromEntity(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id,
            @AuthenticationPrincipal User authenticatedUser) {
        taskService.deleteTask(id, authenticatedUser.getId());
        return ResponseEntity.noContent().build();
    }
}
