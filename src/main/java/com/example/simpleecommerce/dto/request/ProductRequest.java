package com.example.simpleecommerce.dto.request;

import java.util.List;

public record ProductRequest(
        Long sellerId,
        String name,
        String description,
        Long price,
        Long stockCount,
        List<String> tags
) {
}
