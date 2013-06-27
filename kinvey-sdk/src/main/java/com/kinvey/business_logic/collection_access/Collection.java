package com.kinvey.business_logic.collection_access;

import java.util.HashMap;
import java.util.List;

import static com.kinvey.business_logic.collection_access.ArgumentType.*;

/***
 * Kinvey MongoDB collection interface.
 *
 * @see <a href="http://devcenter.kinvey.com/reference/business-logic/reference.html#collection-access-module">Collection Access</a>
 */
public class Collection {

    protected String collectionName;

    /***
     * Get a reference to a collection in your Kinvey MonogoDB data store.
     * <p>
     *     All actions on the Kinvey MongoDB data store are performed through
     *     a reference to a Collection.  Each Collection object represents a
     *     collection within MongoDB.
     * </p>
     *
     * @param collectionName  the name of the collection in MongoDB to access.
     */
    public Collection(String collectionName){
        this.collectionName = collectionName;
    }

    /***
     * Find all documents in the collection matching the given query.
     *
     * @param query  the Kinvey query to execute.
     * @param options  the options for the operation, see See <a href="http://mongodb.github.io/node-mongodb-native/api-generated/collection.html#find">find</a> for details.
     * @return  a List of documents matching the query.
     */
    public List<Document> find(Query query, Options options) throws CollectionAccessException {
    MongoRequest mongoRequest = MongoRequest.performOperationOnCollection(CollectionOperation.FIND, this)
                .setArgument(QUERY, query)
                .setArgument(OPTIONS, options);
        List<HashMap<String, Object>> results = mongoRequest.getList();
        return Document.makeDocumentList(results);
    }

    /***
     * Find the first document in the collection matching the query.
     *
     * @param query  the Kinvey query to execute.
     * @param options  the options for the operation, see See <a href="http://mongodb.github.io/node-mongodb-native/api-generated/collection.html#findOne">findOne</a> for details.
     * @return  the first matching document.
     */
    public Document findOne(Query query, Options options) throws CollectionAccessException {
        MongoRequest mongoRequest = MongoRequest.performOperationOnCollection(CollectionOperation.FIND_ONE, this)
            .setArgument(QUERY, query)
            .setArgument(OPTIONS, options);
        return new Document(mongoRequest.getOne());
    }

    /***
     * Find documents matching the given query and update one or more of the documents.
     *
     * @param query  the Kinvey query to execute.
     * @param sortOrder  if multiple documents match the query, the first sorted document will be updated.
     * @param document  the new document.
     * @param options  the options for the operation, see See <a href="http://mongodb.github.io/node-mongodb-native/api-generated/collection.html#findAndModify">findAndModify</a> for details.
     * @return  the number of documents updated.
     */
    public int findAndModify(Query query, SortOrder sortOrder, Document document, Options options) throws CollectionAccessException {
        MongoRequest mongoRequest = MongoRequest.performOperationOnCollection(CollectionOperation.FIND_AND_MODIFY, this)
            .setArgument(QUERY, query)
            .setArgument(SORT_ORDER, sortOrder)
            .setArgument(DOCUMENT, document)
            .setArgument(OPTIONS, options);
        return mongoRequest.getCount();
    }

    /***
     * Find documents and remove one or more of them from the collection.
     *
     * @param query  the Kinvey query to execute.
     * @param sortOrder the first document in the given sortOrder will be removed.
     * @param options  the options for the operation, see See <a href="http://mongodb.github.io/node-mongodb-native/api-generated/collection.html#findAndRemove">findAndRemove</a> for details.
     * @return  the number of documents removed.
     */
    public int findAndRemove(Query query, SortOrder sortOrder, Options options) throws CollectionAccessException {
        MongoRequest mongoRequest = MongoRequest.performOperationOnCollection(CollectionOperation.FIND_AND_REMOVE, this)
            .setArgument(QUERY, query)
            .setArgument(SORT_ORDER, sortOrder)
            .setArgument(OPTIONS, options);
        return mongoRequest.getCount();
    }

