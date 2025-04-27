package com.imentor.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OllamaChatService {

    private String ollamaApiUrl;
    private String ollamaApiToken;

    private final RestTemplate restTemplate;

    public OllamaChatService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

     public String sendChatMessage(String message) {
        // Prepare the API URL (endpoint where the chat service is available)
        String url = ollamaApiUrl + "/chat";

        // Set up HTTP headers (e.g., Authorization or other required headers)
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + ollamaApiToken);

        // Set the request body (message to send)
        String requestBody = "{\"message\": \"" + message + "\"}"; // Assuming JSON format

        // Create the HTTP request entity
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Send the POST request to the Ollama API
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        // Return the response from Ollama
        return response.getBody(); // The response will depend on Ollama's API (usually a JSON response)
    }
}
