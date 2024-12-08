package com.example.airlineapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@OpenAPIDefinition(info = @Info(title = "Airline API", version = "1.0", description = "API documentation for the Airline API"))
public class AirlineApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineApiApplication.class, args);
	}

}
