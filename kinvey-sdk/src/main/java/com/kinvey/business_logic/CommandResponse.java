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

    private CommandResponse(KinveyRequest req, KinveyResponse res) {
        request = req == null ? KinveyRequest.initialize() : req;
        response = res  == null ? KinveyResponse.initialize() : res;
    }

    public static CommandResponse initialize() {
        return new CommandResponse(null, null);
    }

    public static CommandResponse initialize(KinveyRequest req, KinveyResponse res) {
        return new CommandResponse(req, res);
    }

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
    public CommandResponse setRequest(KinveyRequest request) {
        this.request = request;
        return this;
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
    public CommandResponse setResponse(KinveyResponse response) {
        this.response = response;
        return this;
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
