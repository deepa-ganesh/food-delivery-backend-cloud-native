package io.github.deepaganesh.fooddelivery.order.service;

import io.github.deepaganesh.fooddelivery.order.dto.OrderRequest;
import io.github.deepaganesh.fooddelivery.order.entity.Order;

public interface OrderService {
    Order createOrder(OrderRequest orderRequest);
    Order getOrderById(Long orderId);
}
