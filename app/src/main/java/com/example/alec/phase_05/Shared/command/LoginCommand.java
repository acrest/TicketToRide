package com.example.alec.phase_05.Shared.command;

/**
 * Holds data for the corresponding client and server commands.
 * Created by samuel on 2/3/17.
 */
public abstract class LoginCommand extends BaseCommand {
    private String username;
    private String password;

    /**
     * @param username username of client
     * @param password password of client
     */
    public LoginCommand(String username, String password) {
        super("Login");
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
