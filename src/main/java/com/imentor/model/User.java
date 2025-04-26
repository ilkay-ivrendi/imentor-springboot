package com.imentor.model;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imentor.common.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String username;
    private String firstName;
    private String lastName;
    private String fullName;

    private String email;

    @JsonIgnore
    private String password;

    private Integer age;
    private LocalDate dateOfBirth;

    private String avatarUrl;
    private UserRole userRole;

    private boolean active = false;
    private LocalDate createdAt = LocalDate.now();

    public void prepareForSave() {
        // Auto set fullName
        if (firstName != null && lastName != null) {
            this.fullName = firstName + " " + lastName;
        }
        // Auto set age
        if (dateOfBirth != null) {
            this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();
        }
    }

}
