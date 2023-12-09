package com.kokoro.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}


	@Bean
	public RouteLocator kokoroBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder){
		return routeLocatorBuilder.routes()
				.route(p -> p
						.path("/kokorobank/account/**")
						.filters(f -> f.rewritePath("/kokorobank/account/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-response-Time", LocalDateTime.now().toString()))
						.uri("lb:ACCOUNTS"))
				.route(p -> p
						.path("/kokorobank/loans/**")
						.filters(f -> f.rewritePath("/kokorobank/account/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-response-Time", LocalDateTime.now().toString()))
						.uri("lb:LOANS"))
				.route(p -> p
						.path("/kokorobank/cards/**")
						.filters(f -> f.rewritePath("/kokorobank/account/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-response-Time", LocalDateTime.now().toString()))
						.uri("lb:CARDS"))
				.build();
	}

}
