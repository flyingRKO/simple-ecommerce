package com.example.simpleecommerce.controller;

import com.example.simpleecommerce.dto.request.FinishOrderRequest;
import com.example.simpleecommerce.dto.request.StartOrderRequest;
import com.example.simpleecommerce.dto.response.FinishOrderResponse;
import com.example.simpleecommerce.dto.response.Response;
import com.example.simpleecommerce.dto.response.StartOrderResponse;
import com.example.simpleecommerce.entity.ProductOrder;
import com.example.simpleecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/start-order")
    public Response<StartOrderResponse> startOrder(@RequestBody StartOrderRequest request) throws Exception {
        return Response.success(orderService.startOrder(request.userId(), request.productId(), request.count()));
    }

    @PostMapping("/order/finish-order")
    public Response<FinishOrderResponse> finishOrder(@RequestBody FinishOrderRequest request) throws Exception {
        return Response.success(orderService.finishOrder(request.orderId(), request.paymentMethodId(), request.addressId()));
    }

    @GetMapping("/order/users/{userId}/orders")
    public Response<List<ProductOrder>> getUserOrders(@PathVariable Long userId) {
        return Response.success(orderService.getUserOrders(userId));
    }

    @GetMapping("/order/orders/{orderId}")
    public Response<FinishOrderResponse> getOrder(@PathVariable Long orderId) {
        return Response.success(orderService.getOrder(orderId));
    }
}
