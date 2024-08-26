package com.example.simpleecommerce.dto.request;

public record ProcessDeliveryRequest(
        Long orderId,
        String productName,
        Long productCount,
        String address
) {
}
