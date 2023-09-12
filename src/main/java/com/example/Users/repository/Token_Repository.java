package com.example.Users.repository;
import com.example.Users.entity.Authentication_Token;
import com.example.Users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Token_Repository  extends JpaRepository<Authentication_Token, Integer>{
    Authentication_Token findByUser(User user);
}




