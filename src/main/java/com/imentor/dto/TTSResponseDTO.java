package com.imentor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TTSResponseDTO {
    private String audioPath;
    private String message;
}
