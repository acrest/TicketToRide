package com.example.alec.phase_05.Client.UI;

import android.app.TabActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Client.Model.Derpness;
import com.example.alec.phase_05.Client.Model.PlayerStat;
import com.example.alec.phase_05.Client.Poller;
import com.example.alec.phase_05.Client.Presenter.IPresenterTicketToRide;
import com.example.alec.phase_05.Client.Presenter.ITicketToRideListener;
import com.example.alec.phase_05.Client.Presenter.PresenterTicketToRide;
import com.example.alec.phase_05.R;
import com.example.alec.phase_05.Shared.model.Chat;
import com.example.alec.phase_05.Shared.model.City;
import com.example.alec.phase_05.Shared.model.Deck;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.IPlayer;
import com.example.alec.phase_05.Shared.model.Route;
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
    private RecyclerView mPlayerStatsView;
    private ChatAdapter mChatRecyclerAdapter;
    private RouteAdapter mRoutesRecyclerAdapter;
    private GameHistoryAdapter mGameHistoryRecyclerAdapter;
    private PlayerStatAdapter mPlayerStatAdapter;
    private Button mCreateChatButton;
    private Button doneSelCard;
    private EditText mEditTextChat;
    private TextView boxCountView;
    private TextView passengerCountView;
    private TextView tankerCountView;
    private TextView reeferCountView;
    private TextView freightCountView;
    private TextView hopperCountView;
    private TextView coalCountView;
    private TextView caboosecountView;
    private TextView locomotiveCountView;
    private TextView firstCard;
    private TextView secondCard;
    private TextView thirdCard;

    int boxCount;
    int passengerCount;
    int tankerCount;
    int reeferCount;
    int freightCount;
    int hopperCount;
    int coalCount;
    int cabooseCount;
    int locomotiveCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_to_ride);
        Poller.getInstance().setModelPolling();

        presenter = new PresenterTicketToRide(this);

        mChatRecView = (RecyclerView) findViewById(R.id.rec_chat_list);
        mRoutesRecView = (RecyclerView) findViewById(R.id.routes_list);
        mGameHistoryRecView = (RecyclerView) findViewById(R.id.games_history_list);
        mPlayerStatsView = (RecyclerView) findViewById(R.id.player_stats);

        mChatRecView.setLayoutManager(new LinearLayoutManager(this));
        mRoutesRecView.setLayoutManager(new LinearLayoutManager(this));
        mGameHistoryRecView.setLayoutManager(new LinearLayoutManager(this));
        mPlayerStatsView.setLayoutManager(new LinearLayoutManager(this));


        mChatRecyclerAdapter = new ChatAdapter(new ArrayList<Chat>(), this);
        mRoutesRecyclerAdapter = new RouteAdapter(Derpness.getInstance().generateFakeChat(), this);
        mGameHistoryRecyclerAdapter = new GameHistoryAdapter(new ArrayList<Chat>(), this);
//        List<PlayerStat> tmp=new ArrayList<>();
//        tmp.add(new PlayerStat(new Player("test1")));
//        tmp.add(new PlayerStat(new Player("test2")));
//        tmp.add(new PlayerStat(new Player("test3")));
//        tmp.add(new PlayerStat(new Player("test4")));
//        tmp.add(new PlayerStat(new Player("test5")));
//        tmp.add(new PlayerStat(new Player("test6")));
//        tmp.add(new PlayerStat(new Player("test7")));
//        mPlayerStatAdapter = new PlayerStatAdapter(tmp, this);
        mPlayerStatAdapter = new PlayerStatAdapter(presenter.getPlayerStats(), this);
        mChatRecView.setAdapter(mChatRecyclerAdapter);
        mRoutesRecView.setAdapter(mRoutesRecyclerAdapter);
        mGameHistoryRecView.setAdapter(mGameHistoryRecyclerAdapter);
        mPlayerStatsView.setAdapter(mPlayerStatAdapter);

        mCreateChatButton = (Button) findViewById(R.id.create_chat_button);

        mEditTextChat = (EditText) findViewById(R.id.EditTextChat);
        mCreateChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditTextChat.getText() != null) {
                    Chat chat = new Chat(ClientModel.getInstance().getCurrentPlayer().getName(), ClientModel.getInstance().getGameID(), mEditTextChat.getText().toString(), ClientModel.getInstance().getCurrentPlayer().getColor());
                    mEditTextChat.setText("");
                    ClientModel.getInstance().addChat(chat);
                }
            }
        });

        setCardCountsZero();
        boxCountView = (TextView) findViewById(R.id.yellow_cards);
        passengerCountView = (TextView) findViewById(R.id.blue_cards);
        tankerCountView = (TextView) findViewById(R.id.orange_cards);
        reeferCountView = (TextView) findViewById(R.id.white_cards);
        freightCountView = (TextView) findViewById(R.id.purple_cards);
        hopperCountView = (TextView) findViewById(R.id.black_cards);
        coalCountView = (TextView) findViewById(R.id.red_cards);
        caboosecountView = (TextView) findViewById(R.id.green_cards);
        locomotiveCountView = (TextView) findViewById(R.id.rainbow_cards);


        TabHost mTabHost = getTabHost();

        mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("Player Info").setContent(R.id.player_info));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("Routes").setContent(R.id.routes));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("Game History").setContent(R.id.game_history));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test4").setIndicator("Bank").setContent(R.id.bank));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test5").setIndicator("Map").setContent(R.id.map));

        mTabHost.addTab(mTabHost.newTabSpec("tab_test6").setIndicator("Chat").setContent(R.id.chat));

        mTabHost.setCurrentTab(0);

