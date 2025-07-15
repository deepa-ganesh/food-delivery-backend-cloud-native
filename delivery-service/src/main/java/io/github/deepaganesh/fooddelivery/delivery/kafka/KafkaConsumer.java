package io.github.deepaganesh.fooddelivery.delivery.kafka;

import io.github.deepaganesh.fooddelivery.common.dto.kafka.OrderCreatedEvent;
import io.github.deepaganesh.fooddelivery.delivery.service.DeliveryService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    DeliveryService deliveryService;

    @KafkaListener(topics = "${kafka.topic.order-created}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void consumeOrderCreatedEvent(ConsumerRecord<String, OrderCreatedEvent> consumerRecord) {
        logger.info("Received message from the Kafka topic: {}, key: {}", consumerRecord.topic(), consumerRecord.key());

        OrderCreatedEvent consumedEvent = consumerRecord.value();

        deliveryService.createNewOrder(consumedEvent);
    }
}
