package com.example.alec.phase_05.Client.UI;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.alec.phase_05.Client.Presenter.ILobbyListener;
import com.example.alec.phase_05.Client.Presenter.IPresenterLobby;
import com.example.alec.phase_05.Client.Presenter.MockPresenterLobby;
import com.example.alec.phase_05.Client.Presenter.PresenterLobby;
import com.example.alec.phase_05.R;

public class LobbyActivity extends Activity implements ILobbyListener {
    private Button mStartGameButton;
    private TextView mNumberOfPlayers;
    private IPresenterLobby presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_lobby);

        mStartGameButton = (Button) findViewById(R.id.lobby_start_button);
        mStartGameButton.setEnabled(true);
        mStartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to set visibility of button to game host        mStartGameButton.setVisibility(View.VISIBLE); //To set visible
            }
        });

        mNumberOfPlayers = (TextView) findViewById(R.id.lobby_current_number_players);

        presenter = new PresenterLobby(this);
    }

    @Override
    public void updateNumberOfPlayers(int num, int max) {
        mNumberOfPlayers.setText(num + "/" + max);
    }
}
