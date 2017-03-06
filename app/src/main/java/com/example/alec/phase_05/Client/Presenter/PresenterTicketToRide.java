package com.example.alec.phase_05.Client.Presenter;

import android.os.Handler;
import android.os.Looper;

import com.example.alec.phase_05.Client.Model.ClientModel;

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
    public void claimRoute() {

    }

    @Override
    public void drawDestinationCards() {

    }

    @Override
    public void endTurn() {

    }

    @Override
    public void update(UpdateIndicator u) {
        if(u.needUpdate(ClientModel.PLAYER_TRAIN_CARDS)) {

        }
        if(u.needUpdate(ClientModel.PLAYER_DESTINATION_CARDS)) {

        }
        if(u.needUpdate(ClientModel.PLAYER_TRAIN_CARDS)) {

        }
        if(u.needUpdate(ClientModel.PLAYER_TRAIN_CARDS)) {

        }
        if(u.needUpdate(ClientModel.PLAYER_TRAIN_CARDS)) {

        }
    }
}
