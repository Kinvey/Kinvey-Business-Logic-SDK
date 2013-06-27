package com.kinvey.business_logic.collection_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Document extends HashMap<String, Object> {
    public static List<Document> makeDocumentList(List<HashMap<String, Object>> list) {
        List<Document> output = new ArrayList<Document>();
        for (HashMap<String, Object> d : list){
            output.add(new Document(d));
        }
        return output;
    }


    public Document(){
    }

    public Document(HashMap<String, Object> map){
        super(map);
    }
}
