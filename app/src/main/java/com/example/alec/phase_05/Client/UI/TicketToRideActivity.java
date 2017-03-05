package com.example.alec.phase_05.Client.UI;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.TabActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alec.phase_05.Client.Model.Chat_Item;
import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Client.Model.Derpness;
import com.example.alec.phase_05.Client.Model.IClientGame;
import com.example.alec.phase_05.Client.Presenter.IPresenterLobby;
import com.example.alec.phase_05.Client.Presenter.IPresenterTicketToRide;
import com.example.alec.phase_05.Client.Presenter.ITicketToRideListener;
import com.example.alec.phase_05.Client.Presenter.PresenterLobby;
import com.example.alec.phase_05.Client.Presenter.PresenterTicketToRide;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import android.app.Activity;
import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import com.example.alec.phase_05.R;
import com.example.alec.phase_05.Shared.model.TrainCard;
import com.example.alec.phase_05.Shared.model.TrainType;

import java.util.ArrayList;
import java.util.List;

//public class TicketToRideActivity extends Activity {



public class TicketToRideActivity extends TabActivity implements ITicketToRideListener {
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    ViewPager vpager;
    private IPresenterTicketToRide presenter;
    private RecyclerView mGameRecView;
    private DerpAdapter mRecyclerAdapter;
    private IClientGame currentGame;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_to_ride);

        presenter = new PresenterTicketToRide(this);
        currentGame = ClientModel.getInstance().getCurrentGame();

        mGameRecView = (RecyclerView) findViewById(R.id.rec_chat_list);
        mGameRecView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerAdapter = new DerpAdapter(Derpness.getInstance().generateFakeChat(), this);
        mGameRecView.setAdapter(mRecyclerAdapter);

        TabHost mTabHost = getTabHost();

        mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("Player Info").setContent(R.id.player_info));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("Routes").setContent(R.id.routes));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("Game History").setContent(R.id.game_history));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test4").setIndicator("Bank").setContent(R.id.bank));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test5").setIndicator("Map").setContent(R.id.map));

        mTabHost.addTab(mTabHost.newTabSpec("tab_test6").setIndicator("Chat").setContent(R.id.chat));

        mTabHost.setCurrentTab(0);

        TextView redCard;
        TextView orangeCard;
        TextView yellowCard;
        TextView greenCard;
        TextView blueCard;
        TextView purpleCard;
        TextView whiteCard;
        TextView blackCard;
        TextView goldCard;
        TextView rainbowCard;

        redCard = (TextView) findViewById(R.id.red_cards);
        orangeCard = (TextView) findViewById(R.id.orange_cards);
        yellowCard = (TextView) findViewById(R.id.yellow_cards);
        greenCard = (TextView) findViewById(R.id.green_cards);
        blueCard = (TextView) findViewById(R.id.blue_cards);
        purpleCard = (TextView) findViewById(R.id.purple_cards);
        whiteCard = (TextView) findViewById(R.id.white_cards);
        blackCard = (TextView) findViewById(R.id.black_cards);
        rainbowCard = (TextView) findViewById(R.id.rainbow_cards);


        ArrayList<TrainCard> cardList = currentGame.getPlayer(1).getTrainCards();
        int red_coal = 0;
        int orange_tanker = 0;
        int yellow_boxcar = 0;
        int green_caboose = 0;
        int blue_passenger = 0;
        int purple_freight = 0;
        int white_reefer = 0;
        int black_hopper = 0;
        int rainbow_any = 0;
        for (int i = 0; i < cardList.size(); i++) {
            TrainType trainType = cardList.get(i).getType();
            switch (trainType) {
                case COAL: red_coal++;
                    break;
                case TANKER: orange_tanker++;
                    break;
                case BOX: yellow_boxcar++;
                    break;
                case CABOOSE: green_caboose++;
                    break;
                case PASSENGER: blue_passenger++;
                    break;
                case FREIGHT: purple_freight++;
                    break;
                case REEFER: white_reefer++;
                    break;
                case HOPPER: black_hopper++;
                    break;
                case ANY: rainbow_any++;
            }

        }

        redCard.setText(Integer.toString(red_coal));
        orangeCard.setText(Integer.toString(orange_tanker));
        yellowCard.setText(Integer.toString(yellow_boxcar));
        greenCard.setText(Integer.toString(green_caboose));
        blueCard.setText(Integer.toString(blue_passenger));
        purpleCard.setText(Integer.toString(purple_freight));
        whiteCard.setText(Integer.toString(white_reefer));
        blackCard.setText(Integer.toString(black_hopper));
        rainbowCard.setText(Integer.toString(rainbow_any));



        presenter = new PresenterTicketToRide(this);
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

    public class DerpAdapter extends RecyclerView.Adapter<DerpAdapter.DerpHolder>   {
        private static final int INVALID_INDEX = -1;

        private List<Chat_Item> listData;
        private LayoutInflater inflater;
        private RecyclerView recyclerView;
        private int selectedIndex;

        public DerpAdapter(List<Chat_Item> listData, Context c)
        {
            this.inflater = LayoutInflater.from(c);
            this.listData = listData;
            selectedIndex = INVALID_INDEX;
            recyclerView = null;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            this.recyclerView = recyclerView;
        }

        @Override
        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
            super.onDetachedFromRecyclerView(recyclerView);
            this.recyclerView = null;
        }

        @Override
        public DerpHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.inflate(R.layout.list_chat_message, parent, false);
            return new DerpHolder(v);
        }

        @Override
        public void onBindViewHolder(DerpHolder holder, int position) {
            Chat_Item message = listData.get(position);
        }

        @Override
        public int getItemCount() {
            return listData.size();
        }

        public DerpHolder getSelectedHolder() {
            if(selectedIndex != INVALID_INDEX) {
                return (DerpHolder) recyclerView.findViewHolderForAdapterPosition(selectedIndex);
            }
            return null;
        }

        class DerpHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private TextView titleLabel;
            private TextView playersLabel;
            private TextView inGameLabel;
            private View container;


            public DerpHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);

                titleLabel = (TextView)itemView.findViewById(R.id.lbl_game_name_text);
                playersLabel = (TextView)itemView.findViewById(R.id.lbl_players_text);
                inGameLabel = (TextView)itemView.findViewById(R.id.lbl_in_game_text);
                container = itemView.findViewById(R.id.cont_item_root);
            }

            @Override
            public void onClick(View view) {

            }
        }

    }
}
