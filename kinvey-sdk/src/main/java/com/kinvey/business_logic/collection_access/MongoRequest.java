package com.kinvey.business_logic.collection_access;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.kinvey.business_logic.collection_access.query.ArgumentType;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MongoRequest {

    static HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static JsonFactory JSON_FACTORY = new JacksonFactory();

    public static MongoRequest performOperationOnCollection(CollectionOperation operation, Collection collection){
        return new MongoRequest(collection, operation);
    }

    public MongoRequest(Collection collection, CollectionOperation op) {

    }

    /***
     * Testing Constructor, allows overriding of the built-in transport and JSON factory
     *
     * @param collection
     * @param op
     * @param transport
     * @param jsonFactory
     */
    public MongoRequest(Collection collection, CollectionOperation op, HttpTransport transport, JsonFactory jsonFactory){
        HTTP_TRANSPORT = transport;
        JSON_FACTORY = jsonFactory;
    }

    public MongoRequest setArgument(ArgumentType argumentType, Object argument) {
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
            MongoUrl url = new MongoUrl("Hi");// PlusUrl.listPublicActivities(USER_ID).setMaxResults(MAX_RESULTS);
            url.put("fields", "items(id,url,object(content,plusoners/totalItems))");
            HttpRequest request = requestFactory.buildGetRequest(url);
            return request.execute();
        } catch (IOException e){
            throw new CollectionAccessException(e);
        }
    }


    private static final ArrayList<HashMap<String, Object>> LIST_MAP_OBJECT = new ArrayList<HashMap<String, Object>>();
    private static final ArrayList<Object> LIST_OBJECT = new ArrayList<Object>();
    private static final HashMap<String, Object> MAP_OBJECT = new HashMap<String, Object>();


    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getList() throws CollectionAccessException {
        try {
            HttpResponse response = makeRequest();
            return (List<Map<String, Object>>)response.parseAs(LIST_MAP_OBJECT.getClass());
        } catch (IOException e){
            throw new CollectionAccessException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Object> getObjectList() throws CollectionAccessException {
        try {
            HttpResponse response = makeRequest();
            return (List<Object>)response.parseAs(LIST_OBJECT.getClass());
        } catch (IOException e){
            throw new CollectionAccessException(e);
        }
    }


    @SuppressWarnings("unchecked")
    public Map<String, Object> getOne() throws CollectionAccessException {
        try {
            HttpResponse response = makeRequest();
            return (Map<String, Object>)response.parseAs(LIST_OBJECT.getClass());
        } catch (IOException e){
            throw new CollectionAccessException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public void execute() throws CollectionAccessException {
        try {
            HttpResponse response = makeRequest();
//            return response.parseAs(output.getClass());
        } catch (IOException e){
            throw new CollectionAccessException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public int getCount() throws CollectionAccessException {
        try {
            HttpResponse response = makeRequest();
            return response.parseAs(Integer.class).intValue();
        } catch (IOException e){
            throw new CollectionAccessException(e);
        }
    }
}
