package com.tsu.tastybites.model;

public class LoginResponse {

    private String message;

    private Boolean successful;

    public LoginResponse(String message, Boolean successful) {
        this.message = message;
        this.successful = successful;
    }

    public LoginResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }
}
