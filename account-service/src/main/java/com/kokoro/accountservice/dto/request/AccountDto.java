package com.kokoro.accountservice.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {

    private Long accountNumber;

    private String accountType;

    private String branchAddress;
}
