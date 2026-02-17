package com.patient_management.auth_service.dto;

public class UserResponse {

    public long id;

    public String username;

    public String email;

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
