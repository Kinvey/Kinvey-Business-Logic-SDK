package com.kinvey.business_logic.collection_access;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Base64;
import com.kinvey.business_logic.KinveyAuthCredentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MongoRequest {

    private static HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static JsonFactory JSON_FACTORY = new JacksonFactory();

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
        this.baseUrl = "mongo.kinvey.com";
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
     * @param transport
     * @param jsonFactory
     */
    public MongoRequest(Collection collection, CollectionOperation op, String protocol, String baseUrl, HttpTransport transport, JsonFactory jsonFactory){
        this.collection = collection;
        this.collectionOperation = collectionOperation;
        this.protocol = protocol;
        this.baseUrl = baseUrl;
        HTTP_TRANSPORT = transport;
        JSON_FACTORY = jsonFactory;
        this.content = new HashMap<String, Object>();
    }

    public MongoRequest setArgument(ArgumentType argumentType, Object argument) {

        if (argumentType == ArgumentType.QUERY){
            Query q = (Query)argument;
            argument = q.getQueryFilterJson(JSON_FACTORY);
        }
        this.content.put(argumentType.getArgumentType(), argument);
        return this;
    }


    private HttpResponse makeRequest() throws CollectionAccessException {
        try {
            HttpRequestFactory requestFactory =
                    HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                        @Override
                        public void initialize(HttpRequest request) {
                            request.setParser(new JsonObjectParser(JSON_FACTORY));
                        }
                    });
            MongoUrl url = new MongoUrl(this.protocol,
                    this.baseUrl,
                    this.collection.collectionName,
                    this.collectionOperation.getEndPoint());

            JsonHttpContent postContent = new JsonHttpContent(JSON_FACTORY, this.content);
            HttpRequest request = requestFactory.buildPostRequest(url, postContent);

            // Set the auth headers
            KinveyAuthCredentials authCredentials = KinveyAuthCredentials.getInstance();
            String userAndPassword = authCredentials.getAppId() + ":" + authCredentials.getMasterSecret();
            HttpHeaders headers = request.getHeaders();
            headers.put("Authorization", "Basic " + Base64.encodeBase64String(userAndPassword.getBytes()));
            request.setHeaders(headers);

            return request.execute();
        } catch (IOException e){
            throw new CollectionAccessException(e);
        }
    }


    private static final ArrayList<HashMap<String, Object>> LIST_MAP_OBJECT = new ArrayList<HashMap<String, Object>>();
    private static final ArrayList<Object> LIST_OBJECT = new ArrayList<Object>();
    private static final HashMap<String, Object> MAP_OBJECT = new HashMap<String, Object>();

    @SuppressWarnings("unchecked")
    public ArrayList<HashMap<String, Object>> getList() throws CollectionAccessException {
        try {
            HttpResponse response = makeRequest();
            return (ArrayList<HashMap<String, Object>>)response.parseAs(LIST_MAP_OBJECT.getClass());
        } catch (IOException e){
            throw new CollectionAccessException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Object> getObjectList() throws CollectionAccessException {
        try {
            HttpResponse response = makeRequest();
            return (ArrayList<Object>)response.parseAs(LIST_OBJECT.getClass());
        } catch (IOException e){
            throw new CollectionAccessException(e);
        }
    }


    @SuppressWarnings("unchecked")
    public HashMap<String, Object> getOne() throws CollectionAccessException {
        try {
            HttpResponse response = makeRequest();
            return (HashMap<String, Object>)response.parseAs(MAP_OBJECT.getClass());
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
            return response.parseAs(Integer.class);
        } catch (IOException e){
            throw new CollectionAccessException(e);
        }
    }
}
