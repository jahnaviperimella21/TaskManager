package com.taskmanager.dto;

import java.util.Set;

public class JwtResponse {
    private String token;
    private String email;
    private String fullName;
    private Set<String> roles;

    public JwtResponse() {}
    public JwtResponse(String token, String email, String fullName, Set<String> roles) {
        this.token = token;
        this.email = email;
        this.fullName = fullName;
        this.roles = roles;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }

    public static JwtResponseBuilder builder() {
        return new JwtResponseBuilder();
    }

    public static class JwtResponseBuilder {
        private String token;
        private String email;
        private String fullName;
        private Set<String> roles;

        public JwtResponseBuilder token(String token) { this.token = token; return this; }
        public JwtResponseBuilder email(String email) { this.email = email; return this; }
        public JwtResponseBuilder fullName(String fullName) { this.fullName = fullName; return this; }
        public JwtResponseBuilder roles(Set<String> roles) { this.roles = roles; return this; }
        public JwtResponse build() {
            return new JwtResponse(token, email, fullName, roles);
        }
    }
}
