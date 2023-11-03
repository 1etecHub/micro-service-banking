package com.kokoro.accountservice.controller;

import com.kokoro.accountservice.constants.AccountConstants;
import com.kokoro.accountservice.dto.request.CustomerDto;
import com.kokoro.accountservice.dto.response.ResponseDto;
import com.kokoro.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AccountController {

    private AccountService accountService;

    @PostMapping ("/greet ")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
        accountService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }


}
