package com.example.alec.phase_05.Server.model;

import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.IBank;
import com.example.alec.phase_05.Shared.model.IChatManager;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 2/25/17.
 */

public class ServerGame extends Game implements IServerGame {
    private CommandManager commandManager;
    private IChatManager chatManager;

    public ServerGame(int id, String name, int maxPlayers, CommandManager commandManager, IChatManager chatManager, IBank bank, GameMap gameMap) {
        super(id, name, maxPlayers, bank, gameMap);
        this.commandManager = commandManager;
        this.chatManager = chatManager;

        commandManager.setGame(this);
    }

    @Override
    public IChatManager getChatManager() {
        return chatManager;
    }

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }

    public TrainCard drawTrainCard(String playerName) {
        return ((IServerBank) getBank()).drawTrainCard();
    }

    public TrainCard pickTrainCard(String playerName, int index) {
        return ((IServerBank) getBank()).drawVisibleTrainCard(index);
    }

    public DestinationCard drawDestinationCard(String playerName) {
        return ((IServerBank) getBank()).drawDestinationCard();
    }
}
