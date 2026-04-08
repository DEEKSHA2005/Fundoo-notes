package com.bridgelabz.fundoonotes.controller;

import com.bridgelabz.fundoonotes.dto.request.UserRegisterRequestDto;
import com.bridgelabz.fundoonotes.dto.request.LoginRequestDto;
import com.bridgelabz.fundoonotes.dto.response.UserResponseDto;
import com.bridgelabz.fundoonotes.dto.response.LoginResponseDto;
import com.bridgelabz.fundoonotes.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Register API
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(
            @Valid @RequestBody UserRegisterRequestDto requestDto) {

        UserResponseDto response = userService.register(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Login API
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto requestDto) {

        LoginResponseDto response = userService.login(requestDto);
        return ResponseEntity.ok(response);
    }
}