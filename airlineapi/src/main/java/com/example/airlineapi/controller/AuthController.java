package com.example.airlineapi.controller;

import com.example.airlineapi.security.request.LoginRequest;
import com.example.airlineapi.security.request.SignupRequest;
import com.example.airlineapi.security.response.MessageResponse;
import com.example.airlineapi.security.response.UserInfoResponse;
import com.example.airlineapi.security.service.securityServiceInterface.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication and authorization")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Register a new user", description = "Endpoint for user registration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Email already in use")
    })
    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }

    @Operation(summary = "User login", description = "Endpoint for user login to obtain JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    @PostMapping("/signin")
    public ResponseEntity<Map<String, Object>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @Operation(summary = "Admin login", description = "Endpoint for admin login to obtain JWT")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    @PostMapping("/admin/signin")
    public ResponseEntity<Map<String, Object>> authenticateAdmin(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateAdmin(loginRequest);
    }

    @Operation(summary = "Get user details", description = "Endpoint to retrieve details of the logged-in user")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User details retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access")
    })
    @GetMapping("/user")
    public ResponseEntity<UserInfoResponse> getUserDetails(Authentication authentication) {
        return authService.getUserDetails(authentication);
    }

    @Operation(summary = "Sign out user", description = "Endpoint to clear the JWT cookie and sign out the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User signed out successfully")
    })
    @PostMapping("/signout")
    public ResponseEntity<MessageResponse> signoutUser() {
        return authService.signoutUser();
    }

    @Operation(summary = "Check user authentication", description = "Endpoint to check if the user is authenticated via JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication status retrieved successfully")
    })
    @GetMapping("/check")
    public ResponseEntity<?> checkAuth(HttpServletRequest request) {
        boolean isAuthenticated = authService.isAuthenticated(request);
        return ResponseEntity.ok().body(Map.of("isAuthenticated", isAuthenticated));
    }
}
