package com.example.Users.repository;

import com.example.Users.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.Users.entity.*;
import java.util.Optional;
@Repository
@Transactional(readOnly = true)
public interface Company_Repository extends JpaRepository<Company, Long> {

    Optional<Company> findCompanyByDocumentIs(int document);


}


