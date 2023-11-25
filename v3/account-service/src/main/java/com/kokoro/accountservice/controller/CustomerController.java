package com.kokoro.accountservice.controller;

import com.kokoro.accountservice.dto.request.CustomerDetailsDto;
import com.kokoro.accountservice.dto.request.CustomerDto;
import com.kokoro.accountservice.dto.response.ErrorResponseDto;
import com.kokoro.accountservice.service.CustomerService;
import com.kokoro.accountservice.service.client.CardsFeignClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "REST APIs for Customers in KokoroBank",
        description = "REST APIs in KokoroBank to FETCH customer details"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
@Validated
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;



    @Operation(
            summary = "Fetch customer details REST API",
            description = "REST API to fetch customer details inside Kokoro bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HttpStatus.OK"
            ),

            @ApiResponse(
                    responseCode = "500",
                    description = "Http status internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("kokorobank-correlation-id") String correlationId,
                                                                   @RequestParam
                                                                   @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                                           String mobileNumber){
        logger.debug("kokorobank-correlation-id found:{}", correlationId);
        CustomerDetailsDto customerDetailsDto = customerService.fetchCustomerDetails(mobileNumber,correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}
