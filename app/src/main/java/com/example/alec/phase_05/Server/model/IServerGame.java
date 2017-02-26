package com.example.alec.phase_05.Server.model;

import com.example.alec.phase_05.Server.CommandManager;
import com.example.alec.phase_05.Shared.model.IChatManager;
import com.example.alec.phase_05.Shared.model.IGame;

/**
 * Created by samuel on 2/25/17.
 */

public interface IServerGame extends IGame {

    IChatManager getChatManager();
    CommandManager getCommandManager();
}
