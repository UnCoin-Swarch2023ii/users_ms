package com.example.Users.response;

import org.springframework.security.core.userdetails.UserDetails;

public class ResponseSign {
    private String token;
    private UserDetails user;

    public String getToken() {
        return token;
    }

    public void setToken(String status) {
        this.token = status;
    }

    public UserDetails getUsers() {
        return user;
    }

    public void setUser(UserDetails message) {
        this.user = message;
    }

    public ResponseSign(String token, UserDetails user) {
        this.token = token;
        this.user = user;
    }
}
