package com.example.Users.controller;

import com.example.Users.entity.Company;
import com.example.Users.response.ResponseDto;
import com.example.Users.response.ResponseSign;
import com.example.Users.service.Company_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth-api/company")
public class Company_Controller {
    @Autowired
    Company_Service companyService;
    @PostMapping("/signup")
    public ResponseSign signup(@RequestBody Company signup) {
        return companyService.signUpCompany(signup);
    }
    @PostMapping("/signin")
    public ResponseSign signIn(@RequestBody Company signInDto) {
        return companyService.signInUser(signInDto);
    }
    @GetMapping("/get/{document}")
    public Company getcompany(@PathVariable int document) {
        return (Company)  companyService.loadCompanyByUsername(document);
    }

}