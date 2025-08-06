package com.esig.todo.services;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.esig.todo.domain.user.User;
import com.esig.todo.domain.user.dtos.LoginRequestDTO;
import com.esig.todo.domain.user.dtos.LoginResponseDTO;
import com.esig.todo.domain.user.dtos.RegisterRequestDTO;
import com.esig.todo.exceptions.customs.UserAlreadyExistsException;
import com.esig.todo.exceptions.customs.UserNotFoundException;
import com.esig.todo.infra.security.TokenService;
import com.esig.todo.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public LoginResponseDTO register(RegisterRequestDTO body) {
        Optional<User> user = this.userRepository.findByEmail(body.email());
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("A user with this email already exists.");
        }

        User newUser = new User();
        newUser.setUsername(body.username());
        newUser.setEmail(body.email());
        newUser.setPassword(passwordEncoder.encode(body.password()));
        this.userRepository.save(newUser);

        String token = tokenService.generateToken(newUser);
        return new LoginResponseDTO(token);
    }

    public LoginResponseDTO login(LoginRequestDTO body) {
        User user = this.userRepository.findByEmail(body.email())
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        if (!passwordEncoder.matches(body.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials.");
        }

        String token = tokenService.generateToken(user);
        return new LoginResponseDTO(token);
    }
}
