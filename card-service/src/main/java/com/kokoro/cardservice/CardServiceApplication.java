package com.kokoro.cardservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.kokoro.cardservices.controller") })
@EnableJpaRepositories("com.kokoro.cardservices.repository")
@EntityScan("com.kokoro.cardservices.entity")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "KokoroBank Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Ebenezer Irene",
						email = "KokoroBank@gmail.com",
						url = "https://www.kokorobank.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.kokorobank.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "KokoroBank Cards microservice REST API Documentation",
				url = "https://www.kokorobank.com/swagger-ui.html"
		)
)
public class CardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardServiceApplication.class, args);
	}

}
