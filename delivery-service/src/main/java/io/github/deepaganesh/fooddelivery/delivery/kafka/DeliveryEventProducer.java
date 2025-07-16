package io.github.deepaganesh.fooddelivery.delivery.kafka;

import io.github.deepaganesh.fooddelivery.common.dto.kafka.DeliveryStatusUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class DeliveryEventProducer {

    @Autowired
    KafkaTemplate<String, DeliveryStatusUpdatedEvent> kafkaTemplate;

    @Value("${kafka.topic.order-created}")
    private String topic;

    public void publishDeliveryEvent(String key, DeliveryStatusUpdatedEvent event) {
        CompletableFuture<SendResult<String, DeliveryStatusUpdatedEvent>> future =
                kafkaTemplate.send(topic, key, event);

        future.thenAccept(result -> {
            log.info("Kafka message sent: key: {}, topic: {}, partition: {}, offset: {}",
                    result.getProducerRecord().key(),
                    result.getRecordMetadata().topic(),
                    result.getRecordMetadata().partition(),
                    result.getRecordMetadata().offset());
        }).exceptionally(ex -> {
            log.error("Failed to send Kafka message for key: {} - Reason: {}", key, ex.getMessage(), ex);
            return null;
        });
    }
}
