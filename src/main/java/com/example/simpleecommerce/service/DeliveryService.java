package com.example.simpleecommerce.service;

import com.example.simpleecommerce.dg.DeliveryAdapter;
import com.example.simpleecommerce.dto.response.DeliveryResponse;
import com.example.simpleecommerce.dto.response.UserAddressResponse;
import com.example.simpleecommerce.entity.Delivery;
import com.example.simpleecommerce.entity.UserAddress;
import com.example.simpleecommerce.enums.DeliveryStatus;
import com.example.simpleecommerce.repository.DeliveryRepository;
import com.example.simpleecommerce.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final UserAddressRepository userAddressRepository;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryAdapter deliveryAdapter;

    @Transactional
    public UserAddressResponse addUserAddress(Long userId, String address, String alias) {
        var userAddress = UserAddress.of(userId, address, alias);
        return UserAddressResponse.from(userAddressRepository.save(userAddress));
    }

    @Transactional
    public DeliveryResponse processDelivery(Long orderId, String productName, Long productCount, String address) {
        var refCode = deliveryAdapter.processDelivery(productName, productCount, address);
        var delivery = Delivery.of(orderId, productName, productCount, address, refCode, DeliveryStatus.REQUESTED);
        return DeliveryResponse.from(deliveryRepository.save(delivery));
    }

    @Transactional(readOnly = true)
    public DeliveryResponse getDelivery(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow();
        return DeliveryResponse.from(delivery);
    }
    @Transactional(readOnly = true)
    public UserAddressResponse getAddress(Long addressId) {
        return UserAddressResponse.from(userAddressRepository.findById(addressId).orElseThrow());
    }
    @Transactional(readOnly = true)
    public UserAddressResponse getUserAddress(Long userId) {
        return UserAddressResponse.from(userAddressRepository.findByUserId(userId).stream().findFirst().orElseThrow());
    }
}
