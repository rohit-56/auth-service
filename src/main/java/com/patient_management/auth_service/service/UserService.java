package com.patient_management.auth_service.service;

import com.patient_management.auth_service.dto.CreateUserRequest;
import com.patient_management.auth_service.entity.UserEntity;
import com.patient_management.auth_service.repository.UserEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserEntityRepository userRepository;

    public UserService(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(CreateUserRequest createUserRequest) {
        UserEntity userEntity = new UserEntity(createUserRequest.getUsername(), createUserRequest.getEmail(),  createUserRequest.getPassword());
        return userRepository.save(userEntity);
    }
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
