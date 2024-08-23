package com.example.simpleecommerce.dto.response;

import com.example.simpleecommerce.entity.UserEntity;

public record SignUpResponse(
        String email
) {
    public static SignUpResponse from(UserEntity user){
        return new SignUpResponse(
                user.getEmail()
        );
    }
}
