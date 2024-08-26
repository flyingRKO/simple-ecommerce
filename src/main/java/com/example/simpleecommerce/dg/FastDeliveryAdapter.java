package com.example.simpleecommerce.dg;

import org.springframework.stereotype.Service;

@Service
public class FastDeliveryAdapter implements DeliveryAdapter{
    @Override
    public Long processDelivery(String productName, Long productCount, String address) {
        // 실제 외부 배송 시스템과 연동하는 코드를 넣어야함

        return 111111111L;
    }
}
