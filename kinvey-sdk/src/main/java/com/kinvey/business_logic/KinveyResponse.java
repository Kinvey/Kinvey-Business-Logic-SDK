package com.kinvey.business_logic;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;


/**
 *  An "internal" Kinvey Response object.
 */
public class KinveyResponse {
    private boolean complete;
    private HashMap<String, Object> headers;
    private HashMap<String, Object> body;
    private HashMap<String, Object> error;
    private int statusCode;
    private int status;

    /**
     * Create a new KinveyResponse (helper for Jackson).
     *
     * @param status  the HTTP status code of the response
     * @param complete  a status flag indicating that processing should terminate
     */
    @JsonCreator()
    public KinveyResponse(@JsonProperty("status") int status, @JsonProperty("complete") boolean complete) {
        this.status = status;
        this.statusCode = status;
        this.complete = complete;
    }

    private KinveyResponse() {}

    public static KinveyResponse initialize() {
        return new KinveyResponse();
    }


    public boolean isComplete() {
        return complete;
    }

    public KinveyResponse setComplete(boolean complete) {
        this.complete = complete;
        return this;
    }

    public HashMap<String, Object> getHeaders() {
        return headers;
    }

    public KinveyResponse setHeaders(HashMap<String, Object> headers) {
        this.headers = headers;
        return this;
    }

    public HashMap<String, Object> getBody() {
        return body;
    }

    public KinveyResponse setBody(HashMap<String, Object> body) {
        this.body = body;
        return this;
    }

    public KinveyResponse setError(KinveyError error) {
        this.statusCode = error.getErrorCode();
        this.error = error.getErrorBody();
        this.setComplete(true);
        return this;
    }

    public HashMap<String, Object> getError() {
        return error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Update the HTTP status code.
     *
     * @param status  the new HTTP status code to use
     *
     */
    public KinveyResponse setStatus(int status) {
        this.status = status;
        this.statusCode = status;
        return this;
    }

    /**
     * Generate the debugging string for a response.
     *
     * @return  the string representation
     *
     */
    public String toString() {
        return "Complete? " + complete
                + " SC: (" + statusCode + "/" + status
                + ") H: " + headers
                + " B: " + body;
    }

}
