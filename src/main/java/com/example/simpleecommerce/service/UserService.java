package com.example.simpleecommerce.service;

import com.example.simpleecommerce.dto.response.SignUpResponse;
import com.example.simpleecommerce.dto.response.UserResponse;
import com.example.simpleecommerce.entity.UserEntity;
import com.example.simpleecommerce.exception.ErrorCode;
import com.example.simpleecommerce.exception.SimpleEcommerceException;
import com.example.simpleecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public SignUpResponse signUp(String email, String password){
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new SimpleEcommerceException(ErrorCode.DUPLICATED_EMAIL);
        });
        UserEntity savedUser = userRepository.save(UserEntity.of(email, password));
        return SignUpResponse.from(savedUser);
    }

    @Transactional(readOnly = true)
    public UserResponse getUser(Long id) {
        return userRepository.findById(id)
                .map(UserResponse::from)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }
}
