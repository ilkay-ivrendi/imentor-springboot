package com.imentor.controller;

import com.imentor.dto.TTSRequestDTO;
import com.imentor.dto.TTSResponseDTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/tts")
public class TTSController {

    @Value("${tts.server.url}")
    private String ttsServerUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/synthesize")
    public ResponseEntity<TTSResponseDTO> synthesize(@RequestBody TTSRequestDTO request) {
        String url = UriComponentsBuilder.fromUriString(ttsServerUrl + "/tts")
                                         .build()
                                         .toString();
    
        try {
            ResponseEntity<TTSResponseDTO> response = restTemplate.postForEntity(url, request, TTSResponseDTO.class);
            
            // Extract only the file path and adjust it to be accessible by the frontend
            String audioPath = response.getBody().getAudioPath().replace("outputs/chat/", "");
            String publicUrl = "http://localhost:8080/audio/" + audioPath;
    
            // Return the full URL path
            TTSResponseDTO modifiedResponse = new TTSResponseDTO();
            modifiedResponse.setAudioPath(publicUrl);
    
            return ResponseEntity.ok(modifiedResponse);
        } catch (Exception e) {
            throw new RuntimeException("Error communicating with TTS service: " + e.getMessage());
        }
    }
    
}
