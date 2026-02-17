package com.patient_management.auth_service.service;

import com.patient_management.auth_service.dto.LoginRequest;
import com.patient_management.auth_service.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserService userService;
    private JwtUtil jwtUtil;

    public AuthService(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public Optional<String> getToken(LoginRequest loginRequest) {
        return null;
    }

    public boolean validateToken(String token) {
        try {
            jwtUtil.validateToken(token);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
