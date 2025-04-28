package com.imentor.dto;

import lombok.Data;

@Data
public class ChatRequestDTO {
    private String userId;
    private String message;
    private boolean stream;
}
