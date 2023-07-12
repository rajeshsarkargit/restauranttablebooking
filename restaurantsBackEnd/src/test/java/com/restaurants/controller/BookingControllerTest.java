package com.restaurants.controller;

import com.restaurants.service.impl.BookingServiceImpl;
import com.restaurants.model.Booking;
import com.restaurants.model.BookingWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static com.restaurants.utils.TestUtils.asJsonString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingController.class)
public class BookingControllerTest {
  public static final String API_V_1_BOOKINGS = "/api/v1/bookings/";
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private BookingServiceImpl service;
  Booking booking = new Booking();
  @Test
  public void getAllGetBookings() throws Exception  {
    List<BookingWrapper> wrappers = Arrays.asList(new BookingWrapper());
    when(service.getBookings()).thenReturn(wrappers);
    mockMvc.perform(MockMvcRequestBuilders
            .get(API_V_1_BOOKINGS)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  public void getBookingByIdPhoneNo() throws Exception  {
    String phoneNo = "1";
    List<BookingWrapper> wrappers = Arrays.asList(new BookingWrapper());
    when(service.getBookingByIdPhoneNo(phoneNo)).thenReturn(wrappers);
    mockMvc.perform(MockMvcRequestBuilders
                    .get(API_V_1_BOOKINGS +phoneNo)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }
  @Test
  public void saveRestaurants() throws Exception  {
    when(service.saveBooking(booking)).thenReturn(booking);
    mockMvc.perform(MockMvcRequestBuilders
            .post(API_V_1_BOOKINGS)
            .content(asJsonString(booking))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated());
  }
  @Test
  public void updateRestaurants() throws Exception  {
    String id = "1";
    booking.setId(id);
    when(service.updateBooking(booking)).thenReturn("SUCCESS");
    mockMvc.perform(MockMvcRequestBuilders
                    .put(API_V_1_BOOKINGS)
                    .content(asJsonString(booking))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }
  @Test
  public void cancelBooking() throws Exception  {
    String bookingId = "1";
    when(service.cancelBooking(bookingId)).thenReturn("deleted");
    mockMvc.perform(MockMvcRequestBuilders
                    .delete(API_V_1_BOOKINGS +bookingId)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }

}