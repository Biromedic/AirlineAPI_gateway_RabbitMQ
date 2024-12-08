package com.example.airlineapi.controller;

import com.example.airlineapi.model.User;
import com.example.airlineapi.service.serviceInterface.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User API", description = "Operations related to user management")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get user by ID", description = "Retrieves a user by their unique ID. Accessible only to admins.")
    public ResponseEntity<User> getUserById(
            @PathVariable @Parameter(description = "ID of the user to retrieve", required = true) Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
}
