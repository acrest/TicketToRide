package com.example.alec.phase_05.Server.model;

import com.example.alec.phase_05.Shared.command.GameCommand;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.IChatManager;
import com.example.alec.phase_05.Shared.model.IPlayer;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

    @Override
    public Map<String, Integer> getBonusPoints() {
        Map<String, Integer> bonusPoints = new HashMap<>();
        for (IPlayer player : getPlayers()) {
            bonusPoints.put(player.getName(), getBonusPoints(player));
        }
        return bonusPoints;
    }

    private int getBonusPoints(IPlayer player) {
        Player realPlayer = (Player) player;
        int points = 0;
        for (DestinationCard card : realPlayer.getDestinationCards()) {
            if (getMap().checkIfDestinationComplete2(card, player.getName())) { //causes a crash
                points += card.getValue();
            } else {
                points -= card.getValue();
            }
        }
        return points;
    }

    @Override
    public void addDestinationCardToBottom(DestinationCard card) {
        ((IServerBank) getBank()).addDestinationCardToBottom(card);
    }

    @Override
    public void removeCardFromPlayerHand(String playerName, DestinationCard card) {
        Player player = (Player) getPlayerByName(playerName);
        if (player != null) {
            player.removeDestinationCard(card);
        }
    }

    @Override
    public void setPlayer(int position, IPlayer player) {
        player.setTrainCount(ServerModel.getInitialTrainCount());
        super.setPlayer(position, player);
    }

    @Override
    public int addPlayerAtNextPosition(IPlayer player) {
        player.setTrainCount(ServerModel.getInitialTrainCount());
        return super.addPlayerAtNextPosition(player);
    }
}
