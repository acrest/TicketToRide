package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Server.ServerFacade;
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
        ICommand command = SerDes.deserializeCommand(reqBody, ServerResult.SERVER_COMMAND_PREFIX, ServerResult.SERVER_COMMAND_SUFFIX);
        System.out.println("command handler " + ((BaseCommand) command).getCommandName());
        Result result = ServerFacade.get_instance().executeCommand(command);
        sendResponse(t, result);
    }
}