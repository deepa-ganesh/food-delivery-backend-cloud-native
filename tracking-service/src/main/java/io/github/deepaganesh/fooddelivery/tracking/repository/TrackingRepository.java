package io.github.deepaganesh.fooddelivery.tracking.repository;

import io.github.deepaganesh.fooddelivery.tracking.entity.TrackingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrackingRepository extends JpaRepository<TrackingInfo, Long> {
    Optional<TrackingInfo> findTopByOrderIdOrderByUpdatedAtDesc(Long orderId);
}
