package com.restaurants.repository;

import com.restaurants.model.Booking;
import com.restaurants.model.Customer;
import com.restaurants.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {

    List<Booking> findByRestaurantId(String restaurantId);
    List<Booking> findByCustomerId(String customerId);
}
