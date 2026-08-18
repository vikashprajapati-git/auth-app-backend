package com.auth.services;

import com.auth.dtos.UserRequestDto;
import com.auth.dtos.UserResponseDto;

public interface AuthService {

    UserResponseDto registerUser(UserRequestDto userRequestDto);

}
