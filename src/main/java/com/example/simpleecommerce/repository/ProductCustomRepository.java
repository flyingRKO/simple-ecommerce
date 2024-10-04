package com.example.simpleecommerce.repository;

import com.example.simpleecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductCustomRepository {
    Page<Product> searchProducts(String productName, String tagName, Pageable pageable);
}
