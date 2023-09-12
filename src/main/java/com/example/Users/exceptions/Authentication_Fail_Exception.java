package com.example.Users.exceptions;

public class Authentication_Fail_Exception extends IllegalArgumentException {
    public Authentication_Fail_Exception(String msg){
        super(msg);
    }
}
