package com.esig.todo.services;

import com.esig.todo.domain.task.Task;
import com.esig.todo.domain.task.enums.TaskPriority;
import com.esig.todo.domain.task.enums.TaskStatus;
import com.esig.todo.exceptions.customs.TaskNotFoundException;
import com.esig.todo.repositories.TaskRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    private TaskRepository taskRepository;
    private TaskService taskService;

    @BeforeEach
    void setup() {
        taskRepository = Mockito.mock(TaskRepository.class);
        taskService = new TaskService(taskRepository);
    }

    @Test
    void testFindTaskByIdSuccess() {
        Long taskId = 1L;
        Task task = new Task(taskId, "Título", "Descrição", "owner", "responsible",
                TaskStatus.PENDING, TaskPriority.MEDIUM, LocalDate.now());

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        Task result = taskService.getTask(taskId);

        assertThat(result).isEqualTo(task);
        verify(taskRepository).findById(taskId);
    }

    @Test
    void testFindTaskByIdNotFound() {
        Long taskId = 2L;

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.getTask(taskId));
        verify(taskRepository).findById(taskId);
    }
}
