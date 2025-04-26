package com.imentor.dto;

import java.time.LocalDate;

import com.imentor.common.enums.UserRole;

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

    private String username;
    private String firstName;
    private String lastName;
    private String fullName;

    private String email;

    private Integer age;
    private LocalDate dateOfBirth;

    private String avatarUrl;
    private UserRole userRole;

    private boolean active;
    private LocalDate createdAt;
}
