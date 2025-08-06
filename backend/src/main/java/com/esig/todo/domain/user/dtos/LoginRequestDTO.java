package com.esig.todo.domain.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
                @Email(message = "Invalid email") @NotBlank(message = "Email cannot be empty") String email,
                @NotBlank(message = "Password cannot be empty") String password) {
}
