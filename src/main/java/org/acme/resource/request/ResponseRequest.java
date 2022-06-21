package org.acme.resource.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseRequest {

    @JsonProperty("status")
    private String status;
    @JsonProperty("message")
    private String message;

    public ResponseRequest(String toString, String userCreated) {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
