package com.example.simpleecommerce.pg;

import org.springframework.stereotype.Service;

@Service
public class EasyCreditCardPaymentAdapter implements CreditCardPaymentAdapter{

    @Override
    public Long processCreditPayment(Long amountKRW, String creditCardNumber) {
        // 실제로는 외부 결제 시스템

        return Math.round(Math.random() * 10000000000L);
    }
}
