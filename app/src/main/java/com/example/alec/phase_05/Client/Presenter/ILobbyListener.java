package com.example.alec.phase_05.Client.Presenter;

/**
 * Created by samuel on 2/11/17.
 */

public interface ILobbyListener {
    void updateNumberOfPlayers(int num, int max);
    void onStartGame();
}
