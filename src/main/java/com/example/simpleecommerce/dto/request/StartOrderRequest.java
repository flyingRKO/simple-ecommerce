package com.example.simpleecommerce.dto.request;

public record StartOrderRequest(
        Long userId,
        Long productId,
        Long count
) {
}
