package com.kokoro.accountservice.service.ServiceImplementation;

import com.kokoro.accountservice.constants.AccountConstants;
import com.kokoro.accountservice.dto.request.CustomerDto;
import com.kokoro.accountservice.entity.Account;
import com.kokoro.accountservice.entity.Customer;
import com.kokoro.accountservice.exception.CustomerAlreadyExistsException;
import com.kokoro.accountservice.mapper.CustomerMapper;
import com.kokoro.accountservice.repository.AccountRepository;
import com.kokoro.accountservice.repository.CustomerRepository;
import com.kokoro.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createCustomer(CustomerDto customerDto){
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> customerOptional =customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(customerOptional.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with the number entered "+customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("anonymous");
        return newAccount;
    }
}














