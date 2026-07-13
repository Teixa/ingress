package com.teixaa.customer.service;

import com.teixaa.customer.dto.CustomerDto;

public interface CustomerService {

    /**
     * @param email
     * input email
     * @return Details of the customer
     */
    CustomerDto fetchCustomerDetails(String email);

    CustomerDto createCustomer(CustomerDto customerDto);
}
