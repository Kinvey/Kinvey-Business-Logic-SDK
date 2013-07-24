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
package com.kinvey.business_logic.collection_access;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kinvey.business_logic.KinveyAuthCredentials;
import com.sun.jersey.core.util.Base64;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MongoRequest {

    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    private CollectionOperation collectionOperation;
    private Collection collection;
    private String protocol;
    private String baseUrl;
    private HashMap<String, Object> content;

    public static MongoRequest performOperationOnCollection(CollectionOperation op, Collection collection){
        return new MongoRequest(collection, op);
    }

    public static MongoRequest performOperationOnCollection(String protocol, String baseUrl, CollectionOperation operation, Collection collection){
        return new MongoRequest(protocol, baseUrl, collection, operation);
    }


    public MongoRequest(Collection collection, CollectionOperation op) {
        this.protocol = "https";
        this.baseUrl = "kmongo.kinvey.com";
        this.collection = collection;
        this.collectionOperation = op;
        this.content = new HashMap<String, Object>();
    }

    public MongoRequest(String protocol, String baseUrl, Collection collection, CollectionOperation collectionOperation){
        this.protocol = protocol;
        this.baseUrl = baseUrl;
        this.collection = collection;
        this.collectionOperation = collectionOperation;
        this.content = new HashMap<String, Object>();
    }

    /***
     * Testing Constructor, allows overriding of the built-in transport and JSON factory
     *
     * @param collection
     * @param op
     * @param jsonFactory
     */
    public MongoRequest(Collection collection, CollectionOperation op, String protocol, String baseUrl,  JsonFactory jsonFactory){
        this.collection = collection;
        this.collectionOperation = op;
        this.protocol = protocol;
        this.baseUrl = baseUrl;
        this.content = new HashMap<String, Object>();
    }

    public MongoRequest setArgument(ArgumentType argumentType, Object argument) {

        if (argument == null){
            return this;
        }

        if (argumentType == ArgumentType.QUERY){
            Query q = (Query)argument;
            argument = q.getQueryFilterMap();
        }
        this.content.put(argumentType.getArgumentType(), argument);
        return this;
    }

    private String buildBody() throws CollectionAccessException {
        try {
            return OBJECT_MAPPER.writeValueAsString(content);
        } catch (IOException e){
            throw new CollectionAccessException(e);
        }
    }

    private HttpResponse makeRequest() throws CollectionAccessException {
        KinveyAuthCredentials authCredentials = KinveyAuthCredentials.getInstance();

        try {
            String encodedUrl = this.protocol + "://" + this.baseUrl + "/"
                    + authCredentials.getAppId() + "/"
                    + this.collection.collectionName + "/"
                    + this.collectionOperation.getEndPoint();

            String userPass = authCredentials.getAppId() + ":" + authCredentials.getMasterSecret();
            String authString = "Basic " + new String(Base64.encode(userPass), "UTF-8");

            URL url = new URL(encodedUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", authString);

            String input = buildBody();


            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String tmpOutput;
            String contentBody = "";
            while ((tmpOutput = br.readLine()) != null) {
                contentBody += tmpOutput;
            }
            conn.disconnect();

            HttpResponse response = new HttpResponse(contentBody);

            return response;

        } catch (IOException e){
            throw new CollectionAccessException(e);
        }
    }



    public ArrayList<HashMap<String, Object>> getList() throws CollectionAccessException {
        try {
            HttpResponse response = makeRequest();
            return response.parseAsListMap();
        } catch (IOException e){
            throw new CollectionAccessException(e);
        }
    }

    public ArrayList<Object> getObjectList() throws CollectionAccessException {
        try {
            HttpResponse response = makeRequest();
            return response.parseAsList();
        } catch (IOException e){
            throw new CollectionAccessException(e);
        }
    }


    public HashMap<String, Object> getOne() throws CollectionAccessException {
        try {
            HttpResponse response = makeRequest();
            return response.parseAsMap();
        } catch (IOException e){
            throw new CollectionAccessException(e);
        }
    }

    public void execute() throws CollectionAccessException {
        try {
            HttpResponse response = makeRequest();
//            return response.parseAs(output.getClass());
        } catch (IOException e){
            throw new CollectionAccessException(e);
        }
    }

    public int getCount() throws CollectionAccessException {
        try {
            HttpResponse response = makeRequest();
            HashMap<String, Object> body = response.parseAsMap();
            Integer count = (Integer)body.get("count");
            return count;
        } catch (IOException e){
            throw new CollectionAccessException(e);
        }
    }

    private static final ArrayList<HashMap<String, Object>> LIST_MAP_OBJECT = new ArrayList<HashMap<String, Object>>();
    private static final ArrayList<Object> LIST_OBJECT = new ArrayList<Object>();
    private static final HashMap<String, Object> MAP_OBJECT = new HashMap<String, Object>();

    private class HttpResponse {
        private String body;
        private ObjectMapper objectMapper;

        public HttpResponse(String body) {
            this.body = body;
            objectMapper = new ObjectMapper();
        }


        int parseAsInt() throws IOException {
            return objectMapper.readValue(body, Integer.class);

        }
        @SuppressWarnings("unchecked")
        HashMap<String, Object> parseAsMap() throws IOException {
            return (HashMap<String, Object>)objectMapper.readValue(body, MAP_OBJECT.getClass());
        }

        @SuppressWarnings("unchecked")
        ArrayList<Object> parseAsList() throws IOException {
            return (ArrayList<Object>)objectMapper.readValue(body, LIST_OBJECT.getClass());
        }

        @SuppressWarnings("unchecked")
        ArrayList<HashMap<String, Object>> parseAsListMap() throws IOException {
            return (ArrayList<HashMap<String, Object>>)objectMapper.readValue(body, LIST_MAP_OBJECT.getClass());
        }

    }
}
