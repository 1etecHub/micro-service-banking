package com.kokoro.accountservice.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponse {

    private Long accountNumber;

    private String accountType;

    private String branchAddress;
}
