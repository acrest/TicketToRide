package com.example.alec.phase_05.Client.Presenter;

import com.example.alec.phase_05.Client.Demo;
import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Client.Model.PlayerStat;
import com.example.alec.phase_05.Shared.model.Chat;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.IGame;
import com.example.alec.phase_05.Shared.model.IPlayer;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.StateWarning;
import com.example.alec.phase_05.Shared.model.TrainCard;
import com.example.alec.phase_05.Shared.model.TrainType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Molly on 2/23/2017.
 */

public class PresenterTicketToRide extends Presenter implements IPresenterTicketToRide {

    private ITicketToRideListener listener;
    private ClientModel model;
    private boolean initPick;

    public PresenterTicketToRide(ITicketToRideListener listener) {
        this.listener = listener;
        model = ClientModel.getInstance();
        initPick = true;
    }

    @Override
    public void drawTrainCard() {
        try {
            model.doDrawTrainCardFromDeck();
        } catch (StateWarning e) {
            listener.handleWarning(e);
        }
    }

    @Override
    public void pickTrainCard(int deckID) {
        try {
            model.doPickTrainCard(deckID);
        } catch (StateWarning e) {
            listener.handleWarning(e);
        }
    }

    public void claimRoute(int routeID, TrainType type) {
        try {
            model.doClaimRoute(routeID, type);
//            model.setLongestPath();
        } catch (StateWarning e) {
            listener.handleWarning(e);
        }
    }

    @Override
    public void returnDestinationCards(DestinationCard[] cards) {
        Facade.getInstance().putBackDestinationCards(cards);
    }

    @Override
    public void drawDestinationCards() {
        try {
            model.doDrawDestinationCards();
        } catch (StateWarning e) {
            listener.handleWarning(e);
        }
    }

    @Override
    public void endTurn() {
        try {
            model.doEndTurn();
        } catch (StateWarning e) {
            listener.handleWarning(e);
        }
    }

    @Override
    public void updateAll() {
        listener.updateTrainCards(model.getTrainCards());
        visitAllPlayers(new PlayerVisitor() {
            @Override
            public void visitPlayer(IPlayer player) {
                listener.updatePlayerTrainCards(player.getName(), model.getTrainCardCount(player.getName()));
            }
        });
        listener.updateDestinationCards(model.getDestinationCards());
        visitAllPlayers(new PlayerVisitor() {
            @Override
            public void visitPlayer(IPlayer player) {
                listener.updatePlayerDestinationCards(player.getName(), model.getDestinationCardCount(player.getName()));
            }
        });
        listener.updateChats(model.getChats());
        visitAllPlayers(new PlayerVisitor() {
            @Override
            public void visitPlayer(IPlayer player) {
                listener.updatePlayerPoints(player.getName(), player.getPoints());
            }
        });
        listener.updateMap(model.getMap());
        visitAllPlayers(new PlayerVisitor() {
            @Override
            public void visitPlayer(IPlayer player) {
                listener.updatePlayerTrainCount(player.getName(), player.getTrainCount());
//                listener.updateLongestPath(model.getLongestRoute());
            }
        });
        List<TrainCard> cards = new ArrayList<>();
        for (int i = 0; i < IGame.NUM_VISIBLE_CARDS; ++i) {
            cards.add(model.getVisibleTrainCard(i));
        }
        listener.updateFaceupTrainCards(cards);
        listener.updateGameState(model.getGameState());
        listener.updateLongestPath(model.getLongestRoute());
    }

    @Override
    public void startDemo() {
        Demo.startDemo();
    }

    @Override
    public List<PlayerStat> getPlayerStats() {
        List<PlayerStat> stats = new ArrayList<>();
        for (int i = 0; i < model.getGameMaxPlayers(); i++) {
            IPlayer player = model.getPlayer(i);
            if (player != null) {
                stats.add(new PlayerStat(player));
            }
        }
        return stats;
    }

    @Override
    public void chooseDestinationCards(List<DestinationCard> chosen, List<DestinationCard> notChosen) {
        for (DestinationCard card : chosen) {
            model.addDestinationCard(card);
        }
        Facade.getInstance().putBackDestinationCards(notChosen.toArray(new DestinationCard[notChosen.size()]));
        model.clearCardChoices();
        if(!initPick) {
            try {
                model.doEndTurn();
            } catch(StateWarning warning) {
                listener.handleWarning(warning);
            }
        } else {
            initPick = false;
        }
    }

    @Override
    public void sendChat(Chat chat) {
        Facade.getInstance().sendChat(chat);
    }

    @Override
    public Map<String, Integer> getLongestPlayer() {
        return model.getLongestRoute();
    }

