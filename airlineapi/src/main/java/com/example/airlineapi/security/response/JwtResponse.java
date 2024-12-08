package com.example.airlineapi.security.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
    private String jwtToken;
    private String email;
    private String roles;
}
