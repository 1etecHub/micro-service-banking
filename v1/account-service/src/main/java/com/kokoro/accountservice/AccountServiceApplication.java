package com.kokoro.accountservice;

import com.kokoro.accountservice.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info=@Info(
				title="Account micro service REST API Documentation",
				description = "kokoro Bank Accounts MicroService REST API Documentation",
				version = "v1",
				contact = @Contact(
						name="Ebenezer Irene",
						email = "ebenezerirene7@gmail.com",
						url = "kokorobank.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "kokorobank.com"
				)

		),
		externalDocs = @ExternalDocumentation(
				description = "kokoro bank account microService REST API documentation",
				url = "kokorobank.com"
		)
)
@EnableConfigurationProperties(value = AccountsContactInfoDto.class)
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

}
