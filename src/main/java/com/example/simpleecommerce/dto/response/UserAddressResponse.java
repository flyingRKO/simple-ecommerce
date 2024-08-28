package com.example.simpleecommerce.dto.response;

import com.example.simpleecommerce.entity.UserAddress;

public record UserAddressResponse(
        Long id,
        Long userId,
        String address,
        String alias
) {
    public static UserAddressResponse from(UserAddress userAddress){
        return new UserAddressResponse(
                userAddress.getId(),
                userAddress.getUserId(),
                userAddress.getAddress(),
                userAddress.getAlias()
        );
    }
}
