package com.auth.controllers;

import com.auth.dtos.UserRequestDto;
import com.auth.dtos.UserResponseDto;
import com.auth.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto){
        return  ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequestDto));
    }

    @GetMapping
    public ResponseEntity<Iterable<UserResponseDto>> getAllUsers(){
        return  ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email){
        return  ResponseEntity.status(HttpStatus.OK).body(userService.getUserByEmail(email));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserResponseDto userRequestDto, @PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userRequestDto,userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(userId));
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable String userId){
        return  ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
    }

}
