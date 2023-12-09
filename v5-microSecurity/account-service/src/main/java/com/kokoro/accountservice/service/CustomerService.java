package com.kokoro.accountservice.service;

import com.kokoro.accountservice.dto.request.CustomerDetailsDto;

public interface CustomerService {

    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}
