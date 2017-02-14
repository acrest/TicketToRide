package com.example.alec.phase_05.Shared.command;

import java.util.List;

/**
 * Created by samuel on 2/13/17.
 */

public abstract class AbstractGetGameUpdatesCommand extends GameCommand {
    private List<ICommand> commands;
    private int lastUpdate;

    public AbstractGetGameUpdatesCommand(String username, String password, int gameID, int lastUpdate) {
        super("GetGameUpdates", username, password, gameID);
        this.lastUpdate = lastUpdate;
    }

    public List<ICommand> getCommands() {
        return commands;
    }

    public void setCommands(List<ICommand> commands) {
        this.commands = commands;
    }

    public int getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(int lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
