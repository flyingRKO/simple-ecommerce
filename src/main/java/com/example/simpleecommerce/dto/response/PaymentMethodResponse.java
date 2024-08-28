package com.example.simpleecommerce.dto.response;

import com.example.simpleecommerce.entity.PaymentMethod;
import com.example.simpleecommerce.enums.PaymentMethodType;

public record PaymentMethodResponse(
        Long id,
        Long userId,
        PaymentMethodType paymentMethodType,
        String creditCardNumber
) {
    public static PaymentMethodResponse from(PaymentMethod paymentMethod) {
        return new PaymentMethodResponse(
                paymentMethod.getId(),
                paymentMethod.getUserId(),
                paymentMethod.getPaymentMethodType(),
                paymentMethod.getCreditCardNumber()
        );
    }
}
