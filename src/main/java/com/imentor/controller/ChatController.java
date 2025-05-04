package com.imentor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imentor.dto.ChatRequestDTO;
import com.imentor.service.ChatService;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {
    @Autowired private ChatService chatService;

    @PostMapping
    public ResponseEntity<String> chat(@RequestBody ChatRequestDTO dto) {
        String reply = chatService.handleChat(dto);
        return ResponseEntity.ok(reply);
    }
}
