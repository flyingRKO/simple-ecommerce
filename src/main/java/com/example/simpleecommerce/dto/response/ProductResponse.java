package com.example.simpleecommerce.dto.response;

import com.example.simpleecommerce.entity.Product;
import com.example.simpleecommerce.entity.Tag;

import java.util.List;



public record ProductResponse(
        Long id,
        Long sellerId,
        String name,
        String description,
        Long price,
        Long stockCount,
        List<String> tags
) {
    public static ProductResponse from(Product product) {
        List<String> tagNames = product.getTags().stream()
                .map(Tag::getName)
                .toList();

        return new ProductResponse(
                product.getId(),
                product.getSellerId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockCount(),
                tagNames
        );
    }
}
