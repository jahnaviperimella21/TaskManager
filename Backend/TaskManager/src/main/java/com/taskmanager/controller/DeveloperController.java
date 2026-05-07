package com.taskmanager.controller;

import com.taskmanager.dto.CommentResponse;
import com.taskmanager.dto.CreateCommentRequest;
import com.taskmanager.dto.TaskResponse;
import com.taskmanager.dto.UpdateStatusRequest;
import com.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/developer")
public class DeveloperController {
    @Autowired
    TaskService taskService;

    @GetMapping("/my-tasks")
    public ResponseEntity<List<TaskResponse>> getMyTasks() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(taskService.getMyTasks(email));
    }

    @GetMapping("/my-tasks/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PatchMapping("/my-tasks/{id}/status")
    public ResponseEntity<TaskResponse> updateTaskStatus(@PathVariable Long id, @Valid @RequestBody UpdateStatusRequest request) {
        return ResponseEntity.ok(taskService.updateTaskStatus(id, request));
    }

    @PostMapping("/my-tasks/{taskId}/comments")
    public ResponseEntity<CommentResponse> addComment(@PathVariable Long taskId, @Valid @RequestBody CreateCommentRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(taskService.addComment(taskId, email, request.getContent()));
    }

    @GetMapping("/my-tasks/{taskId}/comments")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.getComments(taskId));
    }
}
