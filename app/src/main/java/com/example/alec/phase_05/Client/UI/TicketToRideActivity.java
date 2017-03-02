package com.example.alec.phase_05.Client.UI;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.TabActivity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.alec.phase_05.Client.Presenter.ITicketToRideListener;
import com.example.alec.phase_05.R;


//public class TicketToRideActivity extends Activity {



public class TicketToRideActivity extends TabActivity {
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    ViewPager vpager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost mTabHost = getTabHost();

        mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("Player Info").setContent(R.id.player_info));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("Routes").setContent(R.id.routes));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("Game History").setContent(R.id.game_history));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test4").setIndicator("Bank").setContent(R.id.bank));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test5").setIndicator("Map").setContent(R.id.map));

        mTabHost.addTab(mTabHost.newTabSpec("tab_test6").setIndicator("Chat").setContent(R.id.chat));

        mTabHost.setCurrentTab(0);

    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ticket_to_ride);
    }
*/
    private void addDrawerItems() {
        String[] osArray = { "I", "am", "a", "test", "wizard" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(TicketToRideActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
