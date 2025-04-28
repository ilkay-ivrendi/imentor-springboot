package com.imentor.dto;

import java.util.List;

import lombok.Data;

@Data
public class MentorDTO {

    private String name;
    private String branch;
    private String description;
    private String systemPrompt;
    private List<String> lessons;
    private String mentorAvatar;
}