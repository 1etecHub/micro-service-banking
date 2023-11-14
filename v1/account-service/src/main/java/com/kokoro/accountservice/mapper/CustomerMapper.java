package com.kokoro.accountservice.mapper;

import com.kokoro.accountservice.dto.request.AccountDto;
import com.kokoro.accountservice.dto.request.CustomerDto;
import com.kokoro.accountservice.entity.Account;
import com.kokoro.accountservice.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPassword(customer.getPassword());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer){
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
