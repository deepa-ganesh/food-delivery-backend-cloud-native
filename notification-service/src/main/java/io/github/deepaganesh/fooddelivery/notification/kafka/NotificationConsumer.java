package io.github.deepaganesh.fooddelivery.notification.kafka;

import io.github.deepaganesh.fooddelivery.common.dto.kafka.DeliveryStatusUpdatedEvent;
import io.github.deepaganesh.fooddelivery.common.dto.kafka.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationConsumer {

    @KafkaListener(topics = "${kafka.topic.order-created}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "orderCreatedKafkaListenerFactory")
    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("Order Placed: Sending notification for orderId: {}", event.getOrderId());
        // Simulate notification (email, SMS, etc.)
    }

    @KafkaListener(topics = "${kafka.topic.delivery-status-updated}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "deliveryStatusKafkaListenerFactory")
    public void handleDeliveryStatusUpdated(DeliveryStatusUpdatedEvent event) {
        log.info("Delivery status updated: orderId={}, status={}", event.getOrderId(), event.getStatus());
        // Simulate notification
    }
}
