/*
 * Copyright (c) 2013 Kinvey Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.kinvey.business_logic;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The arguments passed to a {@link Request} that's modifying a collection request.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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
