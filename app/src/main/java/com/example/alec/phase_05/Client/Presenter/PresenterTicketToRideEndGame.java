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

    public PresenterTicketToRideEndGame(ITicketToRideEndGameListener listener) {
        this.listener = listener;
    }

    @Override
    public Iterator<IPlayer> getPlayers() {
        return new Iterator<IPlayer>() {
            private ClientModel model = ClientModel.getInstance();
            private int index = 0;

            @Override
            public boolean hasNext() {
                return model.getPlayer(index) != null;
            }

            @Override
            public IPlayer next() {
                IPlayer player = model.getPlayer(index);
                int nextIndex = index + 1;
                while(nextIndex < model.getGameMaxPlayers()) {
                    if(model.getPlayer(nextIndex) != null) {
                        index = nextIndex;
                        break;
                    }
                }
                return player;
            }
        };
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
