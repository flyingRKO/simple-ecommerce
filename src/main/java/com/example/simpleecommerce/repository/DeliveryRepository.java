package com.example.simpleecommerce.repository;

import com.example.simpleecommerce.entity.Delivery;
import com.example.simpleecommerce.enums.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>{
    List<Delivery> findByOrderId(Long orderId);
    List<Delivery> findAllByStatus(DeliveryStatus status);
}
