package com.imentor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imentor.dto.UserDTO;
import com.imentor.model.User;
import com.imentor.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Register a new user
    @Transactional
    public String registerUser(UserDTO userDTO) {
        // Check if user already exists
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("Username is already taken!");
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email is already in use!");
        }

        // Create a new User entity
        User user = User.builder()
                .username(userDTO.getUsername())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword())) // Encrypt password
                .userRole(userDTO.getUserRole())
                .active(false)
                .build();

        // Save user to the database
        userRepository.save(user);

        return "User registered successfully!";
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
        return "jwt_token";
    }
}
