package com.example.alec.phase_05.Client.Presenter;

import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Shared.model.IPlayer;

import java.util.Iterator;
import java.util.Observable;

/**
 * Created by samuel on 3/22/17.
 */

public class PresenterTicketToRideEndGame extends Presenter implements IPresenterTicketToRideEndGame {
    private ITicketToRideEndGameListener listener;
    private ClientModel model;

    public PresenterTicketToRideEndGame(ITicketToRideEndGameListener listener) {
        this.listener = listener;
        model = ClientModel.getInstance();
    }

    @Override
    public Iterator<String> getPlayerNames() {
        return new Iterator<String>() {
            private ClientModel model = ClientModel.getInstance();
            private int index = 0;

            @Override
            public boolean hasNext() {
                return model.getPlayer(index) != null;
            }

            @Override
            public String next() {
                String player = model.getPlayer(index).getName();
                int nextIndex = index + 1;
                while(nextIndex <= model.getGameMaxPlayers()) {
                    if(nextIndex == model.getGameMaxPlayers() || model.getPlayer(nextIndex) != null) {
                        index = nextIndex;
                        break;
                    }
                }
                return player;
            }
        };
    }

    @Override
    public int getPoints(String playerName) {
        return model.getPlayerPoints(playerName);
    }

    @Override
    public int getPenalties(String playerName) {
        return 0;
    }

    @Override
    public int getTotal(String playerName) {
        return model.getPlayerPoints(playerName);
    }

    @Override
    public String getColor(String playerName) {
        return model.getPlayerByName(playerName).getColor();
    }

    @Override
    public String getWinner() {
        return "Winner Name";
    }

    @Override
    public String getLongestRouteHolder() {
        return "Longest Route Holder Name";
    }

    @Override
    public void update(UpdateIndicator updateIndicator) {

    }
}
