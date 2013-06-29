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

    private KinveyRequest() {}

    static KinveyRequest initialize() {
        return new KinveyRequest();
    }

    public HashMap<String, Object> getParams() {
        return params;
    }

    public KinveyRequest setParams(HashMap<String, Object> params) {
        this.params = params;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public KinveyRequest setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public KinveyRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEntityId() {
        return entityId;
    }

    public KinveyRequest setEntityId(String entityId) {
        this.entityId = entityId;
        return this;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public KinveyRequest setCollectionName(String collectionName) {
        this.collectionName = collectionName;
        return this;
    }

    public HashMap<String, Object> getHeaders() {
        return headers;
    }

    public KinveyRequest setHeaders(HashMap<String, Object> headers) {
        this.headers = headers;
        return this;
    }

    public HashMap<String, Object> getBody() {
        return body;
    }

    public KinveyRequest setBody(HashMap<String, Object> body) {
        this.body = body;
        return this;
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
