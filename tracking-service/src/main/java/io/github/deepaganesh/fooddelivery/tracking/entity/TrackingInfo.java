package io.github.deepaganesh.fooddelivery.tracking.entity;

import io.github.deepaganesh.fooddelivery.common.dto.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.time.LocalDateTime;

@Entity
@Data
public class TrackingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private Long deliveryAgentId;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private String currentLocation;

    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    public void setTimestamps() {
        this.updatedAt = LocalDateTime.now();
    }
}
