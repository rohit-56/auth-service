package com.patient_management.auth_service.service;

import com.patient_management.auth_service.dto.LoginRequest;
import com.patient_management.auth_service.entity.UserEntity;
import com.patient_management.auth_service.exception.UserNotFoundException;
import com.patient_management.auth_service.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private UserService userService;
    private JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;

    public AuthService(UserService userService, JwtUtil jwtUtil,PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String getToken(LoginRequest loginRequest) {
        Optional<UserEntity> userEntity = userService.findByEmail(loginRequest.getEmail());

        if(userEntity.isEmpty()){
            log.info("User not found with email {}",loginRequest.getEmail());
            throw new UserNotFoundException("User not found");
        }

        if(!passwordEncoder.matches(loginRequest.getPassword(),userEntity.get().getPassword())){
            throw new RuntimeException("Wrong Password");
        }

        return jwtUtil.generateToken(loginRequest.getEmail(), "admin");
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
