package io.github.deepaganesh.fooddelivery.order.repository;

import io.github.deepaganesh.fooddelivery.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
