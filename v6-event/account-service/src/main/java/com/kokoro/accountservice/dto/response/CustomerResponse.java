package com.kokoro.accountservice.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {

    private String name;

    private String password;

    private String email;

    private String mobileNumber;
}
