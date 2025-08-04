package com.esig.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esig.todo.domain.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
