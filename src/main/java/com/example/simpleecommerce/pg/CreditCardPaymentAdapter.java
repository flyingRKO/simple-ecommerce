package com.example.simpleecommerce.pg;

public interface CreditCardPaymentAdapter {
    Long processCreditPayment(Long amountKRW, String creditCardNumber);
}
