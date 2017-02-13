package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Shared.command.*;

/**
 * Created by samuel on 2/9/17.
 */

public class ServerResult extends Result
{
    //this assumes that the server commands are in the same package as this CommandHandler
    public static final String SERVER_COMMAND_PREFIX = ServerResult.class.getPackage().getName() + ".Server";
    public static final String SERVER_COMMAND_SUFFIX = "Command";

    public ServerResult () {}

    /**
     * Implements the abstract toCommand() method in the Result class.
     * Deserializes using the correct prefix and suffix for the command class.
     * @return deserialized command
     */
    @Override
    public ICommand toCommand()
    {
        return SerDes.deserializeCommand(getRawSerializedResult(), SERVER_COMMAND_PREFIX, SERVER_COMMAND_SUFFIX);
    }
}
