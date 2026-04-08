package com.bridgelabz.fundoonotes.service.impl;

import com.bridgelabz.fundoonotes.dto.request.LoginRequestDto;
import com.bridgelabz.fundoonotes.dto.request.UserRegisterRequestDto;
import com.bridgelabz.fundoonotes.dto.response.LoginResponseDto;
import com.bridgelabz.fundoonotes.dto.response.UserResponseDto;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.UserService;
import com.bridgelabz.fundoonotes.util.TokenUtil;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenUtil tokenUtil;

    public UserServiceImpl(UserRepository userRepository, TokenUtil tokenUtil) {
        this.userRepository = userRepository;
        this.tokenUtil = tokenUtil;
    }

    @Override
    public UserResponseDto register(UserRegisterRequestDto requestDto) {

        Optional<User> existingUser = userRepository.findByEmail(requestDto.getEmail());

        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setFirstName(requestDto.getFirstName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());

        User savedUser = userRepository.save(user);

        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getEmail()
        );
    }

    @Override
    public LoginResponseDto login(LoginRequestDto requestDto) {

        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(requestDto.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = tokenUtil.generateToken(user.getId());

        return new LoginResponseDto(token, "Login successful");
    }
}