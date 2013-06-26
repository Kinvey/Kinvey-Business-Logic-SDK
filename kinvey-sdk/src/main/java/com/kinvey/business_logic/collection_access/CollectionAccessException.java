package com.kinvey.business_logic.collection_access;

import java.io.IOException;

public class CollectionAccessException extends IOException {

    public CollectionAccessException(String message){
        super(message);
    }

    public CollectionAccessException(IOException e) {
        super(e);
    }
}
