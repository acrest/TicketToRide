package com.example.alec.phase_05.Client.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Client.Presenter.ILogInListener;
import com.example.alec.phase_05.Client.Presenter.IPresenterGameStation;
import com.example.alec.phase_05.Client.Presenter.IPresenterLobby;
import com.example.alec.phase_05.Client.Presenter.IPresenterLogIn;
import com.example.alec.phase_05.Client.Presenter.PresenterGameStation;
import com.example.alec.phase_05.Client.Presenter.PresenterLogIn;
import com.example.alec.phase_05.R;

import java.util.HashMap;
import java.util.Map;

public class TestLoginActivity extends Activity implements ILogInListener {

    private Button mLogInButton;
    private EditText mNumberOfPlayers;
    private IPresenterLogIn presenter;
    private int numPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_test_login);

        numPlayers = 2;

        presenter = new PresenterLogIn(this);

        mNumberOfPlayers= (EditText) findViewById(R.id.number_of_players_test);

        mLogInButton = (Button) findViewById(R.id.log_in_button_test);
        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("the button is pressed");
                if (!mNumberOfPlayers.getText().toString().isEmpty()) {
                    numPlayers = Integer.valueOf(mNumberOfPlayers.getText().toString());
                } else {
                    Toast.makeText(TestLoginActivity.this, "You did not specify number of players. Defaulting to 2 players.", Toast.LENGTH_SHORT).show();
                }
                loginUsers();
            }
        });

    }
    private void loginUsers(){
        System.out.println("we login the users");
        presenter.logIn("Alec", "a");
    }


    @Override
    public void onLoginResults(boolean success) {
        if (success) {
            System.out.println("successful now we create a game");

            Intent i = new Intent(TestLoginActivity.this, TestGameStationActivity.class);
            startActivity(i);

        } else {
            System.out.println("unsuccesful");
            Toast.makeText(TestLoginActivity.this, "Server is down. Failed to login or register.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRegisterResults(boolean success) {
    }

}
