package com.example.alec.phase_05.Server.model;

import com.example.alec.phase_05.Shared.command.GameCommand;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.IChatManager;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 2/25/17.
 */

public class ServerGame extends Game implements IServerGame {
    private CommandManager commandManager;
    private IChatManager chatManager;
    private GameState gameState;

    public ServerGame(int id, String name, int maxPlayers, CommandManager commandManager, IChatManager chatManager, IServerBank bank, GameMap gameMap) {
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
    public void addCommand(GameCommand command) {
        commandManager.addCommand(command);
    }

    @Override
    public ICommand recentCommand(String playerName) {
        return commandManager.recentCommand(playerName);
    }

    @Override
    public TrainCard drawTrainCard(String playerName) {
        Player player = (Player) getPlayerByName(playerName);
        if(player == null) return null;
        TrainCard card = ((IServerBank) getBank()).drawTrainCard();
        player.addTrainCard(card);
        return card;
    }

    @Override
    public TrainCard pickTrainCard(String playerName, int index) {
        Player player = (Player) getPlayerByName(playerName);
        if(player == null) return null;
        TrainCard card = ((IServerBank) getBank()).drawVisibleTrainCard(index);
        player.addTrainCard(card);
        return card;
    }

    @Override
    public DestinationCard drawDestinationCard(String playerName) {
        Player player = (Player) getPlayerByName(playerName);
        if(player == null) return null;
        DestinationCard card = ((IServerBank) getBank()).drawDestinationCard();
        player.addDestinationCard(card);
        return card;
    }

    @Override
    public GameMap getMap() {
        return getGameMap();
    }
}
