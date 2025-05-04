package com.imentor.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@CompoundIndex(name = "user_mentor_idx", def = "{'userId' : 1, 'mentorId': 1}", unique = true)
@Document(collection = "chat_sessions")
public class ChatSession {
    @Id
    private String sessionId;
    private String userId;
    private String mentorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // Getters & Setters
}