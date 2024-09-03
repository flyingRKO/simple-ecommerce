package com.example.simpleecommerce.entity;

import com.example.simpleecommerce.enums.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Struct;

@Getter
@NoArgsConstructor
@Entity
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long productId;
    private Long count;
    @Setter
    private OrderStatus status;
    @Setter
    private Long paymentId;
    @Setter
    private Long deliveryId;

    private ProductOrder(Long userId, Long productId, Long count, OrderStatus status, Long paymentId, Long deliveryId) {
        this.userId = userId;
        this.productId = productId;
        this.count = count;
        this.status = status;
        this.paymentId = paymentId;
        this.deliveryId = deliveryId;
    }

    public static ProductOrder of(Long userId, Long productId, Long count, OrderStatus status, Long paymentId, Long deliveryId) {
        return new ProductOrder(userId, productId, count, status, paymentId, deliveryId);
    }
}
