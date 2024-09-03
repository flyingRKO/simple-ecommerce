package com.example.simpleecommerce.service;

import com.example.simpleecommerce.dto.response.FinishOrderResponse;
import com.example.simpleecommerce.dto.response.StartOrderResponse;
import com.example.simpleecommerce.entity.ProductOrder;
import com.example.simpleecommerce.enums.OrderStatus;
import com.example.simpleecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final DeliveryService deliveryService;
    private final CatalogService catalogService;

    @Transactional
    public StartOrderResponse startOrder(Long userId, Long productId, Long count) {
        var paymentMethod = paymentService.getPaymentMethod(userId);
        var userAddress = deliveryService.getUserAddress(userId);
        var order = ProductOrder.of(
                userId,
                productId,
                count,
                OrderStatus.INITIATED,
                null,
                null
        );
        orderRepository.save(order);

        return StartOrderResponse.from(order.getId(), paymentMethod, userAddress);
    }

    @Transactional
    public FinishOrderResponse finishOrder(Long orderId, Long paymentMethodId, Long addressId) throws Exception {
        var order = orderRepository.findById(orderId).orElseThrow();

        var product = catalogService.getProductById(order.getProductId());

        var payment = paymentService.processPayment(order.getUserId(), orderId, product.price() * order.getCount(), paymentMethodId);

        var address = deliveryService.getAddress(addressId);
        var delivery = deliveryService.processDelivery(orderId, product.name(), order.getCount(), address.address());

        catalogService.decreaseStockCount(order.getProductId(), order.getCount());

        order.setPaymentId(payment.id());
        order.setDeliveryId(delivery.id());
        order.setStatus(OrderStatus.DELIVERY_REQUESTED);

        return FinishOrderResponse.from(orderRepository.save(order));
    }

    @Transactional
    public List<ProductOrder> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Transactional
    public FinishOrderResponse getOrder(Long orderId) {
        return FinishOrderResponse.from(orderRepository.findById(orderId).orElseThrow());
    }
}
