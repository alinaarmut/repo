package org.example;

public class AuthRequest {
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
    public AuthRequest(String username, String password) {
    this.username = username;
    this.password = password;
    }   
}
