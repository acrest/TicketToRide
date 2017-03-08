package com.example.alec.phase_05.Client.Presenter;

import android.os.Handler;
import android.os.Looper;

import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Shared.model.Player;

import java.util.Observable;

/**
 * Created by Molly on 2/23/2017.
 */

public class PresenterTicketToRide extends Presenter implements IPresenterTicketToRide {

    private ITicketToRideListener listener;
    private Facade currentFacade;

    public PresenterTicketToRide(ITicketToRideListener listener) {
        this.listener = listener;
        currentFacade = Facade.getInstance();
    }

    @Override
    public void drawTrainCard() {

    }

    @Override
    public void pickTrainCard(int deckID){

    }

    @Override
    public void claimRoute(int routeID) {

    }

    @Override
    public void returnDestinationCard(Integer cardID){

    }

    @Override
    public void drawDestinationCards() {

    }

    @Override
    public void endTurn() {

    }

    @Override
    public void update(UpdateIndicator u) {
        final ClientModel model = ClientModel.getInstance();
        if(u.needUpdate(ClientModel.PLAYER_TRAIN_CARDS)) {
            listener.updateTrainCards(model.getCurrentPlayer().getTrainCards());
            visitAllOtherPlayers(new PlayerVisitor() {
                @Override
                public void visitPlayer(Player player) {
                    listener.updatePlayerTrainCards(player.getName(), model.getNumberOfTrainCards(player.getName()));
                }
            });
        }
        if(u.needUpdate(ClientModel.PLAYER_DESTINATION_CARDS)) {
            listener.updateDestinationCards(model.getCurrentPlayer().getDestinationCards());
            visitAllOtherPlayers(new PlayerVisitor() {
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

    private interface PlayerVisitor {
        void visitPlayer(Player player);
    }
}
