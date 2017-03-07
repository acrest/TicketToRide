package com.example.alec.phase_05.Client.Presenter;

import android.os.Handler;
import android.os.Looper;

import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Client.Model.IClientGame;
import com.example.alec.phase_05.Client.Poller;
import com.example.alec.phase_05.Shared.model.GameDescription;

import java.util.Observable;

/**
 * Created by Andrew on 2/9/2017.
 */

public class PresenterLobby extends Presenter implements IPresenterLobby {
    private ILobbyListener listener;

    public PresenterLobby(ILobbyListener listener) {
        this.listener = listener;
        ClientModel.getInstance().addObserver(this);
    }

    @Override
    public void onStartGameButtonPressed() {
        listener.onStartGame();
        Poller poller = Poller.getInstance();
        poller.setModelPolling();
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
        if(u.needUpdate(ClientModel.NUM_PLAYERS_IN_GAME)) {
            ClientModel model = ClientModel.getInstance();
            int max = model.getGameMaxPlayers();
            int num = model.getNumberPlayers();
            listener.updateNumberOfPlayers(num, max);
            if(num == max) listener.onStartGame();
        }
    }
}
