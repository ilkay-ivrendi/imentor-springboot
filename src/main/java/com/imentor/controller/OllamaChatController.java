package com.imentor.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imentor.service.OllamaChatService;

@RestController
@RequestMapping("/api/v1/chat")
public class OllamaChatController {

    private OllamaChatService ollamaChatService;

    @PostMapping
    public String getUserById(@PathVariable String message) {
        return ollamaChatService.sendChatMessage(message);
    }
    
}
