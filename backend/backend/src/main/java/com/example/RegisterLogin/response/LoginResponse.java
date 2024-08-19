package com.example.RegisterLogin.response;

public class LoginResponse {

    String message;
    Boolean status;
    String token;
    long expiresIn;

    public LoginResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public LoginResponse() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", token='" + token + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}
