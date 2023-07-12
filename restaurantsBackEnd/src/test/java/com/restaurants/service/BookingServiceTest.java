package com.restaurants.service;

import com.restaurants.exception.ServiceException;
import com.restaurants.service.impl.BookingServiceImpl;
import com.restaurants.model.Booking;
import com.restaurants.model.BookingWrapper;
import com.restaurants.repository.BookingRepository;
import com.restaurants.repository.CustomerRepository;
import com.restaurants.repository.RestaurantRepository;
import com.restaurants.repository.TableAvailabilityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.restaurants.constants.Constants.*;
import static com.restaurants.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @InjectMocks
    private BookingServiceImpl service;

    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private BookingRepository repository;
    @Mock
    private TableAvailabilityRepository tableAvlRepo;
    @DisplayName("getBookingByIdPhoneNo")
    @Test
    public void getBookingByIdPhoneNo() {
        when(customerRepository.findByPhoneNo(anyString())).thenReturn(getCustomer());
        when(repository.findByCustomerId(anyString())).thenReturn(Arrays.asList(getBooking()));
        when(restaurantRepository.findById(anyString())).thenReturn(Optional.of(getRestaurant()));
        List<BookingWrapper>  actual = service.getBookingByIdPhoneNo("12345");
        assertNotNull(actual);
        assertEquals(actual.size(),1);
    }
    @DisplayName("getBookings")
    @Test
    public void getBookings() {
        when(repository.findAll()).thenReturn(Arrays.asList(getBooking()));
        when(restaurantRepository.findById(anyString())).thenReturn(Optional.of(getRestaurant()));
        when(customerRepository.findById(anyString())).thenReturn(Optional.of(getCustomer()));
        List<BookingWrapper>  actual = service.getBookings();
        assertNotNull(actual);
        assertEquals(actual.size(),1);
    }
    @DisplayName("saveBooking")
    @Test
    public void saveBooking() {
        Booking booking = getBooking();
        when(restaurantRepository.findById(anyString())).thenReturn(Optional.of(getRestaurant()));
        when(tableAvlRepo.findByRestaurantAndDate(anyString(),anyString())).thenReturn(getTableAvailability());
        Booking  actual = service.saveBooking(booking);
        assertNotNull(actual);
    }
    @DisplayName("saveBooking 1st Booking")
    @Test
    public void saveBooking_1stTime() {
        Booking booking = getBooking();
        when(restaurantRepository.findById(anyString())).thenReturn(Optional.of(getRestaurant()));
        when(tableAvlRepo.findByRestaurantAndDate(anyString(),anyString())).thenReturn(null);
        Booking  actual = service.saveBooking(booking);
        assertNotNull(actual);
    }
    @DisplayName("conflict in Time")
    @Test
    public void saveBooking_Time_Booked() {
        Booking booking = getBooking();
        booking.setBookingTime("11.00");
        when(restaurantRepository.findById(anyString())).thenReturn(Optional.of(getRestaurant()));
        when(tableAvlRepo.findByRestaurantAndDate(anyString(),anyString())).thenReturn(getTableAvailability());
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            service.saveBooking(booking);
        });
        assertNotNull(exception);
        assertEquals(TIME_IS_ALREADY_BOOKED, exception.getMessage());
    }
    @DisplayName("cancelBooking")
    @Test
    public void cancelBooking() {
        String bookingId = "1";
        when(repository.findById(anyString())).thenReturn(Optional.of(getBooking()));
        when(tableAvlRepo.findByRestaurantAndDate(anyString(),anyString())).thenReturn(getTableAvailability());
        String  actual = service.cancelBooking(bookingId);
        assertNotNull(actual);
        assertEquals(BOOKING_CANCELED_SUCCESSFULLY, actual);
    }

    @DisplayName("updateBooking")
    @Test
    public void updateBooking() {
        Booking booking = getBooking();
        when(repository.findById(anyString())).thenReturn(Optional.of(getBooking()));
        String  actual = service.updateBooking(booking);
        assertNotNull(actual);
    }

}
