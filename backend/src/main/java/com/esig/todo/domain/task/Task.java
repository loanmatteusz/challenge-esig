package com.esig.todo.domain.task;

import java.time.LocalDate;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    private String description;

    private String ownerId;

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
        if (dto.priority() != null) {
            this.priority = dto.priority();
        }
        if (dto.deadline() != null) {
            this.deadline = dto.deadline();
        }
    }
}
