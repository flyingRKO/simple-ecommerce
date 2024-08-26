package com.example.simpleecommerce.service;

import com.example.simpleecommerce.dg.DeliveryAdapter;
import com.example.simpleecommerce.entity.Delivery;
import com.example.simpleecommerce.entity.UserAddress;
import com.example.simpleecommerce.enums.DeliveryStatus;
import com.example.simpleecommerce.repository.DeliveryRepository;
import com.example.simpleecommerce.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final UserAddressRepository userAddressRepository;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryAdapter deliveryAdapter;

    public UserAddress addUserAddress(Long userId, String address, String alias) {
        var userAddress = UserAddress.of(userId, address, alias);
        return userAddressRepository.save(userAddress);
    }

    public Delivery processDelivery(Long orderId, String productName, Long productCount, String address) {
        var refCode = deliveryAdapter.processDelivery(productName, productCount, address);
        var delivery = Delivery.of(orderId, productName, productCount, address, refCode, DeliveryStatus.REQUESTED);
        return deliveryRepository.save(delivery);
    }

    public Delivery getDelivery(Long deliveryId) {
        return deliveryRepository.findById(deliveryId).orElseThrow();
    }

    public UserAddress getAddress(Long addressId) {
        return userAddressRepository.findById(addressId).orElseThrow();
    }

    public UserAddress getUserAddress(Long userId) {
        return userAddressRepository.findByUserId(userId).stream().findFirst().orElseThrow();
    }
}
