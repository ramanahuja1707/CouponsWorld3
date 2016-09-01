package com.couponsworld.dto;

/**
 * Created by cpt2rah on 11-08-2016.
 */
public class LoginCredentials {
    private String emailId = "";
    private String password = "";

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
