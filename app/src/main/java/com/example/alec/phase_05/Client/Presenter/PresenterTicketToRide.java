package com.example.alec.phase_05.Client.Presenter;

import android.os.Handler;
import android.os.Looper;

import com.example.alec.phase_05.Shared.model.Player;

import java.util.Observable;

/**
 * Created by Molly on 2/23/2017.
 */

public class PresenterTicketToRide extends Presenter implements IPresenterTicketToRide {

    private ITicketToRideListener listener;

    public PresenterTicketToRide(ITicketToRideListener listener) {
        this.listener = listener;
    }

    @Override
    public void drawTrainCard(int index) {

    }

    @Override
    public void claimRoute(Player player, Integer routID) {

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
    public void update(Observable observable, Object o) {
        if(!(o instanceof UpdateIndicator)) {
            throw new IllegalArgumentException("object passed to update() must be of type UpdateIndicator");
        }
        final UpdateIndicator u = (UpdateIndicator) o;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                update(u);
            }
        };
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(runnable);
    }

    @Override
    public void update(UpdateIndicator u) {
    }
}
