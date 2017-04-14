package com.example.alec.phase_05.Shared.command;

/**
 * Created by clarkpathakis on 2/8/17.
 */

public abstract class GetGameListCommand extends BaseCommand {
    private String playerName;
    public GetGameListCommand(String name) {
        super("GetGameList");
        playerName = name;
    }



    public String getPlayerName(){
        return playerName;
    }
}
