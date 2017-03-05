package com.example.alec.phase_05.Client.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.alec.phase_05.Client.ClientFacade;
import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Client.Model.IClientGame;
import com.example.alec.phase_05.Client.Presenter.ILobbyListener;
import com.example.alec.phase_05.Client.Presenter.IPresenterLobby;
import com.example.alec.phase_05.Client.Presenter.PresenterLobby;
import com.example.alec.phase_05.R;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.IGame;

public class LobbyActivity extends Activity implements ILobbyListener {
    private Button mStartGameButton;
    private TextView mNumberOfPlayers;
    private IPresenterLobby presenter;
    private IClientGame currentGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_lobby);

        mStartGameButton = (Button) findViewById(R.id.lobby_start_button);
        mStartGameButton.setVisibility(View.INVISIBLE);
        mStartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to set visibility of button to game host        mStartGameButton.setVisibility(View.VISIBLE); //To set visible
                presenter.onStartGameButtonPressed();
            }
        });

        mNumberOfPlayers = (TextView) findViewById(R.id.lobby_current_number_players);

        presenter = new PresenterLobby(this);
        currentGame = ClientModel.getInstance().getCurrentGame();
        mNumberOfPlayers.setText(currentGame.getNumberPlayers() + "/" + currentGame.getMaxPlayers());

        if(currentGame.getPlayer(0).getName().equals(ClientModel.getInstance().getCurrentPlayer().getName()))
        {
            mStartGameButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void updateNumberOfPlayers(int num, int max) {
        mNumberOfPlayers.setText(num + "/" + max);
    }

    @Override
    public void onStartGame() {
        if (currentGame.getNumberPlayers() > 1){
            Intent i = new Intent(LobbyActivity.this, TicketToRideActivity.class);
            startActivity(i);
        }

    }

    @Override
    public void onBackPressed()
    {
        //remove player from game;
        //Poller.getInstance().setListGamePolling();
        //super.onBackPressed();
    }
}
