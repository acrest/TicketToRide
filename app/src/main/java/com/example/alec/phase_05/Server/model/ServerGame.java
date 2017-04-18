package com.example.alec.phase_05.Server.model;

import com.example.alec.phase_05.Server.command.ServerDiscardTrainCardCommand;
import com.example.alec.phase_05.Shared.command.GameCommand;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.IChatManager;
import com.example.alec.phase_05.Shared.model.IPlayer;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.PlayerTurnStatus;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.alec.phase_05.Shared.model.PlayerTurnStatus.DESTINATION;
import static com.example.alec.phase_05.Shared.model.PlayerTurnStatus.START;
import static com.example.alec.phase_05.Shared.model.PlayerTurnStatus.TRAIN;

/**
 * Created by samuel on 2/25/17.
 */

public class ServerGame extends Game implements IServerGame {
    private PlayerTurnStatus turnStatus;
    private int playerTurnIndex;
    private CommandManager commandManager;
    private IChatManager chatManager;
    private Set<String> haveDrawnInitialDestinationCards;
    private String lastPlayerTurn;

    public ServerGame(int id, String name, int maxPlayers, CommandManager commandManager, IChatManager chatManager, IServerBank bank, GameMap gameMap) {
        super(id, name, maxPlayers, bank, gameMap);
        this.commandManager = commandManager;
        this.chatManager = chatManager;
        turnStatus = START;
        playerTurnIndex = 0;
        haveDrawnInitialDestinationCards = new HashSet<>();
        commandManager.setGame(this);
        lastPlayerTurn = null;
    }

    @Override
    public IChatManager getChatManager() {
        return chatManager;
    }

    public int getCommandSize(){
        return commandManager.getCommandSize();
    }

    @Override
    public void addCommand(GameCommand command) {
        commandManager.addCommand(command);
    }

    @Override
    public GameCommand getCommand(int index){
        return commandManager.getCommand(index);
    }

    @Override
    public ICommand recentCommand(String playerName) {
        return commandManager.recentCommand(playerName);
    }

    @Override
    public TrainCard drawTrainCard(String playerName) {
        System.out.println("drawing train card");
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

    public void incrementPlayerIndex(){
        playerTurnIndex++;
    }

    public void setStartStatus(){
        turnStatus = START;
    }

    public void setDestinationStatus(){
        turnStatus = DESTINATION;
    }

    public void setTrainStatus(){
        turnStatus = TRAIN;
    }

    @Override
    public PlayerTurnStatus getTurnStatus() {
        return turnStatus;
    }

    @Override
    public int getPlayerTurnIndex() {
        return playerTurnIndex%getNumberPlayers();
    }

    @Override
    public void transferChoices(String playerName, DestinationCard[] returnedCards) {
        Player player = (Player) getPlayerByName(playerName);
        if (player != null) {
            for (DestinationCard returned : returnedCards) {
                player.removeCardChoice(returned);
            }
            for (DestinationCard choice : player.getCardChoices()) {
                player.addDestinationCard(choice);
            }
            player.clearCardChoices();
        }
    }

    @Override
    public DestinationCard[] drawDestinationCards(String playerName) {
        Player player = (Player) getPlayerByName(playerName);
        ArrayList<DestinationCard> cards = new ArrayList<>();
        System.out.println("drawing destination cards");
        for (int i = 0; i < 3; i++) {
            DestinationCard card = ((IServerBank) getBank()).drawDestinationCard();
            if (card != null) {
                player.addCardChoice(card);
                cards.add(card);
            }
        }
        return cards.toArray(new DestinationCard[cards.size()]);
    }

    @Override
    public Set<String> getHaveDrawnInitialDestinationCards() {
        return haveDrawnInitialDestinationCards;
    }

    public boolean hasDrawnInitialDestinationCards(String playerName) {
        return haveDrawnInitialDestinationCards.contains(playerName);
    }

    public void setHasDrawnInitialDestinationCards(String playerName) {
        haveDrawnInitialDestinationCards.add(playerName);
    }

    public String getLastPlayerTurn() {
        return lastPlayerTurn;
    }

    public void setLastPlayerTurn(String lastPlayerTurn) {
        this.lastPlayerTurn = lastPlayerTurn;
    }
}
