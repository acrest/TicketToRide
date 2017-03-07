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
        ClientModel model = ClientModel.getInstance();
        if(u.needUpdate(ClientModel.PLAYER_TRAIN_CARDS)) {

        }
        if(u.needUpdate(ClientModel.PLAYER_DESTINATION_CARDS)) {

        }
        if(u.needUpdate(ClientModel.CHAT)) {

        }
        if(u.needUpdate(ClientModel.PLAYER_POINTS)) {

        }
        if(u.needUpdate(ClientModel.PLAYER_ROUTE)) {

        }
    }
}
