package com.restaurants.service;

import com.restaurants.model.Booking;
import com.restaurants.model.BookingWrapper;
import com.restaurants.model.Customer;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface IBookingService {

    List<BookingWrapper> getBookingByIdPhoneNo(String phoneNo);

    List<BookingWrapper> getBookings();

    Booking saveBooking(Booking booking);

    String updateBooking(Booking booking);
    String cancelBooking(String bookingId);

}
