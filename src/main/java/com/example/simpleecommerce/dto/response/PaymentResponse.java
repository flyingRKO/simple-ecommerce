package com.example.simpleecommerce.dto.response;

import com.example.simpleecommerce.entity.Payment;
import com.example.simpleecommerce.enums.PaymentMethodType;
import com.example.simpleecommerce.enums.PaymentStatus;

public record PaymentResponse(
        Long id,
        Long userId,
        Long orderId,
        PaymentMethodType paymentMethodType,
        String paymentData,
        PaymentStatus paymentStatus,
        Long referenceCode
) {
    public static PaymentResponse from(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getUserId(),
                payment.getOrderId(),
                payment.getPaymentMethodType(),
                payment.getPaymentData(),
                payment.getPaymentStatus(),
                payment.getReferenceCode()
        );
    }
}
