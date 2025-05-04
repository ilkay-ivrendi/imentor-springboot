package com.imentor.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.imentor.model.Mentor;

public interface MentorRepository extends MongoRepository<Mentor, String> {
    Optional<Mentor> findByMentorId(String mentorId);
}
