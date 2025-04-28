package com.imentor.dto;

import com.imentor.common.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {

    private String username;
    private String email;
    private UserRole userRole;
    private String token;

}
