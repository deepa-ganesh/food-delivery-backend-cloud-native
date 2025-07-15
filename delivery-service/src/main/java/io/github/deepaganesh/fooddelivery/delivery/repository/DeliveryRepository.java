package io.github.deepaganesh.fooddelivery.delivery.repository;

import io.github.deepaganesh.fooddelivery.delivery.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
