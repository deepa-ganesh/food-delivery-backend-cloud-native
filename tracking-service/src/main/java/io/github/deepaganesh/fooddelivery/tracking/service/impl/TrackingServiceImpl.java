package io.github.deepaganesh.fooddelivery.tracking.service.impl;

import io.github.deepaganesh.fooddelivery.tracking.common.TrackingMapper;
import io.github.deepaganesh.fooddelivery.tracking.dto.TrackingInfoDTO;
import io.github.deepaganesh.fooddelivery.tracking.repository.TrackingRepository;
import io.github.deepaganesh.fooddelivery.tracking.service.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrackingServiceImpl implements TrackingService {

    private final TrackingRepository repository;

    private final TrackingMapper mapper;

    @Override
    public TrackingInfoDTO getTrackingByOrderId(Long orderId) {
        return repository.findTopByOrderIdOrderByUpdatedAtDesc(orderId)
                .map(mapper::toDTO)
                .orElse(null);
    }

    @Override
    public void updateTracking(TrackingInfoDTO dto) {
        repository.save(mapper.toEntity(dto));
    }
}
