package com.example.simpleecommerce.dto.response;

import com.example.simpleecommerce.entity.ProductOrder;
import com.example.simpleecommerce.enums.OrderStatus;
;
public record FinishOrderResponse(
        Long id,
        Long userId,
        Long productId,
        Long count,
        OrderStatus status,
        Long paymentId,
        Long deliveryId
) {
    public static FinishOrderResponse from(ProductOrder order) {
        return new FinishOrderResponse(
                order.getId(),
                order.getUserId(),
                order.getProductId(),
                order.getCount(),
                order.getStatus(),
                order.getPaymentId(),
                order.getDeliveryId()
        );
    }
}
