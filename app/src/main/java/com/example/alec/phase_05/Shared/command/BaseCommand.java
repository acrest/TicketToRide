package com.example.alec.phase_05.Shared.command;

/**
 * Holds a command name used in serializing and deserializing.
 * Created by samuel on 2/3/17.
 */
public abstract class BaseCommand implements ICommand
{
    private String commandName;
    private static int commandId;
    private int id;

    public BaseCommand(String commandName)
    {
        id = commandId;
        commandId++;
        this.commandName = commandName;
    }

    /**
     * The command name is used to find a matching client or server command for deserialization by SerDes.
     * @return name of this command
     */
    public String getCommandName()
    {
        return commandName;
    }

    /**
     * The command name is used to find a matching client or server command for deserialization by SerDes.
     * @param c name of this command
     */
    public void setCommandName(String c)
    {
        commandName = c;
    }

    public int getId() {
        return id;
    }
}
