package com.restaurants.impl;

import com.restaurants.exception.ErrorResponse;
import com.restaurants.exception.ServiceException;
import com.restaurants.model.Customer;
import com.restaurants.repository.CustomerRepository;
import com.restaurants.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.restaurants.constants.Constants.*;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerRepository repository;

    @Override
    public Customer getCustomerById(String customerId) {

        return repository.findById(customerId).get();
    }

    @Override
    public List<Customer> getCustomers() {
        return repository.findAll();
    }


    @Override
    public Customer saveCustomer(Customer customer) {
        Customer existingCustomer = repository.findByPhoneNo(customer.getPhoneNo());
        if(!ObjectUtils.isEmpty(existingCustomer)){
            throw new ServiceException(HttpStatus.BAD_REQUEST,
                    new ErrorResponse(CUSTOMER_ALREADY_EXISTS));
        }
        return repository.save(customer);
    }


    @Override
    public String updateCustomer(Customer customer) {
        String result = PROBLEM_IN_UPDATE_CUSTOMER;
        if (null != customer.getId()) {
            Customer existingCustomer = repository.findById(customer.getId()).get();
            if (null != existingCustomer) {
                repository.save(customer);
                result = CUSTOMER_UPDATED_SUCCESSFULLY;
            }
        }else{
            result = CUSTOMER_ID_IS_MANDATORY_TO_UPDATED_CUSTOMER;
        }
        return result;
    }

}
