package com.esig.todo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esig.todo.domain.user.User;
import com.esig.todo.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
