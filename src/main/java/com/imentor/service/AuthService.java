package com.imentor.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imentor.dto.UserDTO;
import com.imentor.model.User;
import com.imentor.repository.UserRepository;
import com.imentor.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // Register a new user
    @Transactional
    public String registerUser(UserDTO request) {
        // Check if user already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username is already taken!");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already in use!");
        }

        // Create a new User entity
        User user = User.builder()
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Encrypt password
                .userRole(request.getUserRole())
                .active(false)
                .build();

        user.prepareForSave();

        // Save user to the database
        userRepository.save(user);

        return jwtService.generateToken(user.getUsername());
    }

    // Login: Authenticate the user and return JWT
    public String authenticateUser(UserDTO userDTO) {
        // Retrieve user from database
        User user = userRepository.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        // Verify password
        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // Generate JWT Token
        // return jwtUtils.generateToken(user.getUsername());
        return jwtService.generateToken(user.getUsername());
    }
}
