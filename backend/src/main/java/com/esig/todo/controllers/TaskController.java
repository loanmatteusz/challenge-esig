package com.esig.todo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @GetMapping
    public ResponseEntity<String> getTasks() {
        return ResponseEntity.ok("SUCCESS!");
    }
}
