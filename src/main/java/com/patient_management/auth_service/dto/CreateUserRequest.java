package com.patient_management.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateUserRequest {

    @NotBlank(message = "Username is Required")
    public String username;

    @NotBlank(message = "Email is Required")
    @Email(message = "Email should be valid")
    public String email;

    @NotBlank(message = "Password is Required")
    public String password;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
