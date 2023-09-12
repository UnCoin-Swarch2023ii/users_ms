package com.example.Users.service;
import com.example.Users.entity.Authentication_Token;
import com.example.Users.entity.Authentication_Token_Company;
import com.example.Users.entity.Company;
import com.example.Users.entity.User;
import com.example.Users.repository.Token_Company;
import com.example.Users.repository.Token_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class Authentication_Service {
    @Autowired Token_Repository tokenRepository;
    Token_Company tokenCompany;


    public void saveConfirmationToken(Authentication_Token authenticationToken) {
        System.out.println(authenticationToken);
        this.tokenRepository.save(authenticationToken);
    }
    public void saveConfirmationTokenCompany(Authentication_Token_Company authenticationToken) {
        tokenCompany.save(authenticationToken);
    }
    public Authentication_Token getToken(User user) {
        return tokenRepository.findByUser(user);
    }
    public Authentication_Token_Company getTokenCompany(Company company) {
        return tokenCompany.findByCompany(company);
    }
}
