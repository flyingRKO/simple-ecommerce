package com.example.simpleecommerce.dto.response;

import com.example.simpleecommerce.entity.UserEntity;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String email,
        LocalDateTime createdAt
) {
    public static UserResponse from(UserEntity user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
