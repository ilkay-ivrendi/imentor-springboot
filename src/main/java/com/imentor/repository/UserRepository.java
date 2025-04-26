package com.imentor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.imentor.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
