package com.example.simpleecommerce.entity;

import com.example.simpleecommerce.enums.PaymentMethodType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
@Table(indexes = {@Index(name = "idx_userId", columnList = "userId")})
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private PaymentMethodType paymentMethodType;

    private String creditCardNumber;

    private PaymentMethod(Long userId, PaymentMethodType paymentMethodType, String creditCardNumber) {
        this.userId = userId;
        this.paymentMethodType = paymentMethodType;
        this.creditCardNumber = creditCardNumber;
    }

    public static PaymentMethod of(Long userId, PaymentMethodType paymentMethodType, String creditCardNumber) {
        return new PaymentMethod(userId, paymentMethodType, creditCardNumber);
    }
}
