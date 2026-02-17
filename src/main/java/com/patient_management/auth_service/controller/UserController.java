package com.patient_management.auth_service.controller;

import com.patient_management.auth_service.dto.CreateUserRequest;
import com.patient_management.auth_service.dto.UserResponse;
import com.patient_management.auth_service.entity.UserEntity;
import com.patient_management.auth_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<UserResponse> createUser(@Validated @RequestBody CreateUserRequest createUserRequest) {
        UserEntity userEntity=userService.createUser(createUserRequest);
        UserResponse userResponse=new UserResponse();
        userResponse.setId(userEntity.getId());
        userResponse.setUsername(userEntity.getUsername());
        userResponse.setEmail(userEntity.getEmail());
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

}
