package com.kokoro.accountservice.controller;

import com.kokoro.accountservice.constants.AccountConstants;
import com.kokoro.accountservice.dto.AccountsContactInfoDto;
import com.kokoro.accountservice.dto.request.CustomerDto;
import com.kokoro.accountservice.dto.response.ErrorResponseDto;
import com.kokoro.accountservice.dto.response.ResponseDto;
import com.kokoro.accountservice.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Accounts in KokoroBank",
        description = "CRUD REST APIs for Accounts in KokoroBank to CREATE, UPDATE, FETCH and DELETE account details"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
@Validated
public class AccountController {

    private AccountService accountService;
    //@Value("${build.version}")
    private String buildVersion;
    private Environment environment;

    private AccountsContactInfoDto accountsContactInfoDto;


    @Operation(
            summary = "Create account REST API",
            description = "REST API to create new customer and account inside Kokoro bank"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HttpStatus.CREATED"
    )
    @PostMapping ("/create ")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        accountService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }



    @Operation(
            summary = "Fetch account REST API",
            description = "REST API to fetch customer and account inside Kokoro bank"
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
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber){
        CustomerDto customerDto =  accountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);

    }


    @Operation(
            summary = "Update account REST API",
            description = "REST API to update customer and account inside Kokoro bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HttpStatusOK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HttpStatus Internal Server Error",
                    //to allow documentation access to the error response dto class
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdated = accountService.updateAccount(customerDto);
        if (isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
        }
    }


    @Operation(
            summary = "Delete account REST API",
            description = "REST API to delete customer and account inside Kokoro bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HttpStatusOK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HttpStatus Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber){
        boolean isDeleted = accountService.deleteAccount(mobileNumber);

        if (isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Get build information REST API",
            description = "REST API to get build information that is deployed into accounts micro service"
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
    @GetMapping("build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @Operation(
            summary = "Get JAVA version information REST API",
            description = "REST API to get java version details that is deployed into accounts micro service"
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
    @GetMapping("java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary = "Get contact information REST API",
            description = "REST API to get contact info details that can be contacted incase of any issues"
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
    @GetMapping("contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(accountsContactInfoDto);
    }








}











































