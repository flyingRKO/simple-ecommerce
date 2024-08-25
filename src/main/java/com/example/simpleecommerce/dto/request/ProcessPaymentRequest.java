package com.example.simpleecommerce.dto.request;

public record ProcessPaymentRequest(
        Long userId,
        Long orderId,
        Long amountKRW,
        Long paymentMethodId
) {
}
