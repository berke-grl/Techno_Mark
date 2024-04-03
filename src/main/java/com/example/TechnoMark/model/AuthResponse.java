package com.example.TechnoMark.model;

public class AuthResponse {

    private String description;
    private String token;

    public AuthResponse() {
    }

    public AuthResponse(String description) {
        this.description = description;
    }

    public AuthResponse(String description, String token) {
        this.description = description;
        this.token = token;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "description='" + description + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
