package com.example.airlineapi.exception;

public class ErrorMessages {
    public static final String USER_NOT_FOUND = "User not found with id: %d";
    public static final String ROLE_NOT_FOUND = "Role not found: %s";
    public static final String BAD_CREDENTIALS = "Invalid email or password";

    private ErrorMessages() {
    }
}
