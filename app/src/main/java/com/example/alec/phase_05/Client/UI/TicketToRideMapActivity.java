package com.example.alec.phase_05.Client.UI;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.alec.phase_05.Client.Presenter.ITicketToRideListener;

/**
 * Created by clarkpathakis on 2/26/17.
 */

public class TicketToRideMapActivity extends AppCompatActivity implements ITicketToRideListener {
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    ViewPager vpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new TicketToRideView(this));
        //setContentView(R.layout.activity_ticket_to_ride);

        //mDrawerList = (ListView) findViewById(R.id.navList);

//        TabHost tabHost = getTabHost();
    }

    
}
