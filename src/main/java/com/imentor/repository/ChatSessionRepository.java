package com.imentor.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.imentor.model.ChatSession;

public interface ChatSessionRepository extends MongoRepository<ChatSession, String> {
    Optional<ChatSession> findByUserIdAndMentorId(String userId, String mentorId);
}
