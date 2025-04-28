package com.imentor.service;

import java.time.LocalDate;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imentor.common.enums.UserRole;
import com.imentor.dto.AuthResponseDTO;
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

    @Transactional
    public AuthResponseDTO registerUser(RegisterRequestDTO registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Username is already taken!");
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email is already in use!");
        }

        log.info("Registering user: {}", registerRequest.getUsername());

        User user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword())) // Encrypt password
                .userRole(UserRole.USER)
                .active(false)
                .createdAt(LocalDate.now())
                .build();

        user.prepareForSave();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername());

        AuthResponseDTO authResponse = new AuthResponseDTO();
        authResponse.setUsername(user.getUsername());
        authResponse.setEmail(user.getEmail());
        authResponse.setUserRole(UserRole.USER);
        authResponse.setToken(token);

        return authResponse;

    }

    public String authenticateUser(LoginRequestDTO loginRequest) {
        log.info("Authenticating user: {}", loginRequest.getUsername());

        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        return jwtService.generateToken(user.getUsername());
    }
}
