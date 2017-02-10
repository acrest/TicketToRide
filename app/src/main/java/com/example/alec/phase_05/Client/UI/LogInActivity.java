package com.example.alec.phase_05.Client.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alec.phase_05.R;

public class LogInActivity extends Activity {
    private Button mLogInButton;
    private Button mRegisterButton;
    private EditText mLogInUserNameEditText;
    private EditText mLogInPasswordEditText;
    private EditText mRegisterUserNameEditText;
    private EditText mRegisterPasswordEditText;
    private EditText mRegisterConfirmEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_log_in);

        mLogInUserNameEditText = (EditText) findViewById(R.id.sign_in_username_edit);
        mLogInPasswordEditText = (EditText) findViewById(R.id.sign_in_password_edit);
        mRegisterUserNameEditText = (EditText) findViewById(R.id.register_username_edit);
        mRegisterPasswordEditText = (EditText) findViewById(R.id.register_password_edit);
        mRegisterConfirmEditText = (EditText) findViewById(R.id.register_confirm_edit);

        mLogInButton = (Button) findViewById(R.id.log_in_button);
        mLogInButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!mLogInUserNameEditText.getText().toString().isEmpty() && !mLogInPasswordEditText.getText().toString().isEmpty())
                {
                    String something = mLogInUserNameEditText.toString();
                    String somethings = mLogInPasswordEditText.toString();
                    Intent i = new Intent(LogInActivity.this, GameStationActivity.class);
                    startActivity(i);
                }
                else
                {
                    mLogInPasswordEditText.setText("");
                    Toast.makeText(LogInActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mRegisterButton = (Button) findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(!mRegisterUserNameEditText.getText().toString().isEmpty() && !mRegisterPasswordEditText.getText().toString().isEmpty() && !mRegisterConfirmEditText.getText().toString().isEmpty())
                {
                    if(mRegisterPasswordEditText.getText().toString().equals(mRegisterConfirmEditText.getText().toString()))
                    {
                        Intent i = new Intent(LogInActivity.this, GameStationActivity.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(LogInActivity.this, "Confirmed password did not match.", Toast.LENGTH_SHORT).show();
                        mRegisterPasswordEditText.setText("");
                        mRegisterConfirmEditText.setText("");
                    }
                }
                else
                {
                    Toast.makeText(LogInActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                    mRegisterPasswordEditText.setText("");
                    mRegisterConfirmEditText.setText("");
                }
            }
        });
    }
}
