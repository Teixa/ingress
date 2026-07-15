package com.teixaa.customer.service.impl;

import com.teixaa.customer.dto.CustomerDto;
import com.teixaa.customer.entity.Customer;
import com.teixaa.customer.exception.CustomerAlreadyExistsException;
import com.teixaa.customer.exception.ResourceNotFoundException;
import com.teixaa.customer.mapper.CustomerMapper;
import com.teixaa.customer.repository.CustomerRepository;
import com.teixaa.customer.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    CustomerRepository customerRepository;

    @Override
    public CustomerDto fetchCustomerDetails(String email) {

        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "email", email)
        );
        return CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
    } //adicionar dps os campos de data

    @Override
    public void createCustomer(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(customerDto.getEmail());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with given email " + customerDto.getEmail());
        }
        customerRepository.save(customer);
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) {
        boolean isUpdated = false;
        Customer customer = customerRepository.findByEmail(customerDto.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "email", customerDto.getEmail())
        );
        customerRepository.save(customer);
        isUpdated = true;
        return isUpdated;
    }


}
