package com.imentor.dto;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class MentorDTO {

    @Id
    private String id;
    private String name;
    private String branch;
    private String description;
    private String systemPrompt;
    private List<String> lessons;
    private String mentorAvatar;
    private String voiceIntroUrl;
    private String voiceId;
}