package com.kinvey.business_logic;

public class KinveyAuthCredentials {
    private static KinveyAuthCredentials ourInstance = new KinveyAuthCredentials();

    private String appId;
    private String masterSecret;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }

    public static KinveyAuthCredentials getInstance() {
        return ourInstance;
    }

    private KinveyAuthCredentials() {
        appId = "invalid";
        masterSecret = "invalid";
    }
}
