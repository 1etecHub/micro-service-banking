package com.kokoro.accountservice.service.client;

import com.kokoro.accountservice.dto.request.CardsDto;
import com.kokoro.accountservice.dto.request.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "loans",fallback = LoansFallback.class)
public interface LoansFeignClient {

    @GetMapping(value = "/api/loan/fetch",consumes = "application/json")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestHeader("kokorobank-correlation-id") String correlationId,
                                                     @RequestParam String mobileNumber);
}
