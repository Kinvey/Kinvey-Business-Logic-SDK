package com.kinvey.business_logic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *  The metadata associated with the Kinvey app backend.
 *
 *  This class is READ-ONLY, since Kinvey sends this data to the BL
 *  execution environment but doesn't expect a response.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class KinveyAppMetadata {

    private String id;
    private String name;
    private String platform;
    private String url;
    private Integer apiVersion;

    /**
     * Get the app ID of the Kinvey App Backend.
     *
     * @return  the app's Kinvey ID
     */
    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    /**
     * Get the name of the application backend.
     *
     * @return  the app's name
     */
    public String getName() {
        return name;
    }


    /**
     * Get the name of the primary platform for this app backend.
     * The platform is a non-binding indication of the primary platform that
     * consumes this backend.  There may be other client consumers.
     *
     * @return  the name of the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * The configured default API version for this app backend.
     * If no X-Kinvey-API-Version header is specified the app will default to
     * the specified version.  This is an informative parameter as libraries
     * can override the setting.
     *
     * @return  the configured API version
     */
    public Integer getApiVersion() {
        return apiVersion;
    }
}
