package com.example.alec.phase_05.Client.UI;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.alec.phase_05.Client.Presenter.ITicketToRideListener;
import com.example.alec.phase_05.Shared.model.Chat;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.StateWarning;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.List;
import java.util.Map;

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


    @Override
    public void updateTrainCards(List<TrainCard> cards) {

    }

    @Override
    public void updateDestinationCards(List<DestinationCard> cards) {

    }

    @Override
    public void updatePlayerTrainCards(String playerName, int count) {

    }

    @Override
    public void updatePlayerDestinationCards(String playerName, int count) {

    }

    @Override
    public void updateChats(List<Chat> chats) {

    }

    @Override
    public void updatePlayerPoints(String playerName, int points) {

    }

    @Override
    public void updatePlayerTrainCount(String playerName, int count) {

    }

    @Override
    public void updateFaceupTrainCards(List<TrainCard> cards) {

    }

    @Override
    public void updateMap(GameMap map) {

    }

    @Override
    public void pickDestinationCards(List<DestinationCard> cards) {

    }

    @Override
    public void initPickDestinationCards(List<DestinationCard> cards) {

    }

    @Override
    public void onTurnStart() {

    }

    @Override
    public void onGameFinished() {

    }

    @Override
    public void updateGameState(GameState state) {

    }

    @Override
    public void handleWarning(StateWarning warning) {

    }

    @Override
    public void updateLongestPath(Map<Player, Integer> longestRoute) {

    }
}
