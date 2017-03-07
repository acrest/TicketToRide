package com.example.alec.phase_05.Client.UI;

import android.app.TabActivity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Shared.model.Chat;
import com.example.alec.phase_05.Shared.model.Chat_Item;
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
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.Player;
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
    private RecyclerView mChatRecView;
    private RecyclerView mRoutesRecView;
    private RecyclerView mGameHistoryRecView;
    private DerpAdapter mChatRecyclerAdapter;
    private DerpAdapter mRoutesRecyclerAdapter;
    private DerpAdapter mGameHistoryRecyclerAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_to_ride);

        presenter = new PresenterTicketToRide(this);

        mChatRecView = (RecyclerView) findViewById(R.id.rec_chat_list);
        mRoutesRecView = (RecyclerView) findViewById(R.id.routes_list);
        mGameHistoryRecView = (RecyclerView) findViewById(R.id.games_history_list);
        mChatRecView.setLayoutManager(new LinearLayoutManager(this));
        mRoutesRecView.setLayoutManager(new LinearLayoutManager(this));
        mGameHistoryRecView .setLayoutManager(new LinearLayoutManager(this));

        mChatRecyclerAdapter = new DerpAdapter(Derpness.getInstance().generateFakeChat(), this);
        mRoutesRecyclerAdapter = new DerpAdapter(Derpness.getInstance().generateFakeChat(), this);
        mGameHistoryRecyclerAdapter = new DerpAdapter(Derpness.getInstance().generateFakeChat(), this);
        mChatRecView.setAdapter(mChatRecyclerAdapter);
        mRoutesRecView.setAdapter(mRoutesRecyclerAdapter);
        mGameHistoryRecView.setAdapter(mGameHistoryRecyclerAdapter);

        TabHost mTabHost = getTabHost();

        mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("Player Info").setContent(R.id.player_info));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("Routes").setContent(R.id.routes));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("Game History").setContent(R.id.game_history));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test4").setIndicator("Bank").setContent(R.id.bank));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test5").setIndicator("Map").setContent(R.id.map));

        mTabHost.addTab(mTabHost.newTabSpec("tab_test6").setIndicator("Chat").setContent(R.id.chat));

        mTabHost.setCurrentTab(0);

        setTrainCards();
        setPlayersStats();
        setRoutes();




        presenter = new PresenterTicketToRide(this);
        presenter = new PresenterTicketToRide(this);

    }

    private void setRoutes() {
        Player curr_player = ClientModel.getInstance().getCurrentPlayer();
        ArrayList<DestinationCard> routes = curr_player.getDestinationCards();

        TextView route_List = (TextView) findViewById(R.id.routes_list);

        StringBuilder builder = new StringBuilder();
        for (DestinationCard card : routes) {
            String info = card.getCity1() + " to " + card.getCity2() + " for " + card.getValue() + " points";
            builder.append(info + "\n");

        }

        route_List.setText(builder.toString());
    }

    private void setPlayersStats() {
        ClientModel model = ClientModel.getInstance();
        int num_players = model.getNumberPlayers();
        ArrayList<TrainCard> cardList = model.getCurrentPlayer().getTrainCards();
        ArrayList<Player> playerList = new ArrayList<Player>();
        ArrayList<String> playerInfo = new ArrayList<String>();
        Player player_with_longest_route = new Player(null, null);


        for (int i = 0; i < num_players; i++) {
            Player temp_player = model.getPlayer(i);
            if (player_with_longest_route.getPointCount() < temp_player.getPointCount()) {
                player_with_longest_route = temp_player;
            }
            playerList.add(temp_player);
            String temp = temp_player.getName() +  "                " + temp_player.getPointCount() + "                      "
                    + temp_player.getTrainCount() + "                    " + cardList.size() + "                  " + temp_player.getDestinationCards().size();
            playerInfo.add(temp);

        }

        if (playerInfo != null) {

            String noString = "You have no routes!";
            playerInfo.add(noString);
        }
        populatePlayerListView(playerInfo);

        TextView longest_route_player = (TextView) findViewById(R.id.longest_route_text);
        String name = player_with_longest_route.getName();
        int longest_route_size = player_with_longest_route.getPointCount();
        longest_route_player.setText(name + " has the longest route of " + Integer.toString(longest_route_size));


    }


    private void populatePlayerListView(ArrayList<String> playerInfo) {
        TextView playersStats = (TextView) findViewById(R.id.player_stats);

        StringBuilder builder = new StringBuilder();
        for (String a_player : playerInfo) {
            builder.append(a_player + "\n");
        }

        playersStats.setText(builder.toString());

    }

    private void setTrainCards() {
        TextView redCard = (TextView) findViewById(R.id.red_cards);
        TextView orangeCard = (TextView) findViewById(R.id.orange_cards);
        TextView yellowCard = (TextView) findViewById(R.id.yellow_cards);
        TextView greenCard = (TextView) findViewById(R.id.green_cards);
        TextView blueCard = (TextView) findViewById(R.id.blue_cards);
        TextView purpleCard = (TextView) findViewById(R.id.purple_cards);
        TextView whiteCard = (TextView) findViewById(R.id.white_cards);
        TextView blackCard = (TextView) findViewById(R.id.black_cards);
        TextView rainbowCard = (TextView) findViewById(R.id.rainbow_cards);

        ListView playerInfoList = (ListView) findViewById(R.id.player_stats);


        ArrayList<TrainCard> cardList = ClientModel.getInstance().getCurrentPlayer().getTrainCards();
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
    public void updateRoutes() {

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
            View v = inflater.inflate(R.layout.list_simple_view, parent, false);
            return new DerpHolder(v);
        }

        @Override
        public void onBindViewHolder(DerpHolder holder, int position) {
            Chat_Item message = listData.get(position);

            holder.message.setText(message.getMessage());

            switch (message.getColor()){
                case("blue"):
                    holder.message.setTextColor(Color.BLUE);
                    break;
                case("red"):
                    holder.message.setTextColor(Color.RED);
                    break;
                case("yellow"):
                    holder.message.setTextColor(Color.YELLOW);
                    break;
                case("black"):
                    holder.message.setTextColor(Color.BLACK);
                    break;
                case("green"):
                    holder.message.setTextColor(Color.GREEN);
                    break;
            }
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
            private TextView message;
            private View container;


            public DerpHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);

                message = (TextView)itemView.findViewById(R.id.lbl_simple_view);
                container = itemView.findViewById(R.id.cont_item_root);
            }

            @Override
            public void onClick(View view) {

            }
        }

    }
}
