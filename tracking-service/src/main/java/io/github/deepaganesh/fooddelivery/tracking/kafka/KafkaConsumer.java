package io.github.deepaganesh.fooddelivery.tracking.kafka;

import io.github.deepaganesh.fooddelivery.common.dto.kafka.DeliveryStatusUpdatedEvent;
import io.github.deepaganesh.fooddelivery.tracking.dto.TrackingInfoDTO;
import io.github.deepaganesh.fooddelivery.tracking.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class KafkaConsumer {

    @Autowired
    private TrackingService trackingService;

    @KafkaListener(topics = "${kafka.topic.delivery-status-updated}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void handleStatusUpdate(DeliveryStatusUpdatedEvent event) {
        TrackingInfoDTO dto = new TrackingInfoDTO();
        dto.setOrderId(event.getOrderId());
        dto.setDeliveryAgentId(event.getDeliveryAgentId());
        dto.setDeliveryStatus(event.getStatus());
        dto.setUpdatedAt(LocalDateTime.now());

        trackingService.updateTracking(dto);
    }
}
