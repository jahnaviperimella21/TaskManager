package com.taskmanager.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
public class TaskItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    private LocalDateTime deadline;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "assigned_to_id")
    private User assignedTo;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public TaskItem() {}

    public static TaskItemBuilder builder() {
        return new TaskItemBuilder();
    }

    // Getters and Setters
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
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
    public User getAssignedTo() { return assignedTo; }
    public void setAssignedTo(User assignedTo) { this.assignedTo = assignedTo; }
    public List<Comment> getComments() { return comments; }
    public void setComments(List<Comment> comments) { this.comments = comments; }

    public static class TaskItemBuilder {
        private String title;
        private String description;
        private TaskStatus status;
        private TaskPriority priority;
        private LocalDateTime deadline;
        private Project project;
        private User assignedTo;

        public TaskItemBuilder title(String title) { this.title = title; return this; }
        public TaskItemBuilder description(String description) { this.description = description; return this; }
        public TaskItemBuilder status(TaskStatus status) { this.status = status; return this; }
        public TaskItemBuilder priority(TaskPriority priority) { this.priority = priority; return this; }
        public TaskItemBuilder deadline(LocalDateTime deadline) { this.deadline = deadline; return this; }
        public TaskItemBuilder project(Project project) { this.project = project; return this; }
        public TaskItemBuilder assignedTo(User assignedTo) { this.assignedTo = assignedTo; return this; }
        public TaskItem build() {
            TaskItem task = new TaskItem();
            task.setTitle(title);
            task.setDescription(description);
            task.setStatus(status);
            task.setPriority(priority);
            task.setDeadline(deadline);
            task.setProject(project);
            task.setAssignedTo(assignedTo);
            return task;
        }
    }
}
