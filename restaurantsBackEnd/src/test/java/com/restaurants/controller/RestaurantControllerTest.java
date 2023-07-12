package com.restaurants.controller;

import com.restaurants.impl.RestaurantServiceImpl;
import com.restaurants.model.Restaurant;
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
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {
  public static final String API_V_1_RESTAURANTS = "/api/v1/restaurants/";
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private RestaurantServiceImpl service;
  Restaurant restaurant = new Restaurant();
  @Test
  public void getAllRestaurants() throws Exception  {
    List<Restaurant> restaurants = Arrays.asList(restaurant);
    when(service.getRestaurants()).thenReturn(restaurants);
    mockMvc.perform(MockMvcRequestBuilders
            .get(API_V_1_RESTAURANTS)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  public void getRestaurants() throws Exception  {
    String id = "1";
    when(service.getRestaurantById(id)).thenReturn(restaurant);
    mockMvc.perform(MockMvcRequestBuilders
                    .get(API_V_1_RESTAURANTS+id)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }
  @Test
  public void saveRestaurants() throws Exception  {
    when(service.saveRestaurant(restaurant)).thenReturn(restaurant);
    mockMvc.perform(MockMvcRequestBuilders
            .post(API_V_1_RESTAURANTS)
            .content(asJsonString(restaurant))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated());
  }
  @Test
  public void updateRestaurants() throws Exception  {
    String id = "1";
    restaurant.setId(id);
    when(service.updateRestaurants(restaurant)).thenReturn("SUCCESS");
    mockMvc.perform(MockMvcRequestBuilders
                    .put(API_V_1_RESTAURANTS)
                    .content(asJsonString(restaurant))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }
  @Test
  public void cancelBooking() throws Exception  {
    String restId = "1";
    when(service.deleteRestaurantById(restId)).thenReturn("deleted");
    mockMvc.perform(MockMvcRequestBuilders
                    .delete(API_V_1_RESTAURANTS +restId)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }


}