package com.example.alec.phase_05.Shared.command;

/**
 * Holds data for the corresponding client and server commands.
 * Created by samuel on 2/3/17.
 */
public abstract class RegisterCommand extends BaseCommand {
    private String username;
    private String password;

    /**
     * @param username username of client
     * @param password password of client
     */
    public RegisterCommand(String username, String password) {
        super("Register");
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
