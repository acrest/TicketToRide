package com.example.alec.phase_05.Client.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.alec.phase_05.R;

public class LogInActivity extends Activity {
    private Button mLogInButton;
    private Button mRegisterButton;
    private EditText mLogInUserNameEditText;
    private EditText mLogInPasswordEditText;
    private EditText mRegisterUserNameEditText;
    private EditText mRegisterPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_log_in);

        mLogInButton = (Button) findViewById(R.id.log_in_button);
        mLogInButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(LogInActivity.this, GameStationActivity.class);
                startActivity(i);
            }
        });

        mRegisterButton = (Button) findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(LogInActivity.this, GameStationActivity.class);
                startActivity(i);
            }
        });
    }
}
