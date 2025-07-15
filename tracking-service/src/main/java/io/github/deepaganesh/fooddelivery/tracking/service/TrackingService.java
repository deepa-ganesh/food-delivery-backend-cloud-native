package io.github.deepaganesh.fooddelivery.tracking.service;

import io.github.deepaganesh.fooddelivery.tracking.dto.TrackingInfoDTO;

public interface TrackingService {
    TrackingInfoDTO getTrackingByOrderId(Long orderId);
    void updateTracking(TrackingInfoDTO dto);
}
