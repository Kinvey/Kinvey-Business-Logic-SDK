package com.kinvey.business_logic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class CollectionArgumentsTest {
    CollectionArguments collectionArguments;

    @Before
    public void setUp() throws Exception {
        File file = FileUtils.getFile("src", "test", "resources", "CollectionArguments.json");
        System.out.println(file);
        ObjectMapper mapper = new ObjectMapper();
        collectionArguments = mapper.readValue(file, CollectionArguments.class);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testDataBinding() throws Exception {
        Assert.assertNotNull(collectionArguments);
        System.out.println(collectionArguments.getKinveyAppMetadata());
        System.out.println(collectionArguments.getAppId());
        Assert.assertNotNull(collectionArguments.getKinveyAppMetadata());

    }

}