//        setTrainCards();
//        setPlayersStats();
//        setRoutes();


        final Deck deck = new Deck();


        final ImageButton deckButton = (ImageButton) findViewById(R.id.deck);
        final ImageButton card1Button = (ImageButton) findViewById(R.id.card1);
        setCard(card1Button, deck);
        final ImageButton card2Button = (ImageButton) findViewById(R.id.card2);
        setCard(card2Button, deck);
        final ImageButton card3Button = (ImageButton) findViewById(R.id.card3);
        setCard(card3Button, deck);
        final ImageButton card4Button = (ImageButton) findViewById(R.id.card4);
        setCard(card4Button, deck);
        final ImageButton card5Button = (ImageButton) findViewById(R.id.card5);
        setCard(card5Button, deck);

        final Toast toast = Toast.makeText(getApplicationContext(), "Card added to hand!",
                Toast.LENGTH_SHORT);


        deckButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TrainCard card = deck.drawCard();
                toast.show();

            }
        });
        card1Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TrainCard card = deck.drawCard();
                toast.show();
                setImageButton(card1Button, card.getType());
            }
        });

        card2Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TrainCard card = deck.drawCard();
                toast.show();
                setImageButton(card2Button, card.getType());
            }
        });

        card3Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TrainCard card = deck.drawCard();
                toast.show();
                setImageButton(card3Button, card.getType());
            }
        });

        card4Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TrainCard card = deck.drawCard();
                toast.show();
                setImageButton(card4Button, card.getType());
            }
        });

        card5Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TrainCard card = deck.drawCard();
                toast.show();
                setImageButton(card5Button, card.getType());
            }
        });
        final ImageView imageView = (ImageView) findViewById(R.id.map);


        AlertDialog.Builder mBuilder = new AlertDialog.Builder(TicketToRideActivity.this);
        final View mView = getLayoutInflater().inflate(R.layout.dialog_dest_card, null);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();



        Button placeRoutesButton = (Button) findViewById(R.id.placeRoute);
        placeRoutesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.startDemo();
            }
        });

        firstCard = (TextView) mView.findViewById(R.id.firstCard);
        secondCard = (TextView) mView.findViewById(R.id.secondCard);
        thirdCard = (TextView) mView.findViewById(R.id.thirdCard);
        doneSelCard = (Button) mView.findViewById(R.id.doneButton);
        doneSelCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //send cards to DB
                dialog.dismiss();
            }
        });

        //*************************************************

