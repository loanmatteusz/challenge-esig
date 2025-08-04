package com.esig.todo.domain.task;

import java.util.Date;

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

    private String useId;
    private String priority;
    private Date deadline;

    // public Product(TaskRequestDTO data){
    // this.price = data.price();
    // this.name = data.name();
    // }
}
