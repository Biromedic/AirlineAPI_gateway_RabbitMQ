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
                        .uri("http://airlineapi:8087"))
                // Ticket Service
                .route("ticket_service", r -> r.path("/api/tickets/**")
                        .uri("http://airlineapi:8087"))
                // Listing Service
                .route("listing_service", r -> r.path("/api/listings/**")
                        .uri("http://airlineapi:8087"))
                // Flight Service
                .route("flight_service", r -> r.path("/api/flights/**")
                        .uri("http://airlineapi:8087"))
                // Booking Service
                .route("booking_service", r -> r.path("/api/bookings/**")
                        .uri("http://airlineapi:8087"))
                // Authentication Service
                .route("auth_service", r -> r.path("/api/auth/**")
                        .uri("http://airlineapi:8087"))
                .route("message_service", r -> r.path("/api/messages/**")
                        .uri("http://messaging:8088"))
                .route("payment_service", r -> r.path("/api/payments/**")
                        .uri("http://paymentservice:8090"))
                .route("notification_service", r -> r.path("/api/notifications/**")
                        .uri("http://notificationservice:8091"))
                .build();
    }
}
