package com.example.Users.controller;

import com.example.Users.entity.User;
import com.example.Users.response.ResponseDto;
import com.example.Users.service.User_Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class User_Controller {
    @Autowired
    User_Service userService;
    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody User signup) {
        return userService.signUpUser(signup);
    }
    @PostMapping("/signin")
    public ResponseDto signIn(@RequestBody User signIn) {
        return userService.signInUser(signIn);
    }
    @GetMapping("/get/{document}")
    public User getuser(@PathVariable int document) {
        return (User) userService.loadUserByID(document);
    }
    @PutMapping("/update/{document}")
    public ResponseDto updateUserByDocument( @RequestBody User updatedUser) {
        return userService.updateUser(updatedUser);
    }

    // Endpoint to delete a user by document
    @DeleteMapping("/delete/{document}")
    public ResponseDto deleteUserByDocument(@PathVariable int document) {

        return userService.deleteUser(document);
    }
    }

