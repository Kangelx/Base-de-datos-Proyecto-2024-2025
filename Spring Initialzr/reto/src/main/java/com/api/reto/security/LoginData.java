package com.api.reto.security;

public class LoginData {
    private String username;
    private String password;

    // Constructor por defecto necesario para la deserialización JSON
    public LoginData() {
    }

    // Getters y setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}