package com.example.simpleecommerce.dto.request;

import com.example.simpleecommerce.enums.PaymentMethodType;

public record PaymentMethodRequest(
        Long userId,
        PaymentMethodType paymentMethodType,
        String creditCardNumber

) {
}
