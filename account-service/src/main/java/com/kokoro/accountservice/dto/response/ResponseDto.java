package com.kokoro.accountservice.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto {


    private String statusCode;

    private String statusMessage;
}
