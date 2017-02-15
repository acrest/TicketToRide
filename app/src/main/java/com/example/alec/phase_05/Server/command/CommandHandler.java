package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Shared.command.*;
import com.sun.net.httpserver.*;
import java.io.*;

/**
 * Handles requests made to the /command handle.
 * Created by samuel on 2/4/17.
 */
public class CommandHandler extends HandlerBase
{
    /**
     * Handles requests from clients the connect via the /command handler.
     * @param t HttpExchange object of incoming connection
     * @throws IOException
     */
    public void handle(HttpExchange t) throws IOException
    {
        String reqBody = readRequestBody(t);
        ICommand cmd = SerDes.deserializeCommand(reqBody, ServerResult.SERVER_COMMAND_PREFIX, ServerResult.SERVER_COMMAND_SUFFIX);
        //may want to call ServerFacade.getInstance().executeCommand(cmd) instead
        //not sure if a ServerFacade should be used to execute the commands, or if this is good enough
        Result r;
        if(cmd != null)
        {
            r = cmd.execute();
        }
        else
        {
            r = new ServerResult();
            r.setErrorMessage("no server command to match command sent to server");
        }
        sendResponse(t, r);
    }
}