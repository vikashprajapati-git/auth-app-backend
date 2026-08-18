package com.auth.services.impl;

import com.auth.dtos.UserRequestDto;
import com.auth.dtos.UserResponseDto;
import com.auth.services.AuthService;
import com.auth.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        return userService.createUser(userRequestDto);
    }
}
