package com.example.alec.phase_05.Client.Presenter;

import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Client.Poller;

/**
 * Created by Andrew on 2/9/2017.
 */

public class PresenterLobby extends Presenter implements IPresenterLobby {
    private ILobbyListener listener;
    private boolean gameStarted;

    public PresenterLobby(ILobbyListener listener) {
        this.listener = listener;
        gameStarted = false;
    }

    @Override
    public void onStartGameButtonPressed() {
        requestStartGame();
    }

    private void onGameStart() {
        listener.onStartGame();
        Poller poller = Poller.getInstance();
        poller.setModelPolling();
        ClientModel.getInstance().deleteObserver(this); //stops the ticket to ride activity from being made every time the poller activates
    }

    private void requestStartGame() {
        System.out.println("request start game " + ClientModel.getInstance().getGameID());

        if (ClientModel.getInstance().getNumberPlayers() < 2 || gameStarted) return;
        Facade.getInstance().startGame();
        gameStarted = true;
    }

    @Override
    public void update(UpdateIndicator u) {
        if (u.needUpdate(ClientModel.NUM_PLAYERS_IN_GAME)) {
            ClientModel model = ClientModel.getInstance();
            int max = model.getGameMaxPlayers();
            int num = model.getNumberPlayers();
            listener.updateNumberOfPlayers(num, max);
            if (num == max && model.isHost()) {
                requestStartGame();
            }
        }
        if (u.needUpdate(ClientModel.GAME_START)) {
            onGameStart();
        }
    }
}
