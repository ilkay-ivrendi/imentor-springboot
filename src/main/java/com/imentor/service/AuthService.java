package com.imentor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imentor.common.enums.UserRole;
import com.imentor.dto.LoginRequestDTO;
import com.imentor.dto.RegisterRequestDTO;

import com.imentor.model.User;
import com.imentor.repository.UserRepository;
import com.imentor.security.JwtService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    
    // Register a new user
    @Transactional
    public String registerUser(RegisterRequestDTO registerRequest) {
        // Check if user already exists
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Username is already taken!");
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email is already in use!");
        }

        log.info("Registering user: {}", registerRequest.getUsername());

        // Create a new User entity
        User user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword())) // Encrypt password
                .userRole(UserRole.USER)
                .active(false)
                .build();

        user.prepareForSave();

        // Save user to the database
        userRepository.save(user);

        return jwtService.generateToken(user.getUsername());
    }

    // Login: Authenticate the user and return JWT
    public String authenticateUser(LoginRequestDTO loginRequest) {
        log.info("Authenticating user: {}", loginRequest.getUsername());
        // Retrieve user from database
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        // Verify password
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // Generate JWT Token
        // return jwtUtils.generateToken(user.getUsername());
        return jwtService.generateToken(user.getUsername());
    }
}
