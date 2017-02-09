package com.example.alec.phase_05.Client.UI;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.alec.phase_05.R;

public class LobbyActivity extends Activity {
    private Button mStartGameButton;

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
    }
}
