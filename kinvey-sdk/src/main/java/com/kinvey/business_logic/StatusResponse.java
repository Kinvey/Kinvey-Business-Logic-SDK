package com.kinvey.business_logic;

/**
 * The response expected by the Kinvey KCS service for a status request.
 * <p/>
 * This should be the return value of any request handler that has handled a status request.
 */
public class StatusResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
