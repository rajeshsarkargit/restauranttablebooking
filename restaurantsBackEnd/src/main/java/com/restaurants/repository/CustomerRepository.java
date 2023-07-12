package com.restaurants.repository;

import com.restaurants.model.Booking;
import com.restaurants.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    Customer findByPhoneNo(String phoneNo);
}
