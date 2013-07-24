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
package com.kinvey.xmpp_demo;

import com.google.appengine.api.xmpp.*;
import com.kinvey.business_logic.*;

import javax.ws.rs.*;
import java.util.HashMap;
import java.util.logging.Logger;

@Path("/ExternalBLCollection/{command}")
@Consumes("application/json")
@Produces("application/json")
public class XmppEndpoint {

    private final static Logger LOGGER = Logger.getLogger(XmppEndpoint.class.getName());

    @POST
    public CommandResponse handleBLRequest(@PathParam("command") String command, Request<CollectionArguments> request){
        if (command.equals("post")){
            throw new RuntimeException("This BL does not support after hooks");

        } else {

            KinveyRequest req = request.getArguments().getKinveyRequest();
            KinveyResponse res = request.getArguments().getKinveyResponse();
            HashMap<String, Object> body = req.getBody();

            String userEmail = body.get("user").toString();
            String message = body.get("message").toString();
            String reqId = request.getArguments().getRequestId();
            String from = "";

            if (body.containsKey("from")){
                from = body.get("from").toString();
            }

            LOGGER.info("Kinvey Request ID: " + reqId);

            pingUser(userEmail, from, message + "(Req ID: " + reqId + ")");
            HashMap<String, Object> resBody = res.getBody();
            resBody.put("result", pingUser(userEmail, from, message));
            res.setBody(resBody);

            return CommandResponse.initialize(req, res);
        }
    }


    boolean pingUser(String user, String fromUser, String message){
        JID jid = new JID(user);
        Message msg;

        if (!fromUser.equals("")){
            JID from = new JID(fromUser);
            msg = new MessageBuilder()
                    .withFromJid(from)
                    .withRecipientJids(jid)
                    .withBody(message)
                    .build();
        } else {
            msg = new MessageBuilder()
                    .withRecipientJids(jid)
                    .withBody(message)
                    .build();
        }

        boolean messageSent = false;
        XMPPService xmpp = XMPPServiceFactory.getXMPPService();
        SendResponse status = xmpp.sendMessage(msg);
        messageSent = (status.getStatusMap().get(jid) == SendResponse.Status.SUCCESS);

        if (!messageSent) {
            LOGGER.warning("Message failed :-(");
        }

        return messageSent;

    }

}
