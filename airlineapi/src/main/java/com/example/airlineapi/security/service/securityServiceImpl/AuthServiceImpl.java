package com.example.airlineapi.security.service.securityServiceImpl;

import com.example.airlineapi.exception.ErrorMessages;
import com.example.airlineapi.model.Role;
import com.example.airlineapi.model.User;
import com.example.airlineapi.model.enums.AppRole;
import com.example.airlineapi.repository.RoleRepository;
import com.example.airlineapi.repository.UserRepository;
import com.example.airlineapi.security.jwt.JwtUtils;
import com.example.airlineapi.security.request.LoginRequest;
import com.example.airlineapi.security.request.SignupRequest;
import com.example.airlineapi.security.response.MessageResponse;
import com.example.airlineapi.security.response.UserInfoResponse;
import com.example.airlineapi.security.service.UserDetailsImpl;
import com.example.airlineapi.security.service.securityServiceInterface.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    public ResponseEntity<Map<String, Object>> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = performAuthentication(loginRequest);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = extractRoles(userDetails);

        UserInfoResponse response = new UserInfoResponse(userDetails.getId(),
                userDetails.getEmail(), roles, jwtCookie.toString());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(Map.of("userInfo", response));
    }

    @Override
    public ResponseEntity<Map<String, Object>> authenticateAdmin(LoginRequest loginRequest) {
        Authentication authentication = performAuthentication(loginRequest);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = extractRoles(userDetails);

        if (!roles.contains("ROLE_ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", "Unauthorized", "status", false));
        }

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        UserInfoResponse response = new UserInfoResponse(userDetails.getId(),
                userDetails.getEmail(), roles, jwtCookie.toString());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(Map.of("userInfo", response));
    }

    @Override
    public ResponseEntity<MessageResponse> registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail().toLowerCase())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        String role = signUpRequest.getRole() == null || signUpRequest.getRole().isBlank()
                ? "USER" : signUpRequest.getRole();

        Set<Role> roles = new HashSet<>();
        roles.add(getRole(AppRole.valueOf("ROLE_" + role.toUpperCase())));

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @Override
    public ResponseEntity<MessageResponse> signoutUser() {
        SecurityContextHolder.clearContext();
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

    @Override
    public boolean isAuthenticated(HttpServletRequest request) {
        return jwtUtils.getJwtFromCookies(request)
                .map(jwtUtils::validateJwtToken)
                .orElse(false);
    }

    @Override
    public ResponseEntity<UserInfoResponse> getUserDetails(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetailsImpl userDetails)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<String> roles = extractRoles(userDetails);

        UserInfoResponse response = new UserInfoResponse(userDetails.getId(),
                userDetails.getEmail(), roles);

        return ResponseEntity.ok(response);
    }

    private Authentication performAuthentication(LoginRequest loginRequest) {
        try {
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (AuthenticationException exception) {
            throw new BadCredentialsException(ErrorMessages.BAD_CREDENTIALS);
        }
    }

    private Role getRole(AppRole appRole) {
        return roleRepository.findByRoleName(appRole)
                .orElseThrow(() -> new RuntimeException(ErrorMessages.ROLE_NOT_FOUND));
    }

    private List<String> extractRoles(UserDetailsImpl userDetails) {
        return userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    private Set<Role> mapRoles(Set<String> strRoles) {
        return strRoles.stream()
                .map(role -> getRole(AppRole.valueOf("ROLE_" + role.toUpperCase())))
                .collect(Collectors.toSet());
    }
}