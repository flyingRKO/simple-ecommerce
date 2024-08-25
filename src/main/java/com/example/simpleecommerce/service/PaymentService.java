package com.example.simpleecommerce.service;

import com.example.simpleecommerce.entity.Payment;
import com.example.simpleecommerce.entity.PaymentMethod;
import com.example.simpleecommerce.enums.PaymentMethodType;
import com.example.simpleecommerce.enums.PaymentStatus;
import com.example.simpleecommerce.pg.CreditCardPaymentAdapter;
import com.example.simpleecommerce.repository.PaymentMethodRepository;
import com.example.simpleecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentRepository paymentRepository;
    private final CreditCardPaymentAdapter creditCardPaymentAdapter;

    public PaymentMethod registerPaymentMethod(Long userId, PaymentMethodType paymentMethodType, String creditCardNumber) {
         return paymentMethodRepository.save(PaymentMethod.of(
            userId, paymentMethodType, creditCardNumber
        ));
    }

    public Payment processPayment(
            Long userId,
            Long orderId,
            Long amountKRW,
            Long paymentMethodId) throws Exception {
        var paymentMethod = paymentMethodRepository.findById(paymentMethodId).orElseThrow();
        if (paymentMethod.getPaymentMethodType() != PaymentMethodType.CREDIT_CARD) {
            throw new Exception("Not supported payment method");
        }

        var refCode = creditCardPaymentAdapter.processCreditPayment(amountKRW, paymentMethod.getCreditCardNumber());

        var payment = Payment.of(
                userId,
                orderId,
                amountKRW,
                paymentMethod.getPaymentMethodType(),
                paymentMethod.getCreditCardNumber(),
                PaymentStatus.COMPLETED,
                refCode
        );
        return paymentRepository.save(payment);
    }

    public PaymentMethod getPaymentMethod(Long userId){
        return paymentMethodRepository.findByUserId(userId).stream().findFirst().orElseThrow();
    }

    public Payment getPayment(Long paymentId){
        return paymentRepository.findById(paymentId).orElseThrow();
    }

}
