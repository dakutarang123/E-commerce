package com.cloudcart.backend.dto;

public class LoginRequest {

    private String Email;
    private String password;

    public LoginRequest() {
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}