package com.taskmanager.service;

import com.taskmanager.dto.CreateTaskRequest;
import com.taskmanager.dto.TaskResponse;
import com.taskmanager.dto.UpdateStatusRequest;
import com.taskmanager.dto.UpdateTaskRequest;
import com.taskmanager.entity.*;
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

    public TaskResponse createTask(Long projectId, CreateTaskRequest request) {
        Project project = projectRepository.findById(projectId).orElseThrow();
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
