package com.example.simpleecommerce.repository;

import com.example.simpleecommerce.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductCustomRepository {
    @EntityGraph(attributePaths = {"tags"})
    List<Product> findBySellerId(Long sellerId);
}