/*
        imageView.setOnTouchListener(new ImageView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                convertToImageCoord(imageView.getWidth(), imageView.getHeight(),
                        getResources().getDrawable(R.drawable.ticketmap).getMinimumWidth(),
                        getResources().getDrawable(R.drawable.ticketmap).getMinimumHeight(),
                        event.getX(), event.getY());
                return true;
            }
        });

        final Button drawRoute  = (Button)findViewById(R.id.placeRoute);
        drawRoute.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                presenter.startDemo();

//                Bitmap bmp = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.ARGB_8888);
//                Canvas c = new Canvas(bmp);
//                imageView.draw(c);
//
//                Paint p = new Paint();
//                p.setStrokeWidth(8);
//                p.setColor(Color.WHITE);
//                p.setAlpha(75);
//                c.drawLine(88, 0, 188, 100, p);
//                imageView.setImageBitmap(bmp);
            }
        });
*/



        presenter.updateAll();
        dialog.show();
    }

    public void drawRouteLine(City city1, City city2, String color) {
        ImageView imageView = new ImageView(this);
        imageView = (ImageView) findViewById(R.id.map);
        if (imageView.getWidth() > 0 && imageView.getHeight() > 0) {
            Bitmap bmp = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(bmp);
            imageView.draw(c);

            Paint p = new Paint();
            p.setStrokeWidth(8);
            switch (color.toLowerCase()) {
                case "black":
                    p.setColor(Color.BLACK);
                    break;
                case "yellow":
                    p.setColor(Color.YELLOW);
                    break;
                case "red":
                    p.setColor(Color.RED);
                    break;
                case "green":
                    p.setColor(Color.GREEN);
                    break;
                case "blue":
                    p.setColor(Color.BLUE);
                    break;
            }
            //p.setAlpha(75);
            PointF firstCity = convertToScreenCoordinates(imageView.getWidth(), imageView.getHeight(), (float) city1.getXCord(), (float) city1.getYCord());
            PointF secondCity = convertToScreenCoordinates(imageView.getWidth(), imageView.getHeight(), (float) city2.getXCord(), (float) city2.getYCord());


            c.drawLine(secondCity.x, secondCity.y, firstCity.x, firstCity.y, p);
            imageView.setImageBitmap(bmp);
        }
    }

    //**************************************


    public void checkIfRouteSelected() {
        //do some things
    }

    /*
        public Float[] convertToImageCoord(float deviceWidth, float deviceHeight, float imageWidth,
                                           float imageHeight, float xInput, float yInput){
            Float[] coordinates = new Float[2];
            float x;
            float y;
            // System.out.println(" The x and y " + xInput + " " + yInput);
            float conversionRate = deviceHeight/imageHeight;
            // System.out.println("The device height and image height " + deviceHeight + " " + imageHeight);
            //System.out.println("Conversion rate: " + conversionRate + "New Image Width: " + imageWidth * conversionRate);
            float extraSpace = (deviceWidth - (imageWidth*conversionRate))/2;
            //System.out.println("Extra space: " + extraSpace);
            x = ((xInput- extraSpace)/conversionRate);
            y = (yInput)/conversionRate;
            //System.out.println("The converted points " + x + " " + y);
            coordinates[0] = x;
            coordinates[1] = y;
            return coordinates;
        }
    */
    public PointF convertToScreenCoordinates(float deviceWidth, float deviceHeight, float xInput, float yInput) {

        float imageWidth = getResources().getDrawable(R.drawable.tickettoridemap).getMinimumWidth();
        float imageHeight = getResources().getDrawable(R.drawable.tickettoridemap).getMinimumHeight();

        PointF newPoint = new PointF();
        float x;
        float y;
        float conversionRate = deviceHeight / imageHeight;
        float extraSpace = (deviceWidth - (imageWidth * conversionRate)) / 2;
        x = (xInput * conversionRate) + extraSpace;
        y = yInput * conversionRate;
        newPoint.x = x;
        newPoint.y = y;
        return newPoint;

    }


    // **************************************


    public void setCard(ImageButton button, Deck deck) {
        TrainCard card = deck.drawCard();
        setImageButton(button, card.getType());
    }


    public void setImageButton(ImageButton button, TrainType id) {
        switch (id) {
            case HOPPER:
                button.setImageResource(R.drawable.black);
                break;
            case PASSENGER:
                button.setImageResource(R.drawable.blue);
                break;
            case CABOOSE:
                button.setImageResource(R.drawable.green);
                break;
            case TANKER:
                button.setImageResource(R.drawable.orange);
                break;
            case FREIGHT:
                button.setImageResource(R.drawable.purple);
                break;
            case COAL:
                button.setImageResource(R.drawable.red);
                break;
            case REEFER:
                button.setImageResource(R.drawable.white);
                break;
            case BOX:
                button.setImageResource(R.drawable.yellow);
                break;
            case LOCOMOTIVE:
                button.setImageResource(R.drawable.loco);
                break;
        }
    }

    private void setRoutes() {
//        Player curr_player = ClientModel.getInstance().getCurrentPlayer();
//        ArrayList<DestinationCard> routes = curr_player.getDestinationCards();
//
//        TextView route_List = (TextView) findViewById(R.id.routes_list);
//
//        StringBuilder builder = new StringBuilder();
//        for (DestinationCard card : routes) {
//            String info = card.getCity1() + " to " + card.getCity2() + " for " + card.getValue() + " points";
//            builder.append(info + "\n");
//
//        }
//
//        route_List.setText(builder.toString());
    }

