package com.example.simpleecommerce.controller;

import com.example.simpleecommerce.dto.request.PaymentMethodRequest;
import com.example.simpleecommerce.dto.request.ProcessPaymentRequest;
import com.example.simpleecommerce.dto.response.PaymentMethodResponse;
import com.example.simpleecommerce.dto.response.PaymentResponse;
import com.example.simpleecommerce.dto.response.Response;
import com.example.simpleecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/payment/methods")
    public Response<PaymentMethodResponse> registerPaymentMethod(@RequestBody PaymentMethodRequest request) {
        return Response.success(paymentService.registerPaymentMethod(
                request.userId(),
                request.paymentMethodType(),
                request.creditCardNumber()
        ));
    }

    @PostMapping("/payment/process-payment")
    public Response<PaymentResponse> processPayment(@RequestBody ProcessPaymentRequest request) throws Exception {
        return Response.success(paymentService.processPayment(
                request.userId(),
                request.orderId(),
                request.amountKRW(),
                request.paymentMethodId()
        ));
    }

    @GetMapping("/payment/users/{userId}/first-methods")
    public Response<PaymentMethodResponse> getPaymentMethod(@PathVariable Long userId) {
        return Response.success(paymentService.getPaymentMethod(userId));
    }

    @GetMapping("/payment/payments/{paymentId}")
    public Response<PaymentResponse> getPayment(@PathVariable Long paymentId) {
        return Response.success(paymentService.getPayment(paymentId));
    }
}
