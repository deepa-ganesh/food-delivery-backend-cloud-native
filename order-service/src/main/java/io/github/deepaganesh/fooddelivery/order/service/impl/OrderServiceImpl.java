package io.github.deepaganesh.fooddelivery.order.service.impl;

import io.github.deepaganesh.fooddelivery.common.dto.kafka.OrderCreatedEvent;
import io.github.deepaganesh.fooddelivery.common.dto.OrderStatus;
import io.github.deepaganesh.fooddelivery.order.dto.OrderRequest;
import io.github.deepaganesh.fooddelivery.order.entity.Order;
import io.github.deepaganesh.fooddelivery.order.entity.OrderItem;
import io.github.deepaganesh.fooddelivery.common.exception.ResourceNotFoundException;
import io.github.deepaganesh.fooddelivery.order.kafka.OrderEventProducer;
import io.github.deepaganesh.fooddelivery.order.redis.RedisCacheService;
import io.github.deepaganesh.fooddelivery.order.repository.OrderRepository;
import io.github.deepaganesh.fooddelivery.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderEventProducer orderEventProducer;

    @Autowired
    private RedisCacheService redisCacheService;

    @Override
    public Order createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setCustomerId(orderRequest.getCustomerId());
        order.setRestaurantId(orderRequest.getRestaurantId());
        order.setDeliveryAddress(orderRequest.getDeliveryAddress());
        order.setCreatedAt(LocalDateTime.now());
        order.setCurrentStatus(OrderStatus.CREATED);

        List <OrderItem> orderItems = orderRequest.getItems().stream()
                .map(orderItemRequest -> {
                        OrderItem orderItem = new OrderItem();
                        orderItem.setMenuItem(orderItemRequest.getMenuItemId());
                        orderItem.setQuantity(orderItemRequest.getQuantity());
                        orderItem.setPriceAtTime(BigDecimal.valueOf(100));
                        orderItem.setOrder(order);
                        return orderItem;
                })
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);
        order.setTotalPrice(orderItems
                        .stream()
                        .map(orderItem -> orderItem.getPriceAtTime().multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add));

        Order savedOrder = orderRepository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent();
        event.setOrderId(savedOrder.getId());
        event.setCustomerId(savedOrder.getCustomerId());
        event.setRestaurantId(savedOrder.getRestaurantId());
        event.setTotalPrice(savedOrder.getTotalPrice());
        event.setItems(savedOrder.getOrderItems().stream().map(orderItem -> {
            io.github.deepaganesh.fooddelivery.common.dto.OrderItem item = new io.github.deepaganesh.fooddelivery.common.dto.OrderItem();
            item.setMenuItem(orderItem.getMenuItem());
            item.setQuantity(orderItem.getQuantity());
            return item;
        }).collect(Collectors.toList()));

        orderEventProducer.publish(String.valueOf(savedOrder.getId()), event);

        return savedOrder;
    }

    // Spring's Cache Abstraction
    /*@Cacheable(value = "order", key = "#orderId")
    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order is not found with ID: " + orderId));
    }*/

    @Override
    public Order getOrderById(Long orderId) {
        String cacheKey = "order:" + orderId;

        Order cachedOrder = (Order) redisCacheService.getFromCache(cacheKey);

        if (cachedOrder != null) {
            log.info("Order fetched from cache: {}", orderId);
            return cachedOrder;
        }

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order is not found with ID: " + orderId));

        redisCacheService.addToCache(cacheKey, order);
        log.info("Order cached: {}", orderId);

        return order;
    }
}
