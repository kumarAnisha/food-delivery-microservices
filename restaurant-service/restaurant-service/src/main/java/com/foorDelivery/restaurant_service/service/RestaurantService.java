package com.foorDelivery.restaurant_service.service;

import com.foorDelivery.restaurant_service.entity.MenuItem;
import com.foorDelivery.restaurant_service.entity.Restaurant;
import com.foorDelivery.restaurant_service.repositary.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    public List<Restaurant> getAllRestaurant(){
        return restaurantRepository.findAll();
    }
    public List<MenuItem> getMenuItemById(Long id){
        Optional<Restaurant>restaurant = restaurantRepository.findById(id);
        if(restaurant.isPresent()){
            return restaurant.get().getMenuItemList();
        }
        return Collections.emptyList();
    }
    public void addRestaurant(Restaurant restaurants){
        restaurantRepository.save(restaurants);
    }
}
