package io.github.deepaganesh.fooddelivery.tracking.dto;

import io.github.deepaganesh.fooddelivery.common.dto.DeliveryStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrackingInfoDTO {
    private Long orderId;
    private Long deliveryAgentId;
    private DeliveryStatus deliveryStatus;
    private String currentLocation;
    private LocalDateTime updatedAt;
}
