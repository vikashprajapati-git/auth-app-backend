package com.auth.services;

import com.auth.dtos.UserRequestDto;
import com.auth.dtos.UserResponseDto;


public interface UserService {

    //create user
    UserResponseDto createUser(UserRequestDto userRequestDto);

    //update user
    UserResponseDto updateUser(UserResponseDto userResponseDto, String userId);

    //delete user
    String deleteUser(String userId);

    //get user by email
    UserResponseDto getUserByEmail(String email);

    //get user by id
    UserResponseDto getUserById(String userId);

    //get all user
    Iterable<UserResponseDto> getAllUsers();

}
