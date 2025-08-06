package com.esig.todo.domain.user.dtos;

import com.esig.todo.domain.user.User;

public record UserResponseDTO(
        String id,
        String name,
        String email) {
    public static UserResponseDTO from(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail());
    }
}
