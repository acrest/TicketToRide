package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.*;

/**
 * Client version of Result that adds in the ability to deserialize client commands.
 * Created by samuel on 2/3/17.
 */
public class ClientResult extends Result
{
    private static final String CLIENT_COMMAND_PREFIX = ClientResult.class.getPackage().getName() + ".Client";
    private static final String CLIENT_COMMAND_SUFFIX = "Command";

    public ClientResult() {}

    /**
     * Implements the abstract toCommand() method in the Result class.
     * Deserializes using the correct prefix and suffix for the command class.
     * @return deserialized command
     */
    @Override
    public ICommand toCommand()
    {
        return SerDes.deserializeCommand(getRawSerializedResult(), CLIENT_COMMAND_PREFIX, CLIENT_COMMAND_SUFFIX);
    }
}
