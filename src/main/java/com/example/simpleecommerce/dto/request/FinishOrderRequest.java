package com.example.simpleecommerce.dto.request;

public record FinishOrderRequest(
        Long orderId,
        Long paymentMethodId,
        Long addressId
) {
}
