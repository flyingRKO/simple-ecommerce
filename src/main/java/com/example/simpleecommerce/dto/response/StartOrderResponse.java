package com.example.simpleecommerce.dto.response;

public record StartOrderResponse(
        Long orderId,
        PaymentMethodResponse paymentMethod,
        UserAddressResponse address
) {
    public static StartOrderResponse from(Long orderId, PaymentMethodResponse paymentMethod, UserAddressResponse address) {
        return new StartOrderResponse(orderId, paymentMethod, address);
    }
}
