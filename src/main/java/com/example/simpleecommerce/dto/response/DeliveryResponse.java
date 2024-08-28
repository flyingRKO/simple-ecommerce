package com.example.simpleecommerce.dto.response;

import com.example.simpleecommerce.entity.Delivery;
import com.example.simpleecommerce.enums.DeliveryStatus;

public record DeliveryResponse(
        Long id,
        Long orderId,
        String productName,
        Long productCount,
        String address,
        Long referenceCode,
        DeliveryStatus status
) {
    public static DeliveryResponse from(Delivery delivery){
        return new DeliveryResponse(
                delivery.getId(),
                delivery.getOrderId(),
                delivery.getProductName(),
                delivery.getProductCount(),
                delivery.getAddress(),
                delivery.getReferenceCode(),
                delivery.getStatus()
        );
    }
}
