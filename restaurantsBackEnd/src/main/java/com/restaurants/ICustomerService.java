package com.restaurants;

import com.restaurants.model.Customer;
import com.restaurants.model.Restaurant;

import java.util.List;

public interface ICustomerService {

    Customer getCustomerById(String id);

    List<Customer> getCustomers();

    Customer saveCustomer(Customer restaurant);

    String updateCustomer(Customer restaurant);

}
