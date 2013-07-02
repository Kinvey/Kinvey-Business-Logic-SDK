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
