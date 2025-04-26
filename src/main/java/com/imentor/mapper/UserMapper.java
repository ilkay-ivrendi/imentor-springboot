package com.imentor.mapper;

import com.imentor.dto.UserDTO;
import com.imentor.model.User;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .password(null) // for security we set it to null at response
                .age(user.getAge())
                .dateOfBirth(user.getDateOfBirth())
                .avatarUrl(user.getAvatarUrl())
                .userRole(user.getUserRole())
                .active(user.isActive())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .fullName(userDTO.getFullName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .age(userDTO.getAge())
                .dateOfBirth(userDTO.getDateOfBirth())
                .avatarUrl(userDTO.getAvatarUrl())
                .userRole(userDTO.getUserRole())
                .active(userDTO.isActive())
                .createdAt(userDTO.getCreatedAt())
                .build();
    }
}
