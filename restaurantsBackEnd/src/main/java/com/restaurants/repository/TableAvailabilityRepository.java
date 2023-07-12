package com.restaurants.repository;

import com.restaurants.model.Restaurant;
import com.restaurants.model.TableAvailability;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableAvailabilityRepository extends MongoRepository<TableAvailability, String> {
    @Query("{'restaurantId' : ?0, 'bookingDate' : ?1}")
    TableAvailability findByRestaurantAndDate(String restaurantId,String bookingDate);

}
