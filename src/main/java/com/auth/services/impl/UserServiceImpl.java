package com.auth.services.impl;

import com.auth.dtos.UserRequestDto;
import com.auth.dtos.UserResponseDto;
import com.auth.entities.Provider;
import com.auth.entities.User;
import com.auth.exceptions.ResourceNotFoundException;
import com.auth.helpers.UserHelper;
import com.auth.repositories.UserRepositry;
import com.auth.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositry userRepositry;
    private final ModelMapper modelMapper;
    //private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        if(userRequestDto.getEmail() == null || userRequestDto.getEmail().isEmpty()){
            throw new IllegalArgumentException("Email is required");
        }

        if(userRepositry.existsByEmail(userRequestDto.getEmail())){
            throw new IllegalArgumentException("User with this email already exists");
        }

        if(userRequestDto.getName() == null || userRequestDto.getName().isEmpty()){
            throw new IllegalArgumentException("Name is required");
        }

        if(userRequestDto.getPassword() == null || userRequestDto.getPassword().isEmpty()){
            throw new IllegalArgumentException("Password is required");
        }

        User user= modelMapper.map(userRequestDto, User.class);
        user.setProvider(userRequestDto.getProvider()!=null?userRequestDto.getProvider(): Provider.LOCAL);
        //TODO: Implementaion of each role of the usere
        User savedUser= userRepositry.save(user);
        return modelMapper.map(savedUser, UserResponseDto.class);
    }


    @Override
    @Transactional
    public UserResponseDto updateUser(UserResponseDto userResponseDto, String userId) {
        UUID uid = UserHelper.parseUUID(userId);
        User existingUser= userRepositry.findById(uid).orElseThrow(()->new ResourceNotFoundException("User with this id does not exist"));

        if(userResponseDto.getName() != null || !userResponseDto.getName().isEmpty())
            existingUser.setName(userResponseDto.getName());
        if(userResponseDto.getGender() != null || !userResponseDto.getGender().isEmpty())
            existingUser.setGender(userResponseDto.getGender());
        if(userResponseDto.getImage() != null || !userResponseDto.getImage().isEmpty())
            existingUser.setImage(userResponseDto.getImage());
        existingUser.setEnabled(userResponseDto.isEnabled());
        existingUser.setUpdatedAt(Instant.now());

        userRepositry.save(existingUser);
        return modelMapper.map(existingUser, UserResponseDto.class);
    }

    @Override
    @Transactional
    public String deleteUser(String userId) {
        UUID uid = UserHelper.parseUUID(userId);
        User user= userRepositry.findById(uid).orElseThrow(()->new ResourceNotFoundException("User with this id does not exist"));
        userRepositry.delete(user);
        return "User with this "+ userId+ " has been deleted";
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        User user = userRepositry
                .findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("User not found with email : "+email));
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public UserResponseDto getUserById(String userId) {
        UUID uid = UserHelper.parseUUID(userId);
        User user= userRepositry.findById(uid).orElseThrow(()->new ResourceNotFoundException("User with this id does not exist"));
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    @Transactional
    public Iterable<UserResponseDto> getAllUsers() {
        return userRepositry
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class)).toList();
    }

}
