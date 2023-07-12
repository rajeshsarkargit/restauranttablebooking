package com.restaurants.impl;

import com.restaurants.IBookingService;
import com.restaurants.exception.ErrorResponse;
import com.restaurants.exception.ServiceException;
import com.restaurants.model.*;
import com.restaurants.repository.BookingRepository;
import com.restaurants.repository.CustomerRepository;
import com.restaurants.repository.RestaurantRepository;
import com.restaurants.repository.TableAvailabilityRepository;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.restaurants.constants.Constants.*;

@Service
public class BookingServiceImpl implements IBookingService {
    @Autowired
    private BookingRepository repository;
    @Autowired
    private TableAvailabilityRepository tableAvlRepo;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<BookingWrapper> getBookingByIdPhoneNo(String custPhoneNo) {
        Customer customer = customerRepository.findByPhoneNo(custPhoneNo);
        List<Booking> bookings = repository.findByCustomerId(customer.getId());
        List<BookingWrapper> bookingswraps = bookings.stream()
                .filter(booking -> booking.getCustomerId().equals(customer.getId()) && !booking.getIsBookingCanceled())
                .map(booking -> {
                    BookingWrapper wrapper = new BookingWrapper();
                    wrapper.setBookingDate(booking.getBookingDate());
                    wrapper.setBookingTime(booking.getBookingTime());
                    wrapper.setTableNumber(booking.getTableNumber());
                    Optional<Restaurant> restoptn = restaurantRepository.findById(booking.getRestaurantId());
                    wrapper.setRestaurantName(null != restoptn ? restoptn.get().getName() : "");
                    wrapper.setRestaurantPhoneNo(null != restoptn ? restoptn.get().getPhoneNo() : "");
                    wrapper.setCustomerName(null != customer ? customer.getName() : "");
                    wrapper.setCustomerPhoneNo(null != customer ? customer.getPhoneNo() : "");
                    return wrapper;
            }).collect(Collectors.toList());
        return bookingswraps;
    }

    @Override
    public List<BookingWrapper> getBookings() {
        List<Booking> bookings =repository.findAll();
        List<BookingWrapper> bookingswraps = bookings.stream().filter(booking -> !booking.getIsBookingCanceled()).map(booking -> {
            BookingWrapper wrapper = new BookingWrapper();
            wrapper.setBookingId(booking.getId());
            wrapper.setBookingDate(booking.getBookingDate());
            wrapper.setBookingTime(booking.getBookingTime());
            wrapper.setTableNumber(booking.getTableNumber());
            Optional<Restaurant> restoptn = restaurantRepository.findById(booking.getRestaurantId());
            wrapper.setRestaurantName(null != restoptn ? restoptn.get().getName() : "");
            wrapper.setRestaurantPhoneNo(null != restoptn ? restoptn.get().getPhoneNo() : "");
            Optional<Customer> custoptn = customerRepository.findById(booking.getCustomerId());
            wrapper.setCustomerName(null != custoptn ? custoptn.get().getName() : "");
            wrapper.setCustomerPhoneNo(null != custoptn ? custoptn.get().getPhoneNo() : "");
            return wrapper;
        }).collect(Collectors.toList());
        return bookingswraps;
    }


    @Override
    public Booking saveBooking(Booking booking) {
        Date bookingDate = getDate(booking.getBookingDate());
        Date today = new Date();
        if(DateUtils.isSameDay(today, bookingDate) || bookingDate.after(today)){
            Restaurant restaurant = restaurantRepository.findById(booking.getRestaurantId()).get();
            TableAvailability tableAvailability = tableAvlRepo.findByRestaurantAndDate(restaurant.getId(),booking.getBookingDate());
            if(null != tableAvailability && tableAvailability.getAvailableTables()!=0){
                if(!ObjectUtils.isEmpty(tableAvailability.getBookingTimes()) && tableAvailability.getBookingTimes().contains(booking.getBookingTime())){
                    throw new ServiceException(HttpStatus.BAD_REQUEST, new ErrorResponse(TIME_IS_ALREADY_BOOKED));
                }
                booking.setTableNumber(tableAvailability.getAvailableTables());
                tableAvailability.getBookingTimes().add(booking.getBookingTime());
            }else{
                booking.setTableNumber(restaurant.getTotalTables());
                tableAvailability = new TableAvailability(null, booking.getRestaurantId(),
                        booking.getBookingDate(), Arrays.asList(booking.getBookingTime()),0);
            }
            booking.setIsBookingCanceled(false);
            repository.save(booking);
            //update Table Availability Info
            tableAvailability.setAvailableTables(booking.getTableNumber()-1);
            tableAvlRepo.save(tableAvailability);
        }else{
            throw new ServiceException(HttpStatus.BAD_REQUEST, new ErrorResponse(PLEASE_CHECK_BOOKING_DATE));
        }
        return booking;
    }

    @Override
    public String cancelBooking(String bookingId) {
        String result = ERROR_IN_CANCEL_BOOKING;

        Optional<Booking> bookingOptn = repository.findById(bookingId);
        if (null != bookingOptn) {
            Booking booking = bookingOptn.get();
            booking.setIsBookingCanceled(true);
            repository.save(booking);
            TableAvailability tableAvailability = tableAvlRepo.findByRestaurantAndDate(booking.getRestaurantId(),booking.getBookingDate());
            tableAvailability.setAvailableTables(tableAvailability.getAvailableTables()+1);
            tableAvlRepo.save(tableAvailability);
            result = BOOKING_CANCELED_SUCCESSFULLY;
        }else{
            throw new ServiceException(HttpStatus.BAD_REQUEST, new ErrorResponse(BOOKING_NOT_FOUND));
        }
        return result;
    }

    @Override
    public String updateBooking(Booking booking) {
        String result = PROBLEM_IN_UPDATING_BOOKING;
        if (null != booking.getId()) {
            Booking existingProduct = repository.findById(booking.getId()).get();
            if (null != existingProduct) {
                Date bookingDate = getDate(booking.getBookingDate());
                Date today = new Date();
                if(DateUtils.isSameDay(today, bookingDate) || bookingDate.after(today)){
                    repository.save(booking);
                    result = BOOKING_UPDATED_SUCCESSFULLY;
                }else{
                    throw new ServiceException(HttpStatus.BAD_REQUEST,new ErrorResponse(PLEASE_CHECK_BOOKING_DATE));
                }
            }
        }else{
            result = BOOKING_IS_MANDATORY_TO_UPDATED_PRODUCT;
        }
        return result;
    }
    private Date getDate(String dateStr) {
        Date bookingDate = null ;
        try {
            bookingDate = new SimpleDateFormat(DATE_PATTERN).parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return bookingDate;
    }
}
