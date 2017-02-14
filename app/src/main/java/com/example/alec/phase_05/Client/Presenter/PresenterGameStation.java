package com.example.alec.phase_05.Client.Presenter;

import android.util.Log;

import com.example.alec.phase_05.Client.ClientModel;
import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.UI.GameStationActivity;
import com.example.alec.phase_05.Shared.model.GameDescription;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Andrew on 2/9/2017.
 */

public class PresenterGameStation implements IPresenterGameStation {

    private IGameStationListener listener;

    public PresenterGameStation(IGameStationListener listener) {
        this.listener = listener;
        ClientModel.getInstance().addObserver(this);
    }

    @Override
    public void joinGame(int gameID, String color) {
        Facade.getInstance().joinGame(gameID, color);
    }

    @Override
    public void createGame(String hostColor, String gameName, int numberOfPlayers) {
        Facade.getInstance().createGame(numberOfPlayers, gameName, hostColor);
    }

    @Override
    public void update(Observable observable, Object o) {
        if(!(o instanceof UpdateIndicator)) {
            throw new IllegalArgumentException("object passed to update() must be of type UpdateIndicator");
        }
        UpdateIndicator u = (UpdateIndicator) o;
        if(u.needUpdate(ClientModel.GAME_LIST)) {
            listener.updateGameList(ClientModel.getInstance().getGameList());
            GameDescription gameDescription = findGameByID(listener.getCurrentGameID());
            if(gameDescription != null) {
                Collection<String> usedColors = gameDescription.getAllUsedColors();
                listener.hideRed(usedColors.contains("red"));
                listener.hideBlue(usedColors.contains("blue"));
                listener.hideYellow(usedColors.contains("yellow"));
                listener.hideGreen(usedColors.contains("green"));
                listener.hideBlack(usedColors.contains("black"));
            }
        }
        if(u.needUpdate(ClientModel.CREATE_GAME_SUCCESS)) {
            listener.createGameSuccess(true);
        }
        if(u.needUpdate(ClientModel.CREATE_GAME_FAILURE)) {
            listener.createGameSuccess(false);
        }
        if(u.needUpdate(ClientModel.JOIN_GAME_SUCCESS)) {
            listener.joinGameSuccess(true);
        }
        if(u.needUpdate(ClientModel.JOIN_GAME_FAILURE)) {
            listener.joinGameSuccess(false);
        }
    }

    private GameDescription findGameByID(int id) {
        for(GameDescription gameDescription : ClientModel.getInstance().getGameList()) {
            if(gameDescription.getID() == id) {
                return gameDescription;
            }
        }
        return null;
    }
}
