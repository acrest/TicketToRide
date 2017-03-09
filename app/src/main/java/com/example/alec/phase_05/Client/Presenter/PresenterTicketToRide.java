package com.example.alec.phase_05.Client.Presenter;

import com.example.alec.phase_05.Client.Demo;
import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Shared.model.IGame;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Molly on 2/23/2017.
 */

public class PresenterTicketToRide extends Presenter implements IPresenterTicketToRide {

    private ITicketToRideListener listener;
    private Facade facade;

    public PresenterTicketToRide(ITicketToRideListener listener) {
        this.listener = listener;
        facade = Facade.getInstance();
    }

    @Override
    public void drawTrainCard() {
        facade.drawTrainCard();
    }

    @Override
    public void pickTrainCard(int deckID) {
        facade.pickTrainCard(deckID);
    }

    @Override
    public void discardTrainCard(TrainCard card) {
        facade.discardTrainCard(card);
    }

    @Override
    public void claimRoute(int routeID) {
    }

    @Override
    public void returnDestinationCard(Integer cardID) {

    }

    @Override
    public void drawDestinationCards() {

    }

    @Override
    public void endTurn() {

    }

    @Override
    public void updateAll() {
        final ClientModel model = ClientModel.getInstance();
        listener.updateTrainCards(model.getCurrentPlayer().getTrainCards());
        visitAllOtherPlayers(new PlayerVisitor() {
            @Override
            public void visitPlayer(Player player) {
                listener.updatePlayerTrainCards(player.getName(), model.getNumberOfTrainCards(player.getName()));
            }
        });
        listener.updateDestinationCards(model.getCurrentPlayer().getDestinationCards());
        visitAllOtherPlayers(new PlayerVisitor() {
            @Override
            public void visitPlayer(Player player) {
                listener.updatePlayerDestinationCards(player.getName(), model.getNumberOfDestinationCards(player.getName()));
            }
        });
        listener.updateChats(model.getChats());
        visitAllPlayers(new PlayerVisitor() {
            @Override
            public void visitPlayer(Player player) {
                listener.updatePlayerPoints(player.getName(), player.getPointCount());
            }
        });
        listener.updateMap(ClientModel.getInstance().getGameMap());
        visitAllPlayers(new PlayerVisitor() {
            @Override
            public void visitPlayer(Player player) {
                listener.updatePlayerTrainCount(player.getName(), player.getTrainCount());
            }
        });
        List<TrainCard> cards = new ArrayList<>();
        for (int i = 0; i < IGame.NUM_VISIBLE_CARDS; ++i) {
            cards.add(ClientModel.getInstance().getVisibleTrainCard(i));
        }
        listener.updateFaceupTrainCards(cards);
    }

    @Override
    public void startDemo() {
        Demo.startDemo();
    }

    @Override
    public void update(UpdateIndicator u) {
        final ClientModel model = ClientModel.getInstance();
        if(u.needUpdate(ClientModel.PLAYER_TRAIN_CARDS)) {
            listener.updateTrainCards(model.getCurrentPlayer().getTrainCards());
            visitAllPlayers(new PlayerVisitor() {
                @Override
                public void visitPlayer(Player player) {
                    listener.updatePlayerTrainCards(player.getName(), model.getNumberOfTrainCards(player.getName()));
                }
            });
        }
        if(u.needUpdate(ClientModel.PLAYER_DESTINATION_CARDS)) {
            listener.updateDestinationCards(model.getCurrentPlayer().getDestinationCards());
            visitAllPlayers(new PlayerVisitor() {
                @Override
                public void visitPlayer(Player player) {
                    listener.updatePlayerDestinationCards(player.getName(), model.getNumberOfDestinationCards(player.getName()));
                }
            });
        }
        if(u.needUpdate(ClientModel.CHAT)) {
            listener.updateChats(model.getChats());
        }
        if(u.needUpdate(ClientModel.PLAYER_POINTS)) {
            visitAllPlayers(new PlayerVisitor() {
                @Override
                public void visitPlayer(Player player) {
                    listener.updatePlayerPoints(player.getName(), player.getPointCount());
                }
            });
        }
        if(u.needUpdate(ClientModel.GAME_MAP)) {
            listener.updateMap(ClientModel.getInstance().getGameMap());
        }
        if(u.needUpdate(ClientModel.PLAYER_TRAIN_COUNT)) {
            visitAllPlayers(new PlayerVisitor() {
                @Override
                public void visitPlayer(Player player) {
                    listener.updatePlayerTrainCount(player.getName(), player.getTrainCount());
                }
            });
        }
        if(u.needUpdate(ClientModel.VISIBLE_TRAIN_CARDS)) {
            List<TrainCard> cards = new ArrayList<>();
            for(int i = 0; i < IGame.NUM_VISIBLE_CARDS; ++i) {
                cards.add(ClientModel.getInstance().getVisibleTrainCard(i));
            }
            listener.updateFaceupTrainCards(cards);
        }
    }

    private void visitAllOtherPlayers(PlayerVisitor visitor) {
        ClientModel model = ClientModel.getInstance();
        for(int i = 0; i < model.getGameMaxPlayers(); ++i) {
            Player player = model.getPlayer(i);
            if(player != null && !model.getCurrentPlayer().getName().equals(player.getName())) {
                visitor.visitPlayer(player);
            }
        }
    }

    private void visitAllPlayers(PlayerVisitor visitor) {
        ClientModel model = ClientModel.getInstance();
        for(int i = 0; i < model.getGameMaxPlayers(); ++i) {
            Player player = model.getPlayer(i);
            if(player != null) {
                visitor.visitPlayer(player);
            }
        }
    }

    public Player getCurrPlayer() {
        ClientModel model = ClientModel.getInstance();
        return model.getCurrentPlayer();
    }

    public ArrayList<TrainCard> getTrainCards() {
        ClientModel model = ClientModel.getInstance();
        ArrayList<TrainCard> cardList = model.getCurrentPlayer().getTrainCards();
        return cardList;
    }

    public ArrayList<Player> getPlayers() {
        ClientModel model = ClientModel.getInstance();
        int num_players = model.getNumberPlayers();
        ArrayList<Player> playerList = new ArrayList<Player>();

        for (int i = 0; i < num_players; i++) {
            Player temp_player = model.getPlayer(i);

            playerList.add(temp_player);
        }

        return playerList;
    }

    private interface PlayerVisitor {
        void visitPlayer(Player player);
    }
}
