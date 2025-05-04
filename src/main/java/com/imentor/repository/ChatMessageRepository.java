package com.imentor.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.imentor.model.ChatMessage;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findBySessionIdOrderByTimestampAsc(String sessionId);
}