    @Override
    public String longestPath() {
        System.out.println("longest path");
        Map<String, Integer> longestRouteInfo = model.getLongestRoute();
        String playerName = "";
        int length = 0;

        if (longestRouteInfo == null) {
            System.out.println("PRESENTER no info");
            playerName = "No one ";
        } else {
            System.out.println("in the else for longest path");
            Iterator<Map.Entry<String, Integer>> iter = longestRouteInfo.entrySet().iterator();
            Map.Entry<String, Integer> entry = null;
            String key = "No one ";
            if (iter.hasNext()) {
                entry = iter.next();
                System.out.println("String key = entry.getKey();");
                key = entry.getKey();
                System.out.println("length = entry.getValue();");
                length = entry.getValue();
            }
           // Map.Entry<String, Integer> entry = longestRouteInfo.entrySet().iterator().next();


            System.out.println("playerName = key;");
            playerName = key;
            System.out.println("in the else for longest path");
            System.out.println(playerName);


            System.out.println("before iterator");
            Iterator<Map.Entry<String, Integer>> iterator = longestRouteInfo.entrySet().iterator();
            while (iterator.hasNext()) {
                System.out.println("in while");
                entry = iterator.next();
                key = entry.getKey();
                length = entry.getValue();
                playerName = playerName + ", " + key;
            }
        }
        playerName = playerName + " has the longest route of " + Integer.toString(length);
        System.out.println("PRESENTER " + playerName);
        return playerName;
    }

    @Override
    public void update(UpdateIndicator u) {
        if (u.needUpdate(ClientModel.PLAYER_TRAIN_CARDS)) {
            listener.updateTrainCards(model.getTrainCards());
            visitAllPlayers(new PlayerVisitor() {
                @Override
                public void visitPlayer(IPlayer player) {
                    listener.updatePlayerTrainCards(player.getName(), model.getTrainCardCount(player.getName()));
                }
            });
        }
        if (u.needUpdate(ClientModel.PLAYER_DESTINATION_CARDS)) {
            listener.updateDestinationCards(model.getDestinationCards());
            visitAllPlayers(new PlayerVisitor() {
                @Override
                public void visitPlayer(IPlayer player) {
                    listener.updatePlayerDestinationCards(player.getName(), model.getDestinationCardCount(player.getName()));
                }
            });
        }
        if (u.needUpdate(ClientModel.CHAT)) {
            listener.updateChats(model.getChats());
        }
        if (u.needUpdate(ClientModel.PLAYER_POINTS)) {
            visitAllPlayers(new PlayerVisitor() {
                @Override
                public void visitPlayer(IPlayer player) {
                    listener.updatePlayerPoints(player.getName(), player.getPoints());
                }
            });
        }
        if (u.needUpdate(ClientModel.GAME_MAP)) {
            listener.updateMap(ClientModel.getInstance().getMap());
        }
        if (u.needUpdate(ClientModel.PLAYER_TRAIN_COUNT)) {
            visitAllPlayers(new PlayerVisitor() {
                @Override
                public void visitPlayer(IPlayer player) {
                    listener.updatePlayerTrainCount(player.getName(), player.getTrainCount());
                }
            });
        }
        if (u.needUpdate(ClientModel.VISIBLE_TRAIN_CARDS)) {
            List<TrainCard> cards = new ArrayList<>();
            for (int i = 0; i < IGame.NUM_VISIBLE_CARDS; ++i) {
                cards.add(model.getVisibleTrainCard(i));
            }
            listener.updateFaceupTrainCards(cards);
        }
        if (u.needUpdate(ClientModel.DISPLAY_HAND)) {
            listener.pickDestinationCards(model.getCardChoices());
        }
        if (u.needUpdate(ClientModel.INIT_DISPLAY_HAND)) {
            listener.initPickDestinationCards(model.getCardChoices());
        }
        if (u.needUpdate(ClientModel.PLAYER_TURN_START)) {
            listener.onTurnStart();
        }
        if(u.needUpdate(ClientModel.GAME_FINISHED_REQUEST)) {
            Facade.getInstance().finishGame();
        }
        if (u.needUpdate(ClientModel.GAME_FINISHED)) {
            listener.onGameFinished();
        }
        if(u.needUpdate(ClientModel.GAME_STATE)) {
            listener.updateGameState(model.getGameState());
        }
        if(u.needUpdate(ClientModel.ROUTE)) {
            listener.updateLongestPath(model.getLongestRoute());
        }
    }

    private void visitAllPlayers(PlayerVisitor visitor) {
        for (int i = 0; i < model.getGameMaxPlayers(); ++i) {
            IPlayer player = model.getPlayer(i);
            if (player != null) {
                visitor.visitPlayer(player);
            }
        }
    }

//    public Player getCurrPlayer() {
//        return ClientModel.getInstance().getCurrentPlayer();
//    }

//    public ArrayList<TrainCard> getTrainCards() {
//        return ClientModel.getInstance().getCurrentPlayer().getTrainCards();
//    }

//    public ArrayList<Player> getPlayers() {
//        ClientModel model = ClientModel.getInstance();
//        int num_players = model.getNumberPlayers();
//        ArrayList<Player> playerList = new ArrayList<>();
//
//        for (int i = 0; i < num_players; i++) {
//            Player temp_player = model.getPlayer(i);
//
//            playerList.add(temp_player);
//        }
//
//        return playerList;
//    }

    private interface PlayerVisitor {
        void visitPlayer(IPlayer player);
    }
}
