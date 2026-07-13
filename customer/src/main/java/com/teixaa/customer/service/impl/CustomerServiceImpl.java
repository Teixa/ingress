package com.teixaa.customer.service.impl;

import com.teixaa.customer.dto.CustomerDto;
import com.teixaa.customer.entity.Customer;
import com.teixaa.customer.mapper.CustomerMapper;
import com.teixaa.customer.repository.CustomerRepository;
import com.teixaa.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    @Override
    public CustomerDto fetchCustomerDetails(String email) {

        Customer customer = customerRepository.findByEmail(email);
        return CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
    } //adicionar dps os campos de data

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Customer saved = customerRepository.save(customer);
        return CustomerMapper.mapToCustomerDto(saved, new CustomerDto());
    }


}
