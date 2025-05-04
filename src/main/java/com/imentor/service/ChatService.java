package com.imentor.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imentor.dto.ChatRequestDTO;
import com.imentor.model.ChatMessage;
import com.imentor.model.ChatSession;
import com.imentor.model.Mentor;
import com.imentor.repository.ChatMessageRepository;
import com.imentor.repository.ChatSessionRepository;
import com.imentor.repository.MentorRepository;
import com.imentor.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatService {

    @Autowired
    private ChatSessionRepository sessionRepo;
    @Autowired
    private ChatMessageRepository messageRepo;
    @Autowired
    private MentorRepository mentorRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private OllamaChatService ollama;

    public String handleChat(ChatRequestDTO dto) {
        userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + dto.getUserId() + " not found"));
        mentorRepo.findById(dto.getMentorId())
                .orElseThrow(() -> new IllegalArgumentException("Mentor with ID " + dto.getMentorId() + " not found"));

        // 1. Get or create session
        ChatSession session = getOrCreateSession(dto.getUserId(), dto.getMentorId());

        // 2. Save user message
        saveMessage(session.getSessionId(), "user", dto.getMessage());

        // 3. Get system prompt
        String systemPrompt = mentorRepo.findByMentorId(dto.getMentorId())
                .map(Mentor::getSystemPrompt)
                .orElse("You are a helpful mentor.");

        log.info("Mentor is Working: {}", systemPrompt);

        // 4. Build message history
        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", systemPrompt));
        messageRepo.findBySessionIdOrderByTimestampAsc(session.getSessionId()).forEach(m -> messages
                .add(Map.of("role", m.getSender().equals("user") ? "user" : "assistant", "content", m.getContent())));

        // 5. Generate response
        String reply = ollama.chatWithOllama("tinyllama", messages, dto.isStream());

        // 6. Save assistant message
        saveMessage(session.getSessionId(), "assistant", reply);

        return reply;
    }

    private ChatSession getOrCreateSession(String userId, String mentorId) {
        return sessionRepo.findByUserIdAndMentorId(userId, mentorId)
                .orElseGet(() -> {
                    ChatSession newSession = new ChatSession();
                    newSession.setUserId(userId);
                    newSession.setMentorId(mentorId);
                    newSession.setCreatedAt(LocalDateTime.now());
                    newSession.setUpdatedAt(LocalDateTime.now());
                    return sessionRepo.save(newSession);
                });
    }

    private void saveMessage(String sessionId, String sender, String content) {
        ChatMessage msg = new ChatMessage();
        msg.setSessionId(sessionId);
        msg.setSender(sender);
        msg.setContent(content);
        msg.setTimestamp(LocalDateTime.now());
        messageRepo.save(msg);
    }
}
