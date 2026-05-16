package com.foorDelivery.restaurant_service.DTO;

import com.foorDelivery.restaurant_service.entity.MenuItem;
import com.foorDelivery.restaurant_service.entity.Restaurant;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class RestaurantResponseDTO {
    private Long id;
    private String name;
    private String city;
    private Double rating;
    private String imageUrl;
    //private List<MenuItem> menu;

    public RestaurantResponseDTO(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.city = restaurant.getCity();
        this.rating = restaurant.getRating();
        this.imageUrl = restaurant.getImageUrl();
        //this.menu = restaurant.getMenuItemList();
    }

}
