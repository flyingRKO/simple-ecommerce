package com.example.simpleecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
@Table(indexes = {@Index(name = "idx_userId", columnList = "userId")})
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String address;
    private String alias;

    private UserAddress(Long userId, String address, String alias) {
        this.userId = userId;
        this.address = address;
        this.alias = alias;
    }

    public static UserAddress of(Long userId, String address, String alias) {
        return new UserAddress(userId, address, alias);
    }
}
