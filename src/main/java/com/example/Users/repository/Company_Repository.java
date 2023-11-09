package com.example.Users.repository;


import com.example.Users.entity.Company; // Import the MongoDB entity
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Company_Repository extends MongoRepository<Company, String> {
    Optional<Company> findCompanyByNITIs(int NIT);
}





