package com.esig.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.esig.todo.domain.task.Task;

public interface TaskRepository extends JpaRepository<Task, String> {
}
