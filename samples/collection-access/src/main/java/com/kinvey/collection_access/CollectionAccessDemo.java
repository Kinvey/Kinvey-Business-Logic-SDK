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
package com.kinvey.collection_access;

import com.kinvey.business_logic.*;
import com.kinvey.business_logic.collection_access.Collection;
import com.kinvey.business_logic.collection_access.CollectionAccessException;
import com.kinvey.business_logic.collection_access.Document;
import com.kinvey.business_logic.collection_access.Query;

import javax.ws.rs.*;
import java.util.HashMap;
import java.util.logging.Logger;

@Path("/CollectionAccessDemo/{command}")
@Consumes("application/json")
@Produces("application/json")
public class CollectionAccessDemo {

    private final static Logger LOGGER = Logger.getLogger(CollectionAccessDemo.class.getName());

    @POST
    public CommandResponse handleBLRequest(@PathParam("command") String command, Request<CollectionArguments> request){
        if (command.equals("post")){
            throw new RuntimeException("Fail");
        }

        KinveyRequest req  = request.getArguments().getKinveyRequest();
        KinveyResponse res = request.getArguments().getKinveyResponse();

        String reqId = request.getArguments().getRequestId();

        LOGGER.info("Kinvey Request ID: " + reqId);

        Document d = new Document();
        d.put("a", 1);
        Collection test = new Collection("test");

        Document found = null;
        int code = 200;
        try {
            test.insert(d, null);
            Query q = new Query();
            q.equals("a", 1);
            found = test.findOne(q, null);
        } catch (CollectionAccessException e){
            LOGGER.severe(e.toString());
            found = new Document();
            KinveyError.Builder errorBuilder = new KinveyError.Builder();
            KinveyError error = errorBuilder.setException(e).setMessage("Collection Access Failed").build();
            found = new Document(error.getErrorBody());
            code = error.getErrorCode();

        }

        res.setStatus(code)
                .setBody(found)
                .setComplete(true);

        // LOGGER.info("Sending back: " + commandResponse);
        LOGGER.info("Finished.");
        return CommandResponse.initialize(req, res);
    }


}
