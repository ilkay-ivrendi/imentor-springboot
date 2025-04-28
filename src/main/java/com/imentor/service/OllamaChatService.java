package com.imentor.service;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OllamaChatService {

    @Value("${ollama.api.url}")
    private String ollamaApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String chatWithOllama(String persona, List<Map<String, String>> messages, boolean stream) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("model", persona); // like "llama3"
        payload.put("messages", messages);
        payload.put("stream", stream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(payload, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                URI.create(ollamaApiUrl + "/api/chat"),
                requestEntity,
                String.class);

        return response.getBody();
    }

}
