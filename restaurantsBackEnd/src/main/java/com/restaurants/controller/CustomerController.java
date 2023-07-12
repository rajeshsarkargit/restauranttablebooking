package com.restaurants.controller;

import com.restaurants.exception.ErrorResponse;
import com.restaurants.exception.ServiceException;
import com.restaurants.model.Customer;
import com.restaurants.ICustomerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.restaurants.constants.Constants.PLEASE_PROVIDE_CUSTOMER_ID;

@RestController
@RequestMapping(value = "/api/v1/customers")
@CrossOrigin("http://localhost:4200/")
public class CustomerController {

    @Autowired
    private ICustomerService service;

    @GetMapping(value = "/")
    public ResponseEntity<List<Customer>> getCustomers() {
         List<Customer> customers = service.getCustomers();
         return new ResponseEntity<>(customers,HttpStatus.OK);
    }

    @GetMapping(value = "/{customerId}")
    public Customer getCustomers(
            @PathVariable(name = "customerId") String customerId) {
        return service.getCustomerById(customerId);
    }

    @PostMapping(value = "/",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> saveCustomers(@RequestBody Customer customer) {
        return new ResponseEntity<>(service.saveCustomer(customer),HttpStatus.CREATED);
    }

    @PutMapping(value = "/")
    public String updateRestaurants(@RequestBody Customer customer) throws Exception {
        if(StringUtils.isEmpty(customer.getId())){
            throw new ServiceException(HttpStatus.BAD_REQUEST,
                    new ErrorResponse(PLEASE_PROVIDE_CUSTOMER_ID));
        }
        return service.updateCustomer(customer);
    }





}
