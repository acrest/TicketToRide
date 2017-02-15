package com.example.alec.phase_05.Client.Presenter;

import android.os.Handler;
import android.os.Looper;

import com.example.alec.phase_05.Client.ClientModel;
import com.example.alec.phase_05.Client.UI.LobbyActivity;
import com.example.alec.phase_05.Shared.model.GameState;

import java.util.Observable;

/**
 * Created by Andrew on 2/9/2017.
 */

public class PresenterLobby implements IPresenterLobby {
    private ILobbyListener listener;

    public PresenterLobby(ILobbyListener listener) {
        this.listener = listener;
        ClientModel.getInstance().addObserver(this);
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

    private void update(UpdateIndicator u) {
        if(u.needUpdate(ClientModel.NUM_PLAYERS_IN_GAME)) {
            GameState currentGame = ClientModel.getInstance().getCurrentGame();
            int max = currentGame.getMaxPlayers();
            int num = currentGame.getNumberPlayers();
            listener.updateNumberOfPlayers(num, max);
        }
    }
}
