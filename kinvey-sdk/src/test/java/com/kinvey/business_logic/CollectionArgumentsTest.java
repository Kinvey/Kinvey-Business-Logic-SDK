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
