package io.github.deepaganesh.fooddelivery.restaurant.repository;

import io.github.deepaganesh.fooddelivery.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT DISTINCT r FROM Restaurant r " +
            "JOIN r.menuItems m " +
            "WHERE (:city IS NULL OR r.city = :city) " +
            "AND (:menuItem IS NULL OR LOWER(m.name) LIKE LOWER(CONCAT('%', :menuItem, '%')))")
    List<Restaurant> searchByCityAndMenuItem(@Param("city") String city, @Param("menuItem") String menuItem);
}
