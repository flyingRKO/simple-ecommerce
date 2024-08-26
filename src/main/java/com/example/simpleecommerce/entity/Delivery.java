package com.example.simpleecommerce.entity;

import com.example.simpleecommerce.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Entity
@Table(indexes = {@Index(name = "idx_orderId", columnList = "orderId")})
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private String productName;
    private Long productCount;
    private String address;
    private Long referenceCode;
    @Setter
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private Delivery(Long orderId, String productName, Long productCount, String address, Long referenceCode, DeliveryStatus status) {
        this.orderId = orderId;
        this.productName = productName;
        this.productCount = productCount;
        this.address = address;
        this.referenceCode = referenceCode;
        this.status = status;
    }


    public static Delivery of(Long orderId, String productName, Long productCount, String address, Long referenceCode, DeliveryStatus deliveryStatus) {
        return new Delivery(orderId, productName, productCount, address, referenceCode, deliveryStatus);
    }
}
