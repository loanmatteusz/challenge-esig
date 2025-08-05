package com.esig.todo.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
        @NotBlank(message = "Username cannot be empty") String username,

        @Email(message = "Invalid Email") @NotBlank(message = "Email cannot be empty") String email,

        @Size(min = 3, message = "Password should have at least 3 characteres") String password) {
}