//    private void setPlayersStats() {
//
//        ArrayList<Player> playerList = presenter.getPlayers();
//        ArrayList<String> playerInfo = new ArrayList<>();
//        Player player_with_longest_route = new Player(null, null);
//        Player currPlayer = presenter.getCurrPlayer();
//
//        System.out.println("WE ARE TESTING HERE");
//        System.out.println(playerList.size());
//            for (int i = 0; i < playerList.size(); i++) {
//                Player temp_player = playerList.get(i);
//               if (currPlayer.getName().equals(temp_player.getName())) {
//
//                   String temp = temp_player.getName() + "                  " + temp_player.getPointCount() + "                    "
//                           + temp_player.getTrainCount() + "                      "  + "                    "
//                            + "  " + temp_player.getColor();
//                   playerInfo.add(temp);
//
//
//               } else {
//
//                   String temp = temp_player.getName() + "                  " + temp_player.getPointCount() + "                    "
//                           + temp_player.getTrainCount() + "                   " + temp_player.getTrainCards().size() + "                 "
//                           + temp_player.getDestinationCards().size() + "  " + temp_player.getColor();
//                   playerInfo.add(temp);
//
//               }
//
//                if (player_with_longest_route.getPointCount() < temp_player.getPointCount()) {
//                    player_with_longest_route = temp_player;
//                }
//
//            }
//
//
//        populatePlayerListView(playerInfo);
//
//        TextView longest_route_player = (TextView) findViewById(R.id.longest_route_text);
//        String name = player_with_longest_route.getName();
//        int longest_route_size = player_with_longest_route.getPointCount();
//        longest_route_player.setText(name + " has the longest route of " + Integer.toString(longest_route_size));
//
//
//    }


//    private void populatePlayerListView(ArrayList<String> playerInfo) {
//        TextView playersStats = (TextView) findViewById(R.id.player_stats);
//
//        StringBuilder builder = new StringBuilder();
//
//        for (String a_player : playerInfo) {
//            builder.append(a_player + "\n");
//        }
//
//        playersStats.setText(builder.toString());
//
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//           // View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//           // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
//            return rootView;
//        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

//    private void setTrainCards() {
//        TextView redCard = (TextView) findViewById(R.id.red_cards);
//        TextView orangeCard = (TextView) findViewById(R.id.orange_cards);
//        TextView yellowCard = (TextView) findViewById(R.id.yellow_cards);
//        TextView greenCard = (TextView) findViewById(R.id.green_cards);
//        TextView blueCard = (TextView) findViewById(R.id.blue_cards);
//        TextView purpleCard = (TextView) findViewById(R.id.purple_cards);
//        TextView whiteCard = (TextView) findViewById(R.id.white_cards);
//        TextView blackCard = (TextView) findViewById(R.id.black_cards);
//        TextView rainbowCard = (TextView) findViewById(R.id.rainbow_cards);
//
//        TextView playerInfoList = (TextView) findViewById(R.id.player_stats);
//
//        ArrayList<TrainCard> cardList = presenter.getTrainCards();
//        //ArrayList<TrainCard> cardList = ClientModel.getInstance().getCurrentPlayer().getTrainCards();
//        int red_coal = 0;
//        int orange_tanker = 0;
//        int yellow_boxcar = 0;
//        int green_caboose = 0;
//        int blue_passenger = 0;
//        int purple_freight = 0;
//        int white_reefer = 0;
//        int black_hopper = 0;
//        int rainbow_any = 0;
//        for (int i = 0; i < cardList.size(); i++) {
//            TrainType trainType = cardList.get(i).getType();
//            switch (trainType) {
//                case COAL: red_coal++;
//                    break;
//                case TANKER: orange_tanker++;
//                    break;
//                case BOX: yellow_boxcar++;
//                    break;
//                case CABOOSE: green_caboose++;
//                    break;
//                case PASSENGER: blue_passenger++;
//                    break;
//                case FREIGHT: purple_freight++;
//                    break;
//                case REEFER: white_reefer++;
//                    break;
//                case HOPPER: black_hopper++;
//                    break;
//                case LOCOMOTIVE: rainbow_any++;
//            }
//
//        }
//
//        redCard.setText(Integer.toString(red_coal));
//        orangeCard.setText(Integer.toString(orange_tanker));
//        yellowCard.setText(Integer.toString(yellow_boxcar));
//        greenCard.setText(Integer.toString(green_caboose));
//        blueCard.setText(Integer.toString(blue_passenger));
//        purpleCard.setText(Integer.toString(purple_freight));
//        whiteCard.setText(Integer.toString(white_reefer));
//        blackCard.setText(Integer.toString(black_hopper));
//        rainbowCard.setText(Integer.toString(rainbow_any));
//
//    }


    /*
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_ticket_to_ride);
        }
    */
