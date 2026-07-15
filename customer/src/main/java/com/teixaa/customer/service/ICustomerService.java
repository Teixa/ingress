package com.teixaa.customer.service;

import com.teixaa.customer.dto.CustomerDto;

public interface ICustomerService {

    /**
     * @param email
     * input email
     * @return Details of the customer
     */
    CustomerDto fetchCustomerDetails(String email);

    void createCustomer(CustomerDto customerDto);

    boolean updateCustomer(CustomerDto customerDto);
}
