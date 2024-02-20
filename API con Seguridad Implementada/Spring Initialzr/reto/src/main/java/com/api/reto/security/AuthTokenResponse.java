package com.api.reto.security;

public class AuthTokenResponse {
    private String token;
    private String tokenType = "Bearer";

    public AuthTokenResponse(String token) {
        this.token = token;
    }

    // Getters y setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}