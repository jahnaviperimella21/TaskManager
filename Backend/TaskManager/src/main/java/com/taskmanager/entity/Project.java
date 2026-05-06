package com.taskmanager.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskItem> tasks = new ArrayList<>();

    public Project() {}

    public static ProjectBuilder builder() {
        return new ProjectBuilder();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public User getManager() { return manager; }
    public void setManager(User manager) { this.manager = manager; }
    public List<TaskItem> getTasks() { return tasks; }
    public void setTasks(List<TaskItem> tasks) { this.tasks = tasks; }

    public static class ProjectBuilder {
        private String name;
        private String description;
        private User manager;

        public ProjectBuilder name(String name) { this.name = name; return this; }
        public ProjectBuilder description(String description) { this.description = description; return this; }
        public ProjectBuilder manager(User manager) { this.manager = manager; return this; }
        public Project build() {
            Project project = new Project();
            project.setName(name);
            project.setDescription(description);
            project.setManager(manager);
            return project;
        }
    }
}
