package com.example.alec.phase_05.Client.UI;

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
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.Presenter.PresenterLogIn;
import com.example.alec.phase_05.Client.ServerProxy;
import com.example.alec.phase_05.Client.communication.ClientCommunicator;
import com.example.alec.phase_05.R;

public class StartScreenActivity extends AppCompatActivity {
    private EditText firstIpAddress;
    private EditText secondIpAddress;
    private EditText thirdIpAddress;
    private EditText fourthIpAddress;
    private String ipAddress;

    private EditText numTrains;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start_screen);

        firstIpAddress = (EditText) findViewById(R.id.first_ip_address);
        secondIpAddress = (EditText) findViewById(R.id.second_ip_address);
        thirdIpAddress = (EditText) findViewById(R.id.third_ip_address);
        fourthIpAddress = (EditText) findViewById(R.id.fourth_ip_address);
        numTrains = (EditText) findViewById(R.id.num_of_trains);

        Button mPlayButton = (Button) findViewById(R.id.go_to_playmode);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setIpAndTrains();
                setTrainCount();

                if(fourthIpAddress.getText().toString().isEmpty()||thirdIpAddress.getText().toString().isEmpty()||numTrains.getText().toString().isEmpty()||firstIpAddress.getText().toString().isEmpty()||secondIpAddress.getText().toString().isEmpty()){
                    Toast.makeText(StartScreenActivity.this, "Fill out all fields", Toast.LENGTH_SHORT).show();
                }
                else {

                    if (Integer.parseInt(numTrains.getText().toString()) >= 10 && Integer.parseInt(numTrains.getText().toString()) <= 45) {
                        if (!firstIpAddress.getText().toString().isEmpty()) {
                            Facade.getInstance().setIpAddress(firstIpAddress.getText().toString()+"."+secondIpAddress.getText().toString()+"."+thirdIpAddress.getText().toString()+"."+fourthIpAddress.getText().toString());
                            ClientCommunicator.getInstance().setServerIP(firstIpAddress.getText().toString()+"."+secondIpAddress.getText().toString()+"."+thirdIpAddress.getText().toString()+"."+fourthIpAddress.getText().toString());
                            Facade.getInstance().setTrainCount(Integer.parseInt(numTrains.getText().toString()));
                            Intent i = new Intent(StartScreenActivity.this, LogInActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(StartScreenActivity.this, "Enter valid IP Address", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(StartScreenActivity.this, "Enter valid number of trains (10-45)", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        Button mTestButton = (Button) findViewById(R.id.go_to_testmode);
        mTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setIpAndTrains();
                setTrainCount();

                if(fourthIpAddress.getText().toString().isEmpty()||thirdIpAddress.getText().toString().isEmpty()||numTrains.getText().toString().isEmpty()||firstIpAddress.getText().toString().isEmpty()||secondIpAddress.getText().toString().isEmpty()){
                    Toast.makeText(StartScreenActivity.this, "Fill out all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (Integer.parseInt(numTrains.getText().toString()) >= 10 && Integer.parseInt(numTrains.getText().toString()) <= 45) {
                        if (!firstIpAddress.getText().toString().isEmpty()) {
                            Facade.getInstance().setIpAddress(firstIpAddress.getText().toString()+"."+secondIpAddress.getText().toString()+"."+thirdIpAddress.getText().toString()+"."+fourthIpAddress.getText().toString());
                            ClientCommunicator.getInstance().setServerIP(firstIpAddress.getText().toString()+"."+secondIpAddress.getText().toString()+"."+thirdIpAddress.getText().toString()+"."+fourthIpAddress.getText().toString());
                            Facade.getInstance().setTrainCount(Integer.parseInt(numTrains.getText().toString()));
                            Intent i = new Intent(StartScreenActivity.this, TestLoginActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(StartScreenActivity.this, "Enter valid IP Address", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(StartScreenActivity.this, "Enter valid number of trains (10-45)", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void setIpAndTrains(){

        firstIpAddress.setText("10");
        secondIpAddress.setText("24");
        thirdIpAddress.setText("64");
        fourthIpAddress.setText("220");
    }

    private void setTrainCount(){
        numTrains.setText("10");
    }

}
