package com.imentor.dto;

import java.time.LocalDate;

import com.imentor.common.enums.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String id;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    private String fullName;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private Integer age;
    private LocalDate dateOfBirth;

    private String avatarUrl;

    @NotNull(message = "User role must be provided")
    private UserRole userRole;

    private boolean active;
    private LocalDate createdAt;
}
