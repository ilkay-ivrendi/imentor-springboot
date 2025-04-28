package com.imentor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.imentor.model.Mentor;

public interface MentorRepository extends MongoRepository<Mentor, String> {

}
