package com.kinvey.business_logic;

import java.util.HashMap;

/**
 *  An "internal" Kinvey Request object.
 */
public class KinveyRequest {
    private String method;
    private String username;
    private String entityId;
    private String collectionName;
    private HashMap<String, Object> headers;
    private HashMap<String, Object> body;
    private HashMap<String, Object> params;

    public HashMap<String, Object> getParams() {
        return params;
    }

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public HashMap<String, Object> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, Object> headers) {
        this.headers = headers;
    }

    public HashMap<String, Object> getBody() {
        return body;
    }

    public void setBody(HashMap<String, Object> body) {
        this.body = body;
    }

    /**
     * Generate the debugging string for a request.
     *
     * @return  the string representation
     *
     */
    public String toString() {
        return "M: " + method
                + " U: " + username
                + " ID: " + entityId
                + " CN: " + collectionName
                + " H: " + headers
                + " B: " + body;
    }
}
