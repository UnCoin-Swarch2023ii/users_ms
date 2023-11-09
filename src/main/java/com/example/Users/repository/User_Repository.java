package com.example.Users.repository;

import com.example.Users.entity.User ; // Import the MongoDB entity
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface User_Repository extends MongoRepository<User, String> {
    Optional<User> findUserByDocumentIs(int document);
}
