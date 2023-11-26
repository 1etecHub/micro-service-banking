package com.kokoro.accountservice.service;

import com.kokoro.accountservice.dto.request.CustomerDto;

public interface AccountService {

    void createCustomer(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
