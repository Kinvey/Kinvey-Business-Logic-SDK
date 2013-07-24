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
package com.kinvey.quickstart;

import com.kinvey.business_logic.*;

import javax.ws.rs.*;
import java.util.HashMap;
import java.util.logging.Logger;

@Path("/SimpleTest/{command}")
@Consumes("application/json")
@Produces("application/json")
public class SimpleTest {

    private final static Logger LOGGER = Logger.getLogger(SimpleTest.class.getName());

    @POST
    public CommandResponse handleBLRequest(@PathParam("command") String command, Request<CollectionArguments> request){
        if (command.equals("post")){
            throw new RuntimeException("Fail");
        }

        KinveyRequest req  = request.getArguments().getKinveyRequest();
        KinveyResponse res = request.getArguments().getKinveyResponse();

        String reqId = request.getArguments().getRequestId();

        LOGGER.info("Kinvey Request ID: " + reqId);

        HashMap<String, Object> output = new HashMap<String, Object>();
        output.put("made_request", reqId);

        res.setStatus(200)
                .setBody(output)
                .setComplete(true);

        // LOGGER.info("Sending back: " + commandResponse);
        LOGGER.info("Finished.");
        return CommandResponse.initialize(req, res);
    }


}
