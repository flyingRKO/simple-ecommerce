package com.example.simpleecommerce.repository;

import com.example.simpleecommerce.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<ProductOrder, Long> {
    List<ProductOrder> findByUserId(Long userId);
}
