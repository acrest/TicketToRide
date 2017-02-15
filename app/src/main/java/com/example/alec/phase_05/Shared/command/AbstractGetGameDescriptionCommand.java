package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.GameDescription;

/**
 * Created by samuel on 2/14/17.
 */

public abstract class AbstractGetGameDescriptionCommand extends GameCommand {

    public AbstractGetGameDescriptionCommand(String username, String password, int gameID) {
        super("GetGameDescription", username, password, gameID);
    }
}
