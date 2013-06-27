package com.kinvey.business_logic.collection_access;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

public class MongoUrl extends GenericUrl {

    public MongoUrl(String protocol, String baseUrl, String collection, String endpoint){
        super();
        String encodedUrl = protocol + "://" + baseUrl + "/" + collection + "/" + endpoint;
        this.setRawPath(encodedUrl);
    }
}
