package com.esig.todo.domain.task;

import java.time.LocalDate;

import com.esig.todo.domain.task.dtos.UpdateTaskRequestDTO;
import com.esig.todo.domain.task.enums.TaskPriority;
import com.esig.todo.domain.task.enums.TaskStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tasks")
@Entity(name = "tasks")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Para Task haverá filtro de núemro

    private String title;

    private String description;

    private String ownerId;

    private String responsibleId;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    private LocalDate deadline;

    public void updateFrom(UpdateTaskRequestDTO dto) {
        if (dto.title() != null) {
            this.title = dto.title();
        }
        if (dto.description() != null) {
            this.description = dto.description();
        }
        if (dto.responsibleId() != null) {
            this.responsibleId = dto.responsibleId();
        }
        if (dto.status() != null) {
            this.status = TaskStatus.valueOf(dto.status().toUpperCase());
        }
        if (dto.priority() != null) {
            this.priority = TaskPriority.valueOf(dto.priority().toUpperCase());
        }
        if (dto.deadline() != null) {
            this.deadline = dto.deadline();
        }
    }
}
