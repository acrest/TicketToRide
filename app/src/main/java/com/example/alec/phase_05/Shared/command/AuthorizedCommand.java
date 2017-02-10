package com.example.alec.phase_05.Shared.command;

/**
 * Holds a username and password.
 * Created by samuel on 2/3/17.
 */
public abstract class AuthorizedCommand extends BaseCommand
{
    private String userName;
    private String password;

    /**
     * @param commandName command name from BaseCommand
     * @param userName username of client
     * @param password password of client
     */
    public AuthorizedCommand(String commandName, String userName, String password)
    {
        super(commandName);
        this.userName = userName;
        this.password = password;
    }

    /**
     * @return username of client
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     * @param u username of client
     */
    public void setUserName(String u)
    {
        userName = u;
    }

    /**
     * @return password of client
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param p password of client
     */
    public void setPassword(String p)
    {
        password = p;
    }
}
