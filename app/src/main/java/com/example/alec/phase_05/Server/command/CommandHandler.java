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
    //this assumes that the server commands are in the same package as this CommandHandler
    private static final String SERVER_COMMAND_PREFIX = CommandHandler.class.getPackage().getName() + ".Server";
    private static final String SERVER_COMMAND_SUFFIX = "Command";

    /**
     * Handles requests from clients the connect via the /command handler.
     * @param t HttpExchange object of incoming connection
     * @throws IOException
     */
    public void handle(HttpExchange t) throws IOException
    {
        String reqBody = readRequestBody(t);
        ICommand cmd = SerDes.deserializeCommand(reqBody, SERVER_COMMAND_PREFIX, SERVER_COMMAND_SUFFIX);
        //may want to call ServerFacade.getInstance().executeCommand(cmd) instead
        //not sure if a ServerFacade should be used to execute the commands, or if this is good enough
        Result r;
        if(cmd != null)
        {
            r = cmd.execute();
        }
        else
        {
            r = new Result();
            r.setErrorMessage("no server command to match command sent to server");
        }
        sendResponse(t, r);
    }
}
