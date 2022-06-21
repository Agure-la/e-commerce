package org.acme.resource.request;

public class SignInResponse {

    private String status;
    private String token;

    public SignInResponse(String success, String token) {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
