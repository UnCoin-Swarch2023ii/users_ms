package com.example.Users.repository;
import com.example.Users.entity.Authentication_Token_Company;
import com.example.Users.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Users.entity.*;
public interface Token_Company extends JpaRepository<Authentication_Token_Company, Integer>{
    Authentication_Token_Company findByCompany(Company company);
}