    /***
     * Add a document into the collection.
     *
     * @param document  the document to add.
     * @param options  the options for the operation, see See <a href="http://mongodb.github.io/node-mongodb-native/api-generated/collection.html#insert">insert</a> for details.
     */
    public void insert(Document document, Options options) throws CollectionAccessException {
        MongoRequest mongoRequest = MongoRequest.performOperationOnCollection(CollectionOperation.INSERT, this)
            .setArgument(DOCUMENT, document)
            .setArgument(OPTIONS, options);
        mongoRequest.execute();
    }

    /***
     * Remove all documents matching the query from the collection.
     * @param query  the Kinvey query to execute.
     * @param options  the options for the operation, see See <a href="http://mongodb.github.io/node-mongodb-native/api-generated/collection.html#remove">remove</a> for details.
     * @return  the number of documents to remove.
     */
    public int remove(Query query, Options options) throws CollectionAccessException {
        MongoRequest mongoRequest = MongoRequest.performOperationOnCollection(CollectionOperation.REMOVE, this)
            .setArgument(QUERY, query)
            .setArgument(OPTIONS, options);
        return mongoRequest.getCount();
    }

    /***
     * Insert or update a document in the collection.
     * @param document  the document to insert or update (the _id field controls insert vs. update).
     * @param options  the options for the operation, see See <a href="http://mongodb.github.io/node-mongodb-native/api-generated/collection.html#save">save</a> for details.
     */
    public void save(Document document, Options options) throws CollectionAccessException {
        MongoRequest mongoRequest = MongoRequest.performOperationOnCollection(CollectionOperation.SAVE, this)
            .setArgument(DOCUMENT, document)
            .setArgument(OPTIONS, options);
        mongoRequest.execute();
    }

    /***
     * Find all distinct values for the given key.
     *
     * @param key  the Key to find distinct values for.
     * @param query
     * @return  the list of all distinct values.
     */
    public List<Object> distinct(String key, Query query) throws CollectionAccessException {
        MongoRequest mongoRequest = MongoRequest.performOperationOnCollection(CollectionOperation.DISTINCT, this)
            .setArgument(KEY, key)
            .setArgument(QUERY, query);
        return mongoRequest.getObjectList();
    }

    /***
     * Update an existing document in the collection
     *
     * @param query  the Kinvey query to execute
     * @param document  the document to update the matched objects with
     * @param options  the options for the operation, see See <a href="http://mongodb.github.io/node-mongodb-native/api-generated/collection.html#update">update</a> for details.
     */
    public void update(Query query, Document document, Options options) throws CollectionAccessException {
        MongoRequest mongoRequest = MongoRequest.performOperationOnCollection(CollectionOperation.UPDATE, this)
            .setArgument(QUERY, query)
            .setArgument(DOCUMENT, document)
            .setArgument(OPTIONS, options);
        mongoRequest.execute();
    }

    /***
     * Find the number of objects that match the given query.
     *
     * @param query  the Kinvey query to execute.
     * @return  the number of objects in the collection matching the query.
     */
    public int count(Query query) throws CollectionAccessException {
        MongoRequest mongoRequest = MongoRequest.performOperationOnCollection(CollectionOperation.COUNT, this)
            .setArgument(QUERY, query);
        return mongoRequest.getCount();
    }

    /***
     * Find all documents in the collection geographically near the given coordinates.
     *
     * @param x  the x (longitude) coordinate
     * @param y  the y (latitude) coordinate
     * @param options  the options for the operation, see See <a href="http://mongodb.github.io/node-mongodb-native/api-generated/collection.html#geoNear">geoNear</a> for details.
     * @return  the list of matching documents.
     */
    public List<Document> geoNear(double x, double y, Options options) throws CollectionAccessException {
        MongoRequest mongoRequest = MongoRequest.performOperationOnCollection(CollectionOperation.GEO_NEAR, this)
            .setArgument(LONGITUDE, x)
            .setArgument(LATITUDE, y)
            .setArgument(OPTIONS, options);
        List<HashMap<String, Object>> results = mongoRequest.getList();
        return Document.makeDocumentList(results);
    }

}
