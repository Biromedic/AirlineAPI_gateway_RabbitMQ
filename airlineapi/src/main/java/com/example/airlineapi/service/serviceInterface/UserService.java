package com.example.airlineapi.service.serviceInterface;

import com.example.airlineapi.model.User;

public interface UserService {
    User getUserById(Long userId);
}