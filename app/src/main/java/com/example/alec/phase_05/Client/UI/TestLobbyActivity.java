package com.example.alec.phase_05.Client.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.alec.phase_05.Client.ClientFacade;
import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Client.Model.IClientGame;
import com.example.alec.phase_05.Client.Presenter.ILobbyListener;
import com.example.alec.phase_05.Client.Presenter.IPresenterLobby;
import com.example.alec.phase_05.Client.Presenter.PresenterLobby;
import com.example.alec.phase_05.Client.ServerProxy;
import com.example.alec.phase_05.Client.command.ClientJoinGameCommand;
import com.example.alec.phase_05.Client.communication.ClientCommunicator;
import com.example.alec.phase_05.R;
import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Server.model.ServerModel;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameInfo;
import com.example.alec.phase_05.Shared.model.IGame;

public class TestLobbyActivity extends Activity implements ILobbyListener {
    private Button mStartGameButton;
    private TextView mNumberOfPlayers;
    private IPresenterLobby presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_lobby);

        final int gameID = ClientModel.getInstance().getGameID();

      //  ServerFacade.getInstance().joinGame("Andrew", gameID, "green");
        final ServerProxy proxy = new ServerProxy(null, null);

//        ClientFacade.getInstance().joinGame(proxy.joinGame("Andrew", gameID, "green"));

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ClientFacade.getInstance().joinGame(proxy.joinGame("Andrew", gameID, "green"));
            }
        });
        thread.start();


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

        ClientModel model = ClientModel.getInstance();
        mNumberOfPlayers.setText(model.getNumberPlayers() + "/" + model.getGameMaxPlayers());

        if (model.isHost()) {
            mStartGameButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void updateNumberOfPlayers(int num, int max) {
        mNumberOfPlayers.setText(num + "/" + max);
    }

    @Override
    public void onStartGame() {
        Intent i = new Intent(TestLobbyActivity.this, TicketToRideActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        //remove player from game;
        //Poller.getInstance().setListGamePolling();
        //super.onBackPressed();
    }
}
