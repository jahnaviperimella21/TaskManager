package com.taskmanager.service;

import com.taskmanager.dto.CommentResponse;
import com.taskmanager.dto.CreateTaskRequest;
import com.taskmanager.dto.TaskResponse;
import com.taskmanager.dto.UpdateStatusRequest;
import com.taskmanager.dto.UpdateTaskRequest;
import com.taskmanager.dto.UserResponse;
import com.taskmanager.entity.*;
import com.taskmanager.repository.CommentRepository;
import com.taskmanager.repository.ProjectRepository;
import com.taskmanager.repository.TaskRepository;
import com.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    public TaskResponse createTask(CreateTaskRequest request) {
        Project project = projectRepository.findById(request.getProjectId()).orElseThrow();
        User assignedTo = userRepository.findByEmail(request.getAssignedToEmail()).orElseThrow();

        TaskItem task = TaskItem.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .deadline(request.getDeadline())
                .status(TaskStatus.TODO)
                .project(project)
                .assignedTo(assignedTo)
                .build();

        task = taskRepository.save(task);
        return mapToResponse(task);
    }

    public List<TaskResponse> getTasksByProject(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        return taskRepository.findByProject(project).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public TaskResponse updateTask(Long taskId, UpdateTaskRequest request) {
        TaskItem task = taskRepository.findById(taskId).orElseThrow();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setStatus(request.getStatus());
        task.setDeadline(request.getDeadline());

        task = taskRepository.save(task);
        return mapToResponse(task);
    }

    public TaskResponse updateTaskStatus(Long taskId, UpdateStatusRequest request) {
        TaskItem task = taskRepository.findById(taskId).orElseThrow();
        task.setStatus(request.getStatus());
        task = taskRepository.save(task);
        return mapToResponse(task);
    }

    public List<TaskResponse> getMyTasks(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return taskRepository.findByAssignedTo(user).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<UserResponse> getDevelopers() {
        return userRepository.findByRolesName(ERole.ROLE_DEVELOPER).stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .fullName(user.getFullName())
                        .build())
                .collect(Collectors.toList());
    }

    public TaskResponse getTaskById(Long id) {
        TaskItem task = taskRepository.findById(id).orElseThrow();
        return mapToResponse(task);
    }

    public CommentResponse addComment(Long taskId, String email, String content) {
        TaskItem task = taskRepository.findById(taskId).orElseThrow();
        User user = userRepository.findByEmail(email).orElseThrow();

        Comment comment = Comment.builder()
                .content(content)
                .task(task)
                .user(user)
                .createdAt(java.time.LocalDateTime.now())
                .build();

        comment = commentRepository.save(comment);

        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .userEmail(user.getEmail())
                .userFullName(user.getFullName())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public List<CommentResponse> getComments(Long taskId) {
        TaskItem task = taskRepository.findById(taskId).orElseThrow();
        return commentRepository.findByTask(task).stream()
                .map(comment -> CommentResponse.builder()
                        .id(comment.getId())
                        .content(comment.getContent())
                        .userEmail(comment.getUser().getEmail())
                        .userFullName(comment.getUser().getFullName())
                        .createdAt(comment.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    private TaskResponse mapToResponse(TaskItem task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .deadline(task.getDeadline())
                .assignedToEmail(task.getAssignedTo().getEmail())
                .projectId(task.getProject().getId())
                .build();
    }
}
