package com.kinvey.business_logic;

/**
 * The response expected by the Kinvey KCS service.
 * <p/>
 * This should be the return value of any request handler from an incoming Kinvey
 * request.
 *
 * TODO: This needs better
 */
public class CommandResponse {
    private KinveyRequest request;
    private KinveyResponse response;

    /**
     * Get the {@link KinveyRequest} that will be returned to Kinvey.
     *
     * @return  the request that will be sent to Kinvey.
     */
    public KinveyRequest getRequest() {
        return request;
    }

    /**
     * Set the {@link KinveyRequest} that will be sent to Kinvey.
     *
     * @param request  the request that should be sent to Kinvey.
     */
    public void setRequest(KinveyRequest request) {
        this.request = request;
    }

    /**
     * Get the {@link KinveyResponse} that will be sent to Kinvey.
     *
     * @return  the response that will be sent to Kinvey.
     */
    public KinveyResponse getResponse() {
        return response;
    }

    /**
     * Set the {@link KinveyResponse} that will be sent to Kinvey.
     *
     * @param response  the response that should be sent to Kinvey.
     */
    public void setResponse(KinveyResponse response) {
        this.response = response;
    }

    /**
     * Create the debugging {@link String} representation of the response.
     *
     * @return  the debugging representation
     */
    public String toString() {
        return "Request: " + request + "\nResponse: " + response;
    }
}
