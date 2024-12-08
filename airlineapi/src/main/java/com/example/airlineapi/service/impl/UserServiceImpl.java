package com.example.airlineapi.service.impl;

import com.example.airlineapi.exception.ErrorMessages;
import com.example.airlineapi.exception.ResourceNotFoundException;
import com.example.airlineapi.model.User;
import com.example.airlineapi.repository.UserRepository;
import com.example.airlineapi.service.serviceInterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND, userId)));
    }

}
