package com.restaurants.impl;

import com.restaurants.IRestaurantService;
import com.restaurants.model.Restaurant;
import com.restaurants.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.restaurants.constants.Constants.*;

@Service
public class RestaurantServiceImpl implements IRestaurantService {
    @Autowired
    private RestaurantRepository repository;

    @Override
    public Restaurant getRestaurantById(String restaurantId) {

        return repository.findById(restaurantId).get();
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return repository.findAll();
    }

    @Override
    public List<Restaurant> findByCategory(String category) {
        return repository.findByCategory(category);
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
         return repository.save(restaurant);
    }

    @Override
    public String deleteRestaurantById(String restaurantId) {
        String result = PROBLEM_IN_DELETING_RESTAURANT;
        Optional<Restaurant> restaurant = repository.findById(restaurantId);
        if (null != restaurant) {
            repository.delete(restaurant.get());
            result = RESTAURANT_DELETED_SUCCESSFULLY;
        }
        return result;
    }

    @Override
    public String updateRestaurants(Restaurant restaurant) {
        String result = ERROR_IN_UPDATING_RESTAURANT_DATA;
        if (null != restaurant.getId()) {
            Restaurant existingRestaurant = repository.findById(restaurant.getId()).get();
            if (null != existingRestaurant) {
                repository.save(restaurant);
                result = restaurant.getName()+" updated Successfully";
            }
        }else{
            result = RESTAURANT_ID_IS_MANDATORY_TO_UPDATED_RESTAURANT;
        }
        return result;
    }

}
