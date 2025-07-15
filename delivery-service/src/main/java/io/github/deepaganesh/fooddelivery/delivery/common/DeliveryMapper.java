package io.github.deepaganesh.fooddelivery.delivery.common;

import io.github.deepaganesh.fooddelivery.delivery.dto.DeliveryDTO;
import io.github.deepaganesh.fooddelivery.delivery.entity.Delivery;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    // Used in non-Spring environments
    // DeliveryMapper INSTANCE = Mappers.getMapper(DeliveryMapper.class);

    @Mapping(source = "status", target = "deliveryStatus")
    DeliveryDTO toDTO(Delivery delivery);

    @Mapping(source = "deliveryStatus", target = "status")
    Delivery toEntity(DeliveryDTO deliveryDTO);

    @InheritConfiguration(name = "toDTO")
    List<DeliveryDTO> toDTO(List<Delivery> deliveries);

    @InheritConfiguration(name = "toEntity")
    List<Delivery> toEntity(List<DeliveryDTO> deliveryDTOs);
}
