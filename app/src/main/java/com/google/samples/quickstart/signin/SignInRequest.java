package com.google.samples.quickstart.signin;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by chris on 29-Sep-16.
 */
public class SignInRequest {
    private String token;

    public SignInRequest(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
