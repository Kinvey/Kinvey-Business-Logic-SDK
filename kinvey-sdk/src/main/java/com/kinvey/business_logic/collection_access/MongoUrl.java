package com.kinvey.business_logic.collection_access;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

public class MongoUrl extends GenericUrl {

    private final static String DEFAULT_ROOT_URL = "https://mongo.kinvey.com/";

    private String endpoint;
    private String rootUrl;

    public MongoUrl(String encodedUrl){
        super(encodedUrl);
    }

//    public PlusUrl setMaxResults(int maxResults) {
//        this.maxResults = maxResults;
//        return this;
//    }

    public static MongoUrl listBlah(String userId) {
        return new MongoUrl("https://");
//        return new PlusUrl(
//                "https://www.googleapis.com/plus/v1/people/" + userId + "/activities/public");
    }

}
