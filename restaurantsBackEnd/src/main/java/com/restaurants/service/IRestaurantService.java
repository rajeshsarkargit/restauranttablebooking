package com.restaurants.service;

import com.restaurants.model.Restaurant;

import java.util.List;

public interface IRestaurantService {
    Restaurant getRestaurantById(String id);

    List<Restaurant> getRestaurants();

    List<Restaurant> findByCategory(String category);

    Restaurant saveRestaurant(Restaurant restaurant);

    String deleteRestaurantById(String restaurantId);

    String updateRestaurants(Restaurant restaurant);

}
