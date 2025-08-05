package com.esig.todo.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.esig.todo.domain.task.Task;

public interface TaskRepository extends JpaRepository<Task, String> {
    Optional<Task> findByIdAndOwnerId(String id, String ownerId);

    Page<Task> findByOwnerId(String ownerId, Pageable pageable);
}
