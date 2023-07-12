package com.restaurants.controller;

import com.restaurants.exception.ErrorResponse;
import com.restaurants.exception.ServiceException;
import com.restaurants.model.Booking;
import com.restaurants.model.BookingWrapper;
import com.restaurants.IBookingService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.restaurants.constants.Constants.CUSTOMER_ALREADY_EXISTS;
import static com.restaurants.constants.Constants.PLEASE_PROVIDE_BOOKING_ID;

@RestController
@RequestMapping(value = "/api/v1/bookings")
@CrossOrigin("http://localhost:4200/")
public class BookingController {
    @Autowired
    private IBookingService service;

    @GetMapping(value = "/")
    public ResponseEntity<List<BookingWrapper>> getAllBooking() {
         List<BookingWrapper> bookings = service.getBookings();
         return new ResponseEntity<>(bookings,HttpStatus.OK);
    }

    @GetMapping(value = "/{custPhoneNo}")
    public ResponseEntity<List<BookingWrapper>> getBookings(
            @PathVariable(name = "custPhoneNo") String custPhoneNo) {
        return new ResponseEntity<>(service.getBookingByIdPhoneNo(custPhoneNo),HttpStatus.OK);
    }

    @PostMapping(value = "/",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> saveBooking(@RequestBody Booking booking) {
        return new ResponseEntity<>(service.saveBooking(booking),HttpStatus.CREATED);
    }

    @PutMapping(value = "/")
    public String updateBooking(@RequestBody Booking booking) throws Exception {
        if(StringUtils.isEmpty(booking.getId())){
            throw new ServiceException(HttpStatus.BAD_REQUEST,
                    new ErrorResponse(PLEASE_PROVIDE_BOOKING_ID));
        }
        return service.updateBooking(booking);
    }

    @DeleteMapping(value = "/{bookingId}")
    public String cancelBooking(@PathVariable(name = "bookingId") String bookingId ) {
        return service.cancelBooking(bookingId);
    }





}
