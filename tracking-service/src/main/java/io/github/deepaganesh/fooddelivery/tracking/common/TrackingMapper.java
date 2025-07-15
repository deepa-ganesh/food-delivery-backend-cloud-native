package io.github.deepaganesh.fooddelivery.tracking.common;

import io.github.deepaganesh.fooddelivery.tracking.dto.TrackingInfoDTO;
import io.github.deepaganesh.fooddelivery.tracking.entity.TrackingInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrackingMapper {
    TrackingInfoDTO toDTO(TrackingInfo entity);
    TrackingInfo toEntity(TrackingInfoDTO dto);
}
