package com.foorDelivery.restaurant_service.repositary;

import com.foorDelivery.restaurant_service.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    //void findAllById(String id);
}
