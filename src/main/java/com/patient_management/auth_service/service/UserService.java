package com.patient_management.auth_service.service;

import com.patient_management.auth_service.dto.CreateUserRequest;
import com.patient_management.auth_service.entity.UserEntity;
import com.patient_management.auth_service.repository.UserEntityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserEntityRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserEntityRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity createUser(CreateUserRequest createUserRequest) {
        UserEntity userEntity = new UserEntity(createUserRequest.getUsername(), createUserRequest.getEmail(), passwordEncoder.encode(createUserRequest.getPassword()));
        return userRepository.save(userEntity);
    }
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
