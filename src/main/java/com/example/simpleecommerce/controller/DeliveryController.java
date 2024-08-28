package com.example.simpleecommerce.controller;

import com.example.simpleecommerce.dto.request.ProcessDeliveryRequest;
import com.example.simpleecommerce.dto.request.RegisterAddressRequest;
import com.example.simpleecommerce.dto.response.DeliveryResponse;
import com.example.simpleecommerce.dto.response.Response;
import com.example.simpleecommerce.dto.response.UserAddressResponse;
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
    public Response<UserAddressResponse> registerAddress(@RequestBody RegisterAddressRequest request) {
        return Response.success(deliveryService.addUserAddress(
                request.userId(),
                request.address(),
                request.alias()
        )) ;
    }

    @PostMapping("/delivery/process-delivery")
    public Response<DeliveryResponse> processDelivery(@RequestBody ProcessDeliveryRequest request) {
        return Response.success(deliveryService.processDelivery(
                request.orderId(),
                request.productName(),
                request.productCount(),
                request.address()
        ));
    }

    @GetMapping("/delivery/deliveries/{deliveryId}")
    public Response<DeliveryResponse> getDelivery(@PathVariable Long deliveryId) {
        return Response.success(deliveryService.getDelivery(deliveryId));
    }

    @GetMapping("/delivery/address/{addressId}")
    public Response<UserAddressResponse> getAddress(@PathVariable Long addressId) throws Exception {
        return Response.success(deliveryService.getAddress(addressId));
    }

    @GetMapping("/delivery/users/{userId}/first-address")
    public Response<UserAddressResponse> getUserAddress(@PathVariable Long userId) throws Exception {
        return Response.success(deliveryService.getUserAddress(userId));
    }
}
