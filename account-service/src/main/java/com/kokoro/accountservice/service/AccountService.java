package com.kokoro.accountservice.service;

import com.kokoro.accountservice.dto.request.CustomerDto;

public interface AccountService {

    void createCustomer(CustomerDto customerDto);
}
