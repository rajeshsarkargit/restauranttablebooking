package com.restaurants.service;

import com.restaurants.service.impl.CustomerServiceImpl;
import com.restaurants.model.Customer;
import com.restaurants.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.restaurants.constants.Constants.CUSTOMER_UPDATED_SUCCESSFULLY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl target;

    @Mock
    private CustomerRepository repository;


    @Test
    public void getRestaurantById() {
        Customer customer = new Customer();
        when(repository.findById(anyString())).thenReturn(Optional.of(customer));
        Customer actual = target.getCustomerById("12345");
        assertNotNull(actual);
    }
    @Test
    public void getRestaurants() {
        List<Customer> customers = Arrays.asList(new Customer());
        when(repository.findAll()).thenReturn(customers);
        List<Customer> actual = target.getCustomers();
        assertNotNull(actual);
        assertEquals(actual.size(),1);
    }
    @Test
    public void saveRestaurant() {
        Customer customer = new Customer();
        when(repository.save(any())).thenReturn(customer);
        Customer actual = target.saveCustomer(customer);
        assertNotNull(actual);
    }
    @Test
    public void updateRestaurants() {
        Customer customer = new Customer();
        customer.setId("1");
        when(repository.findById(any())).thenReturn(Optional.of(customer));
        String actual = target.updateCustomer(customer);
        assertNotNull(actual);
        assertEquals(actual,CUSTOMER_UPDATED_SUCCESSFULLY);
    }


}
