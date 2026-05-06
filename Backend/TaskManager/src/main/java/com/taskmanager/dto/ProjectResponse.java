package com.taskmanager.dto;

public class ProjectResponse {
    private Long id;
    private String name;
    private String description;
    private String managerEmail;

    public ProjectResponse() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getManagerEmail() { return managerEmail; }
    public void setManagerEmail(String managerEmail) { this.managerEmail = managerEmail; }

    public static ProjectResponseBuilder builder() {
        return new ProjectResponseBuilder();
    }

    public static class ProjectResponseBuilder {
        private Long id;
        private String name;
        private String description;
        private String managerEmail;

        public ProjectResponseBuilder id(Long id) { this.id = id; return this; }
        public ProjectResponseBuilder name(String name) { this.name = name; return this; }
        public ProjectResponseBuilder description(String description) { this.description = description; return this; }
        public ProjectResponseBuilder managerEmail(String managerEmail) { this.managerEmail = managerEmail; return this; }
        public ProjectResponse build() {
            ProjectResponse res = new ProjectResponse();
            res.setId(id);
            res.setName(name);
            res.setDescription(description);
            res.setManagerEmail(managerEmail);
            return res;
        }
    }
}
