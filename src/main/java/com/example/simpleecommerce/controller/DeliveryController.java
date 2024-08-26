package com.example.simpleecommerce.controller;

import com.example.simpleecommerce.dto.request.ProcessDeliveryRequest;
import com.example.simpleecommerce.dto.request.RegisterAddressRequest;
import com.example.simpleecommerce.entity.Delivery;
import com.example.simpleecommerce.entity.UserAddress;
import com.example.simpleecommerce.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping("/delivery/addresses")
    public UserAddress registerAddress(@RequestBody RegisterAddressRequest request) {
        return deliveryService.addUserAddress(
                request.userId(),
                request.address(),
                request.alias()
        );
    }

    @PostMapping("/delivery/process-delivery")
    public Delivery processDelivery(@RequestBody ProcessDeliveryRequest request) {
        return deliveryService.processDelivery(
                request.orderId(),
                request.productName(),
                request.productCount(),
                request.address()
        );
    }

    @GetMapping("/delivery/deliveries/{deliveryId}")
    public Delivery getDelivery(@PathVariable Long deliveryId) {
        return deliveryService.getDelivery(deliveryId);
    }

    @GetMapping("/delivery/address/{addressId}")
    public UserAddress getAddress(@PathVariable Long addressId) throws Exception {
        return deliveryService.getAddress(addressId);
    }

    @GetMapping("/delivery/users/{userId}/first-address")
    public UserAddress getUserAddress(@PathVariable Long userId) throws Exception {
        return deliveryService.getUserAddress(userId);
    }
}
