package com.kinvey.business_logic;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The arguments passed to a {@link Request} that's modifying a collection request.
 */
@JsonIgnoreProperties("blScript")
public class CollectionArguments {

    private KinveyResponse kinveyResponse;
    private KinveyRequest kinveyRequest;
    private String appId;
    private KinveyAppMetadata kinveyAppMetadata;
    private String targetFunction;
    private String requestId;
    private String collectionName;
    private String hookType;

    @JsonProperty("response")
    public KinveyResponse getKinveyResponse() {
        return kinveyResponse;
    }

    @JsonProperty("response")
    public void setKinveyResponse(KinveyResponse kinveyResponse) {
        this.kinveyResponse = kinveyResponse;
    }

    @JsonProperty("request")
    public KinveyRequest getKinveyRequest() {
        return kinveyRequest;
    }

    @JsonProperty("request")
    public void setKinveyRequest(KinveyRequest kinveyRequest) {
        this.kinveyRequest = kinveyRequest;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @JsonProperty("appMetadata")
    public KinveyAppMetadata getKinveyAppMetadata() {
        return kinveyAppMetadata;
    }

    @JsonProperty("appMetadata")
    public void setKinveyAppMetadata(KinveyAppMetadata kinveyAppMetadata) {
        this.kinveyAppMetadata = kinveyAppMetadata;
    }

    public String getTargetFunction() {
        return targetFunction;
    }

    public void setTargetFunction(String targetFunction) {
        this.targetFunction = targetFunction;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getHookType() {
        return hookType;
    }

    public void setHookType(String hookType) {
        this.hookType = hookType;
    }
}
