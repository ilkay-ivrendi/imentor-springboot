package com.imentor.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "mentors")
public class Mentor {
    
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
