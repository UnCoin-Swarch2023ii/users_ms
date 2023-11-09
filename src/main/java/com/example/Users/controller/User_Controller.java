package com.example.Users.controller;
import com.example.Users.entity.User ;
import com.example.Users.response.ResponseDto;
import com.example.Users.response.ResponseSign;
import com.example.Users.service.User_Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin
@RequestMapping("/auth-api/user")
public class User_Controller {
    @Autowired
    User_Service userService;
    @PostMapping("/signup")
    public ResponseSign signup(@RequestBody User signup) {
        return userService.signUpUser(signup);
    }
    @PostMapping("/signin")
    public ResponseSign signIn(@RequestBody User signIn) {
        return userService.signInUser(signIn);
    }
    @GetMapping("/get/{document}")
    public User getuser(@PathVariable int document) {
        return (User) userService.loadUserByID(document);
    }
    @PutMapping("/update/{document}")
    public ResponseDto updateUserByDocument( @PathVariable int document, @RequestBody User updatedUser) {
        updatedUser.setDocument(document);
        return userService.updateUser(updatedUser);
    }
    @PutMapping("/enable/{document}")
    public ResponseDto enableUser( @PathVariable int document) {
        return userService.enableUser(document);
    }
    @DeleteMapping("/delete/{document}")
    public ResponseDto deleteUserByDocument(@PathVariable int document) {

        return userService.deleteUser(document);
    }
    @PostMapping("/validateToken/{token}")
    public Boolean validtok(@PathVariable String token) {
        return userService.validToken(token);
    }

}

