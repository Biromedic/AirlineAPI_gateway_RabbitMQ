package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // User Service
                .route("user_service", r -> r.path("/api/users/**")
                        .uri("http://localhost:8081"))
                // Ticket Service
                .route("ticket_service", r -> r.path("/api/tickets/**")
                        .uri("http://localhost:8082"))
                // Listing Service
                .route("listing_service", r -> r.path("/api/listings/**")
                        .uri("http://localhost:8083"))
                // Flight Service
                .route("flight_service", r -> r.path("/api/flights/**")
                        .uri("http://localhost:8084"))
                // Booking Service
                .route("booking_service", r -> r.path("/api/bookings/**")
                        .uri("http://localhost:8085"))
                // Authentication Service
                .route("auth_service", r -> r.path("/api/auth/**")
                        .uri("http://localhost:8086"))
                .route("message_service", r -> r.path("/api/messages/**")
                        .uri("http://localhost:8088"))
                .build();
    }
}
