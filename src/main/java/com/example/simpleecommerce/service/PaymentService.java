package com.example.simpleecommerce.service;

import com.example.simpleecommerce.dto.response.PaymentMethodResponse;
import com.example.simpleecommerce.dto.response.PaymentResponse;
import com.example.simpleecommerce.entity.Payment;
import com.example.simpleecommerce.entity.PaymentMethod;
import com.example.simpleecommerce.enums.PaymentMethodType;
import com.example.simpleecommerce.enums.PaymentStatus;
import com.example.simpleecommerce.exception.ErrorCode;
import com.example.simpleecommerce.exception.SimpleEcommerceException;
import com.example.simpleecommerce.pg.CreditCardPaymentAdapter;
import com.example.simpleecommerce.repository.PaymentMethodRepository;
import com.example.simpleecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentRepository paymentRepository;
    private final CreditCardPaymentAdapter creditCardPaymentAdapter;

    @Transactional
    public PaymentMethodResponse registerPaymentMethod(Long userId, PaymentMethodType paymentMethodType, String creditCardNumber) {

        PaymentMethod paymentMethod = paymentMethodRepository.save(PaymentMethod.of(userId, paymentMethodType, creditCardNumber));

        return PaymentMethodResponse.from(paymentMethod);
    }

    @Transactional
    public PaymentResponse processPayment(
            Long userId,
            Long orderId,
            Long amountKRW,
            Long paymentMethodId) throws Exception {
        var paymentMethod = paymentMethodRepository.findById(paymentMethodId).orElseThrow();
        if (paymentMethod.getPaymentMethodType() != PaymentMethodType.CREDIT_CARD) {
            throw new SimpleEcommerceException(ErrorCode.NOT_SUPPORTED_PAYMENT_METHOD);
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
        paymentRepository.save(payment);
        return PaymentResponse.from(payment);
    }

    @Transactional(readOnly = true)
    public PaymentMethodResponse getPaymentMethod(Long userId){
        PaymentMethod paymentMethod = paymentMethodRepository.findByUserId(userId).stream().findFirst().orElseThrow();
        return PaymentMethodResponse.from(paymentMethod);
    }

    @Transactional(readOnly = true)
    public PaymentResponse getPayment(Long paymentId){
        Payment payment = paymentRepository.findById(paymentId).orElseThrow();
        return PaymentResponse.from(payment);
    }

}
