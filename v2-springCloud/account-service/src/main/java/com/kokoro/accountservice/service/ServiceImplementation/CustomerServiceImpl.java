package com.kokoro.accountservice.service.ServiceImplementation;

import com.kokoro.accountservice.dto.request.*;
import com.kokoro.accountservice.entity.Account;
import com.kokoro.accountservice.entity.Customer;
import com.kokoro.accountservice.exception.ResourceNotFoundException;
import com.kokoro.accountservice.mapper.AccountMapper;
import com.kokoro.accountservice.mapper.CustomerMapper;
import com.kokoro.accountservice.repository.AccountRepository;
import com.kokoro.accountservice.repository.CustomerRepository;
import com.kokoro.accountservice.service.CustomerService;
import com.kokoro.accountservice.service.client.CardsFeignClient;
import com.kokoro.accountservice.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Account account = accountRepository.findByCustomerId(customer.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getId().toString()));

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));
        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        return customerDetailsDto;

    }
}
