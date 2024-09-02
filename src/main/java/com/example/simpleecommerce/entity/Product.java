package com.example.simpleecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sellerId;
    private String name;
    private String description;
    private Long price;
    @Setter
    private Long stockCount;
    @ManyToMany
    @JoinTable(name = "product_tag",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();

    private Product(Long sellerId, String name, String description, Long price, Long stockCount, List<Tag> tags) {
        this.sellerId = sellerId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockCount = stockCount;
        this.tags = tags;
    }

    public static Product of(Long sellerId, String name, String description, Long price, Long stockCount, List<Tag> tags) {
        return new Product(sellerId, name, description, price, stockCount, tags);
    }
}
