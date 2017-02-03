package com.example.alec.phase_05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alec.phase_05.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.print("What is the string: \n");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
