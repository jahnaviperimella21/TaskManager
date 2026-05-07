package com.taskmanager.controller;

import com.taskmanager.dto.CreateTaskRequest;
import com.taskmanager.dto.TaskResponse;
import com.taskmanager.dto.UpdateStatusRequest;
import com.taskmanager.dto.UpdateTaskRequest;
import com.taskmanager.dto.UserResponse;
import com.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request) {
        return ResponseEntity.ok(taskService.createTask(request));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TaskResponse>> getTasksByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(taskService.getTasksByProject(projectId));
    }

    @PutMapping("/{taskId}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long taskId, @Valid @RequestBody UpdateTaskRequest request) {
        return ResponseEntity.ok(taskService.updateTask(taskId, request));
    }

    @PatchMapping("/{taskId}/status")
    public ResponseEntity<TaskResponse> updateTaskStatus(@PathVariable Long taskId, @Valid @RequestBody UpdateStatusRequest request) {
        return ResponseEntity.ok(taskService.updateTaskStatus(taskId, request));
    }

    @GetMapping("/developers")
    public ResponseEntity<List<UserResponse>> getDevelopers() {
        return ResponseEntity.ok(taskService.getDevelopers());
    }
}
