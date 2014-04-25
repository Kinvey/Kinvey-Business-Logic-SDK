package com.kinvey.business_logic.collection_access;

@Deprecated
public enum CollectionOperation {
    FIND("find"),
    FIND_ONE("findOne"),
    FIND_AND_MODIFY("findAndModify"),
    FIND_AND_REMOVE("findAndRemove"),
    INSERT("insert"),
    REMOVE("remove"),
    SAVE("save"),
    DISTINCT("distinct"),
    UPDATE("update"),
    COUNT("count"),
//    GROUP("group"),
    GEO_NEAR("geoNear");

    private final String endPoint;

    public String getEndPoint() {
        return endPoint;
    }

    private CollectionOperation(String string){
        this.endPoint = string;
    }
}
