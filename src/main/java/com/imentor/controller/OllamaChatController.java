package com.imentor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imentor.dto.ChatRequestDTO;
import com.imentor.model.Mentor;
import com.imentor.repository.MentorRepository;
import com.imentor.service.OllamaChatService;

@RestController
@RequestMapping("/api/v1/ollamachat")
public class OllamaChatController {

    @Autowired
    private OllamaChatService ollamaChatService;

    @Autowired
    private MentorRepository mentorRepository;

    // History Teacher Chat Endpoint
    @PostMapping
    public ResponseEntity<?> chatWithMentor(@RequestBody ChatRequestDTO chatRequest) {
        String mentorId = chatRequest.getMentorId();
        Optional<Mentor> optionalMentor = mentorRepository.findById(mentorId);

        if (optionalMentor.isEmpty()) {
            return ResponseEntity.status(404).body("Mentor not found.");
        }

        Mentor mentor = optionalMentor.get();

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of(
                "role", "system",
                "content", mentor.getSystemPrompt()
        ));
        messages.add(Map.of(
                "role", "user",
                "content", chatRequest.getMessage()
        ));

        String response = ollamaChatService.chatWithOllama("llama3.2", messages, chatRequest.isStream());
        return ResponseEntity.ok(response);
    }

}
