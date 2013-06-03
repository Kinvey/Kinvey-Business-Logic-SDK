package com.kinvey.business_logic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class KinveyRequestTest {

    Request<CollectionArguments> request;

    @Before
    public void setUp() throws Exception {
        File file = FileUtils.getFile("src", "test", "resources", "Request_CollectionArguments.json");
        ObjectMapper mapper = new ObjectMapper();
        request = mapper.readValue(file, new TypeReference<Request<CollectionArguments>>() {});

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testDataBinding() throws Exception {
        Assert.assertNotNull(request);
        Assert.assertNotNull(request.getArguments().getKinveyAppMetadata());

    }
}
