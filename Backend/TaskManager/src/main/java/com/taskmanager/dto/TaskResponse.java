package com.taskmanager.dto;

import com.taskmanager.entity.TaskPriority;
import com.taskmanager.entity.TaskStatus;
import java.time.LocalDateTime;

public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime deadline;
    private String assignedToEmail;
    private Long projectId;

    public TaskResponse() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }
    public TaskPriority getPriority() { return priority; }
    public void setPriority(TaskPriority priority) { this.priority = priority; }
    public LocalDateTime getDeadline() { return deadline; }
    public void setDeadline(LocalDateTime deadline) { this.deadline = deadline; }
    public String getAssignedToEmail() { return assignedToEmail; }
    public void setAssignedToEmail(String assignedToEmail) { this.assignedToEmail = assignedToEmail; }
    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public static TaskResponseBuilder builder() {
        return new TaskResponseBuilder();
    }

    public static class TaskResponseBuilder {
        private Long id;
        private String title;
        private String description;
        private TaskStatus status;
        private TaskPriority priority;
        private LocalDateTime deadline;
        private String assignedToEmail;
        private Long projectId;

        public TaskResponseBuilder id(Long id) { this.id = id; return this; }
        public TaskResponseBuilder title(String title) { this.title = title; return this; }
        public TaskResponseBuilder description(String description) { this.description = description; return this; }
        public TaskResponseBuilder status(TaskStatus status) { this.status = status; return this; }
        public TaskResponseBuilder priority(TaskPriority priority) { this.priority = priority; return this; }
        public TaskResponseBuilder deadline(LocalDateTime deadline) { this.deadline = deadline; return this; }
        public TaskResponseBuilder assignedToEmail(String assignedToEmail) { this.assignedToEmail = assignedToEmail; return this; }
        public TaskResponseBuilder projectId(Long projectId) { this.projectId = projectId; return this; }
        public TaskResponse build() {
            TaskResponse res = new TaskResponse();
            res.setId(id);
            res.setTitle(title);
            res.setDescription(description);
            res.setStatus(status);
            res.setPriority(priority);
            res.setDeadline(deadline);
            res.setAssignedToEmail(assignedToEmail);
            res.setProjectId(projectId);
            return res;
        }
    }
}
