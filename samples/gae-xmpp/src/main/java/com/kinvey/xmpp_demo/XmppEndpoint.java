package com.kinvey.xmpp_demo;

import com.google.appengine.api.xmpp.*;
import com.kinvey.business_logic.*;

import javax.ws.rs.*;
import java.util.HashMap;
import java.util.logging.Logger;

@Path("/ExternalBL/{command}")
@Consumes("application/json")
@Produces("application/json")
public class XmppEndpoint {

    private final static Logger LOGGER = Logger.getLogger(XmppEndpoint.class.getName());

    @POST
    public CommandResponse handleBLRequest(@PathParam("command") String command, Request<CollectionArguments> request){
        if (command.equals("pre")){
            throw new RuntimeException("Fail");
        }

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

        CommandResponse commandResponse = new CommandResponse();
        commandResponse.setRequest(req);
        commandResponse.setResponse(res);

        return commandResponse;
    }


    void pingUser(String user, String fromUser, String message){
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

    }


}
