package com.imentor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TTSRequestDTO {
    private String voice_id;
    private String mentor_id;
    private String text;
}
