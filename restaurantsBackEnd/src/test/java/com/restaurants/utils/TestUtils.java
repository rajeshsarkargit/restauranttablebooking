package com.restaurants.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurants.model.Booking;
import com.restaurants.model.Customer;
import com.restaurants.model.Restaurant;
import com.restaurants.model.TableAvailability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestUtils {
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Restaurant getRestaurant(){
        Restaurant restaurant = new Restaurant();
        restaurant.setId("1");
        restaurant.setName("Restaurant-1");
        restaurant.setCategory("Category-1");
        restaurant.setPhoneNo("0123456789");
        restaurant.setTotalTables(10);
        restaurant.setAddress("Address -1");
        return restaurant;
    }
    public static Customer getCustomer(){
        Customer customer = new Customer();
        customer.setId("1");
        customer.setName("Customer-1");
        customer.setPhoneNo("0123456789");
        customer.setEmailId("temp@gmail.com");
        return customer;
    }
    public static Booking getBooking(){
        Booking booking = new Booking();
        booking.setId("1");
        booking.setRestaurantId("1");
        booking.setCustomerId("1");
        booking.setBookingDate("2023-07-11");
        booking.setBookingTime("06.00");
        booking.setTableNumber(1);
        booking.setPersons(2);
        booking.setIsBookingCanceled(false);
        return booking;
    }
    public static TableAvailability getTableAvailability(){
        TableAvailability availability = new TableAvailability();
        availability.setId("1");
        availability.setRestaurantId("1");
        availability.setBookingDate("2023-07-11");
        List<String> times = new ArrayList<>();
        times.add("11.00");
        availability.setBookingTimes(times);
        availability.setAvailableTables(5);
        return availability;
    }

}
