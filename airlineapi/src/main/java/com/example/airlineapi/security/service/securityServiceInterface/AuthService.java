package com.example.airlineapi.security.service.securityServiceInterface;


import com.example.airlineapi.security.request.LoginRequest;
import com.example.airlineapi.security.request.SignupRequest;
import com.example.airlineapi.security.response.MessageResponse;
import com.example.airlineapi.security.response.UserInfoResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Map;

public interface AuthService {
    ResponseEntity<Map<String, Object>> authenticateUser(LoginRequest loginRequest);

    ResponseEntity<Map<String, Object>> authenticateAdmin(LoginRequest loginRequest);

    ResponseEntity<MessageResponse> registerUser(SignupRequest signUpRequest);

    ResponseEntity<MessageResponse> signoutUser();

    ResponseEntity<UserInfoResponse> getUserDetails(Authentication authentication);

    boolean isAuthenticated(HttpServletRequest request);

}
