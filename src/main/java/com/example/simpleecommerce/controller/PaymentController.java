package com.example.simpleecommerce.controller;

import com.example.simpleecommerce.dto.request.PaymentMethodRequest;
import com.example.simpleecommerce.dto.request.ProcessPaymentRequest;
import com.example.simpleecommerce.entity.Payment;
import com.example.simpleecommerce.entity.PaymentMethod;
import com.example.simpleecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/payment/methods")
    public PaymentMethod registerPaymentMethod(@RequestBody PaymentMethodRequest request) {
        return paymentService.registerPaymentMethod(
                request.userId(),
                request.paymentMethodType(),
                request.creditCardNumber()
        );
    }

    @PostMapping("/payment/process-payment")
    public Payment processPayment(@RequestBody ProcessPaymentRequest request) throws Exception {
        return paymentService.processPayment(
                request.userId(),
                request.orderId(),
                request.amountKRW(),
                request.paymentMethodId()
        );
    }

    @GetMapping("/payment/users/{userId}/first-methods")
    public PaymentMethod getPaymentMethod(@PathVariable Long userId) {
        return paymentService.getPaymentMethod(userId);
    }

    @GetMapping("/payment/payments/{paymentId}")
    public Payment getPayment(@PathVariable Long paymentId) {
        return paymentService.getPayment(paymentId);
    }
}
