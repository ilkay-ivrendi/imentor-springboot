package com.imentor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRequestDTO {
    private String userId;
    private String mentorId;
    private String message;
    private String sessionId;
    private boolean stream;
}
