package com.restaurants.service;

import com.restaurants.model.Restaurant;
import com.restaurants.repository.RestaurantRepository;
import com.restaurants.impl.RestaurantServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.restaurants.constants.Constants.RESTAURANT_DELETED_SUCCESSFULLY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @InjectMocks
    private RestaurantServiceImpl target;

    @Mock
    private RestaurantRepository repository;


    @Test
    public void getRestaurantById() {
        Restaurant restaurant = new Restaurant();
        when(repository.findById(anyString())).thenReturn(Optional.of(restaurant));
        Restaurant actual = target.getRestaurantById("12345");
        assertNotNull(actual);
    }
    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = Arrays.asList(new Restaurant());
        when(repository.findAll()).thenReturn(restaurants);
        List<Restaurant> actual = target.getRestaurants();
        assertNotNull(actual);
        assertEquals(actual.size(),1);
    }
    @Test
    public void saveRestaurant() {
        Restaurant restaurant = new Restaurant();
        when(repository.save(any())).thenReturn(restaurant);
        Restaurant actual = target.saveRestaurant(restaurant);
        assertNotNull(actual);
    }
    @Test
    public void deleteRestaurantById() {
        Restaurant restaurant = new Restaurant();
        when(repository.findById(any())).thenReturn(Optional.of(restaurant));
        String actual = target.deleteRestaurantById("restaurant");
        assertNotNull(actual);
        assertEquals(actual,RESTAURANT_DELETED_SUCCESSFULLY);
    }
    @Test
    public void updateRestaurants() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId("1");
        when(repository.findById(any())).thenReturn(Optional.of(restaurant));
        String actual = target.updateRestaurants(restaurant);
        assertNotNull(actual);
        assertEquals(actual,restaurant.getName()+" updated Successfully");
    }


}
