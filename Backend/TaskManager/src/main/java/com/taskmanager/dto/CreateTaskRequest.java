package com.taskmanager.dto;

import com.taskmanager.entity.TaskPriority;
import java.time.LocalDateTime;

public class CreateTaskRequest {
    private String title;
    private String description;
    private TaskPriority priority;
    private LocalDateTime deadline;
    private String assignedToEmail;
    private Long projectId;

    public CreateTaskRequest() {}
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public TaskPriority getPriority() { return priority; }
    public void setPriority(TaskPriority priority) { this.priority = priority; }
    public LocalDateTime getDeadline() { return deadline; }
    public void setDeadline(LocalDateTime deadline) { this.deadline = deadline; }
    public String getAssignedToEmail() { return assignedToEmail; }
    public void setAssignedToEmail(String assignedToEmail) { this.assignedToEmail = assignedToEmail; }
    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
}
