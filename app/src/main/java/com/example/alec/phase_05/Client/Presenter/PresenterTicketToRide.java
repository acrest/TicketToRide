package com.example.alec.phase_05.Client.Presenter;

import com.example.alec.phase_05.Client.Demo;
import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Client.Model.PlayerStat;
import com.example.alec.phase_05.Shared.model.Chat;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.IGame;
import com.example.alec.phase_05.Shared.model.IPlayer;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Molly on 2/23/2017.
 */

public class PresenterTicketToRide extends Presenter implements IPresenterTicketToRide {

    private ITicketToRideListener listener;
    private ClientModel model;

    public PresenterTicketToRide(ITicketToRideListener listener) {
        this.listener = listener;
        model = ClientModel.getInstance();
    }

    @Override
    public void drawTrainCard() {
        try {
            model.doDrawTrainCardFromDeck(model.getCurrentPlayerName());
        } catch (Exception e) {

        }
    }

    @Override
    public void pickTrainCard(int deckID) {
        try {
            model.doPickTrainCard(model.getCurrentPlayerName(), deckID);
        } catch (Exception e) {

        }
    }

    @Override
    public void claimRoute(int routeID) {
        try {
            model.doClaimRoute(model.getCurrentPlayerName(), routeID);
        } catch (Exception e) {

        }
    }

    @Override
    public void returnDestinationCard(DestinationCard card) {
        try {
            model.doPutBackDestinationCard(model.getCurrentPlayerName(), card);
        } catch (Exception e) {
        }
    }

    @Override
    public void drawDestinationCard() {
        try {
            model.doDrawDestinationCard(model.getCurrentPlayerName());
        } catch (Exception e) {
        }
    }

    @Override
    public void endTurn() {
        try {
            model.doDrawTrainCardFromDeck(model.getCurrentPlayerName());
        } catch (Exception e) {
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
            }
        });
        List<TrainCard> cards = new ArrayList<>();
        for (int i = 0; i < IGame.NUM_VISIBLE_CARDS; ++i) {
            cards.add(model.getVisibleTrainCard(i));
        }
        listener.updateFaceupTrainCards(cards);
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
        for(DestinationCard card : chosen) {
            model.addDestinationCard(card);
        }
        for(DestinationCard card : notChosen) {
            try {
                model.doPutBackDestinationCard(model.getCurrentPlayerName(), card);
            } catch(Exception e) {

            }
        }
    }

    @Override
    public void sendChat(Chat chat) {
        //TODO send chat
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
