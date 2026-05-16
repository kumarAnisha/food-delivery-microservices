package com.foorDelivery.restaurant_service.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foorDelivery.restaurant_service.DTO.RestaurantResponseDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private Double rating;

    private String imageUrl;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    @JsonProperty("menuItems")
    private List<MenuItem> menuItemList;

    public RestaurantResponseDTO mapToDTO(){
        return new RestaurantResponseDTO(this);
    }
}
