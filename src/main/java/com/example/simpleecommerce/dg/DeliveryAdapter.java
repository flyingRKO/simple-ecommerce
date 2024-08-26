package com.example.simpleecommerce.dg;

public interface DeliveryAdapter {
    Long processDelivery(String productName, Long productCount, String address);
}
