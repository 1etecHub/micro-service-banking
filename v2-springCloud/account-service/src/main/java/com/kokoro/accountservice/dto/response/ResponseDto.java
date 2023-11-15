package com.kokoro.accountservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
public class ResponseDto {


    @Schema(
            description = "Status code in the response", example = "200"
    )
    private String statusCode;

    @Schema(
            description = "Status code in the response", example = "Request processed successfully"
    )
    private String statusMessage;
}
