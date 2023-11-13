package com.kokoro.accountservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(
        name = "Account",
        description = "Schema to hold account information"
)
public class AccountDto {

    @NotEmpty(message = "Account number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    @Schema(
            description = "Account number of Kokorobank user"
    )
    private Long accountNumber;


    @Schema(
            description = "Account type of Kokorobank user",
            example = "1234567890"
    )
    private String accountType;


    @Schema(
            description = "Branch address of Kokorobank",
            example = "14, Ajah Lagos"
    )
    @NotEmpty(message = "Branch Address cannot be -null or empty")
    private String branchAddress;
}
