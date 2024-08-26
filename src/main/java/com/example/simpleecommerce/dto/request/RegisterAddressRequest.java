package com.example.simpleecommerce.dto.request;

public record RegisterAddressRequest(
        Long userId,
        String address,
        String alias
) {
}
