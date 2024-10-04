package com.example.simpleecommerce.dto.request;

public record SearchProductRequest(
        String productName,
        String tagName
) {
}
