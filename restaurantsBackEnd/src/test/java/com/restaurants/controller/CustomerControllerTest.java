package com.restaurants.controller;

import com.restaurants.impl.CustomerServiceImpl;
import com.restaurants.model.Customer;
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

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
  public static final String API_V_1_CUSTOMERS = "/api/v1/customers/";
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private CustomerServiceImpl service;

  @Test
  public void getAllCustomers() throws Exception  {
    List<Customer> customers = Arrays.asList(new Customer());
    when(service.getCustomers()).thenReturn(customers);
    mockMvc.perform(MockMvcRequestBuilders
            .get(API_V_1_CUSTOMERS)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }
  @Test
  public void getCustomers() throws Exception  {
    String id = "1";
    Customer customers = new Customer();
    when(service.getCustomerById(id)).thenReturn(customers);
    mockMvc.perform(MockMvcRequestBuilders
            .get(API_V_1_CUSTOMERS+id)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  public void saveCustomer() throws Exception  {
    Customer customer = new Customer();
    when(service.saveCustomer(customer)).thenReturn(customer);
    mockMvc.perform(MockMvcRequestBuilders
                    .post(API_V_1_CUSTOMERS)
                    .content(asJsonString(customer))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated());
  }
  @Test
  public void updateCustomer() throws Exception  {
    String id = "1";
    Customer customer = new Customer();
    customer.setId(id);
    when(service.updateCustomer(customer)).thenReturn("SUCCESS");
    mockMvc.perform(MockMvcRequestBuilders
                    .put(API_V_1_CUSTOMERS)
                    .content(asJsonString(customer))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }
 
}