//    private void addDrawerItems() {
//        String[] osArray = { "I", "am", "a", "test", "wizard" };
//        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
//        mDrawerList.setAdapter(mAdapter);
//        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(TicketToRideActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    @Override
    public void updateTrainCards(List<TrainCard> cards) {
        setCardCountsZero();
        for (int i = 0; i < cards.size(); i++) {
            switch (cards.get(i).getType()) {
                case BOX:
                    boxCount++;
                    break;
                case PASSENGER:
                    passengerCount++;
                    break;
                case TANKER:
                    tankerCount++;
                    break;
                case REEFER:
                    reeferCount++;
                    break;
                case FREIGHT:
                    freightCount++;
                    break;
                case HOPPER:
                    hopperCount++;
                    break;
                case COAL:
                    coalCount++;
                    break;
                case CABOOSE:
                    cabooseCount++;
                    break;
                case LOCOMOTIVE:
                    locomotiveCount++;
                    break;
            }
        }
        updateTrainCardTextViews();
    }

    @Override
    public void updateDestinationCards(List<DestinationCard> cards) {
        mRoutesRecyclerAdapter.updateListData(cards);
    }

    @Override
    public void updatePlayerTrainCards(String playerName, int count) {
        mPlayerStatAdapter.setPlayerTrainCards(playerName, count);
    }

    @Override
    public void updatePlayerDestinationCards(String playerName, int count) {
        mPlayerStatAdapter.setPlayerDestinationCards(playerName, count);
    }

    @Override
    public void updateChats(List<Chat> chats) {
        mChatRecyclerAdapter.updateListData(chats);
    }

    @Override
    public void updatePlayerPoints(String playerName, int points) {
        mPlayerStatAdapter.setPlayerPoints(playerName, points);
    }

    @Override
    public void updatePlayerTrainCount(String playerName, int count) {
        mPlayerStatAdapter.setPlayerTrainCount(playerName, count);
    }

    @Override
    public void updateFaceupTrainCards(List<TrainCard> cards) {

    }

    @Override
    public void updateMap(GameMap map) {
        for (int i = 1; i <= 100; i++) {
            Route route = map.getRouteByID(i);
            IPlayer owner = route.getOwner();
            if (owner != null) {
                drawRouteLine(route.getCity1(), route.getCity2(), owner.getColor());
            }
        }
    }

    public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {
        private static final int INVALID_INDEX = -1;

        private List<Chat> listData;
        private LayoutInflater inflater;
        private RecyclerView recyclerView;
        private int selectedIndex;

        public ChatAdapter(List<Chat> listData, Context c) {
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
        public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.inflate(R.layout.list_chat_view, parent, false);
            return new ChatHolder(v);
        }

        @Override
        public void onBindViewHolder(ChatHolder holder, int position) {
            Chat message = listData.get(position);

            holder.message.setText(message.getMessage());

            switch (message.getColor()) {
                case ("blue"):
                    holder.message.setTextColor(Color.BLUE);
                    break;
                case ("red"):
                    holder.message.setTextColor(Color.RED);
                    break;
                case ("yellow"):
                    holder.message.setTextColor(Color.YELLOW);
                    break;
                case ("black"):
                    holder.message.setTextColor(Color.BLACK);
                    break;
                case ("green"):
                    holder.message.setTextColor(Color.GREEN);
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return listData.size();
        }

        public void updateListData(List<Chat> newListData) {
            listData = newListData;
            recyclerView.getRecycledViewPool().clear();
            notifyDataSetChanged();
        }

        public ChatHolder getSelectedHolder() {
            if (selectedIndex != INVALID_INDEX) {
                return (ChatHolder) recyclerView.findViewHolderForAdapterPosition(selectedIndex);
            }
            return null;
        }

        class ChatHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView message;
            private View container;


            public ChatHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);

                message = (TextView) itemView.findViewById(R.id.lbl_simple_view);
                container = itemView.findViewById(R.id.cont_item_root);
            }

            @Override
            public void onClick(View view) {

            }
        }

    }

    public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteHolder> {
        private static final int INVALID_INDEX = -1;

        private List<DestinationCard> listData;
        private LayoutInflater inflater;
        private RecyclerView recyclerView;

        public RouteAdapter(List<DestinationCard> listData, Context c) {
            this.inflater = LayoutInflater.from(c);
            this.listData = listData;
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
        public RouteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.inflate(R.layout.list_destination_view, parent, false);
            return new RouteHolder(v);
        }

        @Override
        public void onBindViewHolder(RouteHolder holder, int position) {
            DestinationCard destinationCard = listData.get(position);

            holder.city1.setText(destinationCard.getCity1().getName());
            holder.city2.setText(destinationCard.getCity2().getName());
            holder.destination_points.setText(Integer.toString(destinationCard.getValue()));
        }

        @Override
        public int getItemCount() {
            return listData.size();
        }

        public void updateListData(List<DestinationCard> newListData) {
            listData = newListData;
            recyclerView.getRecycledViewPool().clear();
            notifyDataSetChanged();
        }

        class RouteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView city1;
            private TextView city2;
            private TextView destination_points;
            private View container;


            public RouteHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);

                city1 = (TextView) itemView.findViewById(R.id.destination_city1);
                city2 = (TextView) itemView.findViewById(R.id.destination_city2);
                destination_points = (TextView) itemView.findViewById(R.id.destination_points);
                container = itemView.findViewById(R.id.cont_item_root);
            }

            @Override
            public void onClick(View view) {

            }
        }

    }

    public class GameHistoryAdapter extends RecyclerView.Adapter<GameHistoryAdapter.GameHistoryHolder> {
        private static final int INVALID_INDEX = -1;

        private List<Chat> listData;
        private LayoutInflater inflater;
        private RecyclerView recyclerView;
        private int selectedIndex;

        public GameHistoryAdapter(List<Chat> listData, Context c) {
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
        public GameHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.inflate(R.layout.list_chat_view, parent, false);
            return new GameHistoryHolder(v);
        }

        @Override
        public void onBindViewHolder(GameHistoryHolder holder, int position) {
            Chat message = listData.get(position);

            holder.message.setText(message.getMessage());

            switch (message.getColor()) {
                case ("blue"):
                    holder.message.setTextColor(Color.BLUE);
                    break;
                case ("red"):
                    holder.message.setTextColor(Color.RED);
                    break;
                case ("yellow"):
                    holder.message.setTextColor(Color.YELLOW);
                    break;
                case ("black"):
                    holder.message.setTextColor(Color.BLACK);
                    break;
                case ("green"):
                    holder.message.setTextColor(Color.GREEN);
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return listData.size();
        }

        public void updateListData(List<Chat> newListData) {
            listData = newListData;
            recyclerView.getRecycledViewPool().clear();
            notifyDataSetChanged();
        }

        public GameHistoryHolder getSelectedHolder() {
            if (selectedIndex != INVALID_INDEX) {
                return (GameHistoryHolder) recyclerView.findViewHolderForAdapterPosition(selectedIndex);
            }
            return null;
        }

        class GameHistoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView message;
            private View container;


            public GameHistoryHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);

                message = (TextView) itemView.findViewById(R.id.lbl_simple_view);
                container = itemView.findViewById(R.id.cont_item_root);
            }

            @Override
            public void onClick(View view) {

            }
        }

    }

    private class PlayerStatAdapter extends RecyclerView.Adapter<PlayerStatAdapter.StatHolder> {
        private List<PlayerStat> listData;
        private LayoutInflater inflater;
        private RecyclerView recyclerView;

        public PlayerStatAdapter(List<PlayerStat> listData, Context c) {
            this.inflater = LayoutInflater.from(c);
            this.listData = listData;
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
        public StatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.inflate(R.layout.list_player_stat_view, parent, false);
            return new StatHolder(v);
        }

        @Override
        public void onBindViewHolder(StatHolder holder, int position) {
            PlayerStat stat = listData.get(position);

            holder.name.setText(stat.getPlayerName());
            holder.name.setTextColor(getColorFromName(stat.getColor()));
            holder.points.setText(Integer.toString(stat.getPoints()));
            holder.points.setTextColor(getColorFromName(stat.getColor()));
            holder.trainCount.setText(Integer.toString(stat.getTrainCount()));
            holder.trainCount.setTextColor(getColorFromName(stat.getColor()));
            holder.trainCards.setText(Integer.toString(stat.getTrainCards()));
            holder.trainCards.setTextColor(getColorFromName(stat.getColor()));
            holder.destinationCards.setText(Integer.toString(stat.getDestinationCards()));
            holder.destinationCards.setTextColor(getColorFromName(stat.getColor()));
        }

        @Override
        public int getItemCount() {
            return listData.size();
        }

        public void updateListData(List<PlayerStat> newListData) {
            listData = newListData;
            recyclerView.getRecycledViewPool().clear();
            notifyDataSetChanged();
        }

        public void addStat(PlayerStat stat) {
            listData.add(stat);
            notifyItemInserted(listData.size() - 1);
        }

        private int findStatByName(String playerName) {
            for (int i = 0; i < listData.size(); ++i) {
                if (listData.get(i).getPlayerName().equals(playerName)) {
                    return i;
                }
            }
            return -1;
        }

        public void setPlayerColor(String playerName, String color) {
            int statIndex = findStatByName(playerName);
            if (statIndex == -1) return;
            listData.get(statIndex).setColor(color);
        }

        public void setPlayerPoints(String playerName, int points) {
            int statIndex = findStatByName(playerName);
            if (statIndex == -1) return;
            listData.get(statIndex).setPoints(points);
            notifyItemChanged(statIndex);
        }

        public void setPlayerTrainCount(String playerName, int trainCount) {
            int statIndex = findStatByName(playerName);
            if (statIndex == -1) return;
            listData.get(statIndex).setTrainCount(trainCount);
            notifyItemChanged(statIndex);
        }

        public void setPlayerTrainCards(String playerName, int trainCards) {
            int statIndex = findStatByName(playerName);
            if (statIndex == -1) return;
            listData.get(statIndex).setTrainCards(trainCards);
            notifyItemChanged(statIndex);
        }

        public void setPlayerDestinationCards(String playerName, int destinationCards) {
            int statIndex = findStatByName(playerName);
            if (statIndex == -1) return;
            listData.get(statIndex).setDestinationCards(destinationCards);
            notifyItemChanged(statIndex);
        }

        public class StatHolder extends RecyclerView.ViewHolder {
            private TextView name;
            private TextView points;
            private TextView trainCount;
            private TextView trainCards;
            private TextView destinationCards;


            public StatHolder(View itemView) {
                super(itemView);

                name = (TextView) itemView.findViewById(R.id.player_stat_name);
                points = (TextView) itemView.findViewById(R.id.player_stat_points);
                trainCount = (TextView) itemView.findViewById(R.id.player_stat_train_count);
                trainCards = (TextView) itemView.findViewById(R.id.player_stat_train_cards);
                destinationCards = (TextView) itemView.findViewById(R.id.player_stat_destination_cards);
            }
        }
    }

    private void setCardCountsZero() {
        boxCount = 0;
        passengerCount = 0;
        tankerCount = 0;
        reeferCount = 0;
        freightCount = 0;
        hopperCount = 0;
        coalCount = 0;
        cabooseCount = 0;
        locomotiveCount = 0;
    }

    private int getColorFromName(String colorName) {
        switch (colorName.toLowerCase()) {
            case "black":
                return Color.BLACK;
            case "yellow":
                return Color.YELLOW;
            case "red":
                return Color.RED;
            case "green":
                return Color.GREEN;
            case "blue":
                return Color.BLUE;
        }
        return -1;
    }

    private void updateTrainCardTextViews(){
        boxCountView.setText(Integer.toString(boxCount));
        passengerCountView.setText(Integer.toString(passengerCount));
        tankerCountView.setText(Integer.toString(tankerCount));
        reeferCountView.setText(Integer.toString(reeferCount));
        freightCountView.setText(Integer.toString(freightCount));
        hopperCountView.setText(Integer.toString(hopperCount));
        coalCountView.setText(Integer.toString(coalCount));
        caboosecountView.setText(Integer.toString(cabooseCount));
        locomotiveCountView.setText(Integer.toString(locomotiveCount));
    }
}