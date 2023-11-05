package com.kokoro.accountservice.dto.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private String name;

    private String password;

    private String email;

    private String mobileNumber;
    private AccountDto accountDto;
}
