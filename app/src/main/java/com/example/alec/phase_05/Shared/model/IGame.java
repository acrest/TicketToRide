package com.example.alec.phase_05.Shared.model;

import com.example.alec.phase_05.Server.CommandManager;

/**
 * Created by samuel on 2/22/17.
 */

public interface IGame {

    int getID();
    String getName();

    Player getPlayer(int position);
    void setPlayer(int position, Player player);
    int addPlayerAtNextPosition(Player player);
    int getNumberPlayers();
    int getMaxPlayers();

    IChatManager getChatManager();
    CommandManager getCommandManager();
    IBank getBank();
}
