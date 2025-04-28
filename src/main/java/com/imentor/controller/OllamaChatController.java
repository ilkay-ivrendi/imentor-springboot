package com.imentor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imentor.dto.ChatRequestDTO;
import com.imentor.service.OllamaChatService;

@RestController
@RequestMapping("/api/v1/chat")
public class OllamaChatController {

    @Autowired
    private OllamaChatService ollamaChatService;

    // History Teacher Chat Endpoint
    @PostMapping("/history-teacher")
    public ResponseEntity<String> chatWithHistoryTeacher(@RequestBody ChatRequestDTO chatRequest) {
        List<Map<String, String>> messages = new ArrayList<>();

        // System prompt (persona)
        messages.add(Map.of(
                "role", "system",
                "content",
                "You are a wise and patient history teacher. Always explain historical facts clearly and suggest history topics for the student's age."));

        // User message
        messages.add(Map.of(
                "role", "user",
                "content", chatRequest.getMessage()));

        String response = ollamaChatService.chatWithOllama("llama3.2", messages, chatRequest.isStream());
        return ResponseEntity.ok(response);
    }

    // Music Teacher Chat Endpoint
    @PostMapping("/music-teacher")
    public ResponseEntity<String> chatWithMusicTeacher(@RequestBody ChatRequestDTO chatRequest) {
        List<Map<String, String>> messages = new ArrayList<>();

        messages.add(Map.of(
                "role", "system",
                "content",
                "You are a cheerful music teacher who explains musical concepts in a fun way. Suggest songs and instruments depending on the user's age."));

        messages.add(Map.of(
                "role", "user",
                "content", chatRequest.getMessage()));

        String response = ollamaChatService.chatWithOllama("llama3.2", messages, chatRequest.isStream());
        return ResponseEntity.ok(response);
    }

}
