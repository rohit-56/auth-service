package com.patient_management.auth_service.controller;

import com.patient_management.auth_service.dto.LoginRequest;
import com.patient_management.auth_service.dto.LoginResponse;
import com.patient_management.auth_service.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginRequest loginRequest) {

        String token = authService.getToken(loginRequest);
        if (token==null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token not created user not present");
        }
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        return new ResponseEntity<>(loginResponse, HttpStatus.CREATED);
    }

    @PostMapping("/validate-token")
    public ResponseEntity<Map<String,String>> validateToken(@RequestHeader("Authorization")  String authHeader) {
        if(authHeader==null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Request");
        }

        boolean validToken = authService.validateToken(authHeader.substring(7));
        if(validToken) {
            Map<String,String> map = new HashMap<>();
            map.put("message","Successfully Validated");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            Map<String,String> map = new HashMap<>();
            map.put("message","Invalid Token");
            return new ResponseEntity<>(map,HttpStatus.UNAUTHORIZED);
        }
    }

}
