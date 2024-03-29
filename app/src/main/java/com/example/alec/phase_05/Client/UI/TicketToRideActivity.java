package com.example.alec.phase_05.Client.UI;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.media.RatingCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.example.alec.phase_05.Client.states.EndTurnState;
import com.example.alec.phase_05.Client.states.OneDrawnCardState;
import com.example.alec.phase_05.Client.states.OneDrawnOnePickedCardState;
import com.example.alec.phase_05.Client.states.OnePickedCardState;
import com.example.alec.phase_05.Client.states.StartTurnState;
import com.example.alec.phase_05.R;
import com.example.alec.phase_05.Shared.model.Chat;
import com.example.alec.phase_05.Shared.model.City;
import com.example.alec.phase_05.Shared.model.Deck;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameComponentFactory;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.IPlayer;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.Route;
import com.example.alec.phase_05.Shared.model.StateWarning;
import com.example.alec.phase_05.Shared.model.TrainCard;
import com.example.alec.phase_05.Shared.model.TrainType;

import static com.example.alec.phase_05.Shared.model.TrainType.BOX;
import static com.example.alec.phase_05.Shared.model.TrainType.CABOOSE;
import static com.example.alec.phase_05.Shared.model.TrainType.COAL;
import static com.example.alec.phase_05.Shared.model.TrainType.FREIGHT;
import static com.example.alec.phase_05.Shared.model.TrainType.HOPPER;
import static com.example.alec.phase_05.Shared.model.TrainType.PASSENGER;
import static com.example.alec.phase_05.Shared.model.TrainType.REEFER;
import static com.example.alec.phase_05.Shared.model.TrainType.TANKER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


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
    private Button dialogDestinationButton;
    private Button dialogBeginTurnButton;
    private Button dialogMapButton;
    private Button dialogSeeMapButton1;
    private Button dialogSeeMapButton2;
    private Button dialogSeeMapButton3;
    private DestinationCard observingCard;
    Button deckButton;
    Button destDeckButton;
    ImageButton card1Button;
    ImageButton card2Button;
    ImageButton card3Button;
    ImageButton card4Button;
    ImageButton card5Button;
    Button placeRoutesButton;
    private EditText mEditTextChat;
    private TextView stateIndicator;
    private TextView boxCountView;
    private TextView passengerCountView;
    private TextView tankerCountView;
    private TextView reeferCountView;
    private TextView freightCountView;
    private TextView hopperCountView;
    private TextView coalCountView;
    private TextView caboosecountView;
    private TextView locomotiveCountView;
    private Button routeInfo;
    private Button twinRouteInfo;
    AlertDialog dialogDesinationCards;
    AlertDialog dialogBeginTurn;
    AlertDialog dialogMap;
    AlertDialog dialogWildRoute;
    View mView;
    View mBeginTurnView;
    View mShowMapView;
    View mWildDialogView;
    ImageView originalDestinationMap;
    TabHost.TabSpec tab6;

    private TextView firstCard;
    private TextView secondCard;
    private TextView thirdCard;
    // Need these layouts in to show and hide destination card dialog when there aren't enough cards.
    private View firstCardLayout;
    private View secondCardLayout;
    private View thirdCardLayout;
    private TextView anyRouteRed;
    private TextView anyRouteOrange;
    private TextView anyRouteYellow;
    private TextView anyRouteGreen;
    private TextView anyRouteBlue;
    private TextView anyRoutePurple;
    private TextView anyRouteWhite;
    private TextView anyRouteBlack;
    private int destinationCoiceCount;
    private TextView destinationPrompt;
    private int destinationChoiceCount;
    private TextView longest_route_player;
    private TextView start_turn_prompt;
    TabHost mTabHost;
    private AlertDialog destinationDialog;
    final Deck deck = new Deck();
    int boxCount;
    int passengerCount;
    int tankerCount;
    int reeferCount;
    int freightCount;
    int hopperCount;
    int coalCount;
    int cabooseCount;
    int locomotiveCount;
    private List<DestinationCard> cardChoices;
    boolean isTwinRoute;

    Map<TextView, Boolean> destCardChoices;
    private Route currentlySelectedRoute;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_to_ride);
        Poller.getInstance().setModelPolling();

        deckButton = (Button) findViewById(R.id.deck);
        destDeckButton = (Button) findViewById(R.id.destdeck);
        card1Button = (ImageButton) findViewById(R.id.card1);
        card2Button = (ImageButton) findViewById(R.id.card2);
        card3Button = (ImageButton) findViewById(R.id.card3);
        card4Button = (ImageButton) findViewById(R.id.card4);
        card5Button = (ImageButton) findViewById(R.id.card5);
        mView = getLayoutInflater().inflate(R.layout.dialog_dest_card, null);
        mBeginTurnView = getLayoutInflater().inflate(R.layout.dialog_begin_turn, null);
        mShowMapView = getLayoutInflater().inflate(R.layout.dialog_map, null);
        mWildDialogView = getLayoutInflater().inflate(R.layout.dialog_any_route, null);

        mTabHost = getTabHost();
        mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("Player Info").setContent(R.id.player_info));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("Destinations").setContent(R.id.routes));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("Bank").setContent(R.id.bank));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test4").setIndicator("Map").setContent(R.id.mapview));
        mTabHost.addTab(tab6 = mTabHost.newTabSpec("tab_test5").setIndicator("Chat").setContent(R.id.chat));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test6").setIndicator("Game History").setContent(R.id.game_history));
        mTabHost.setCurrentTab(0);
        //mTabHost.newTabSpec("tab_test6").setIndicator("CHAT", getResources().getDrawable(R.drawable.chat_notice));

        setOnCreateFields(mView, mBeginTurnView, mShowMapView, mWildDialogView);
        setOnCreateOnCreateListeners();

        setCardCountsZero();
//        setTrainCards();
//        setPlayersStats();
//        setRoutes();

      //  setCard(card1Button, deck);
       // setCard(card2Button, deck);
       // setCard(card3Button, deck);
       // setCard(card4Button, deck);
       // setCard(card5Button, deck);

        /*AlertDialog.Builder mBuilder = new AlertDialog.Builder(TicketToRideActivity.this);
        destCardChoices = new HashMap<>();
        destCardChoices.put(firstCard, false);
        destCardChoices.put(secondCard, false);
        destCardChoices.put(thirdCard, false);
        mBuilder.setView(mView);
        dialogDesinationCards = mBuilder.create();*/

        AlertDialog.Builder mBeginTurnBuilder = new AlertDialog.Builder(TicketToRideActivity.this);
        mBeginTurnBuilder.setView(mBeginTurnView);
        dialogBeginTurn = mBeginTurnBuilder.create();

        AlertDialog.Builder mDialogMapBuilder = new AlertDialog.Builder(TicketToRideActivity.this);
        mDialogMapBuilder.setView(mShowMapView);
        dialogMap = mDialogMapBuilder.create();

        AlertDialog.Builder mDialogWildColorBuilder = new AlertDialog.Builder(TicketToRideActivity.this);
        mDialogWildColorBuilder.setView(mWildDialogView);
        dialogWildRoute = mDialogWildColorBuilder.create();

        dialogMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogMap.dismiss();
            }
        });

        dialogSeeMapButton1 = (Button) mView.findViewById(R.id.dialog_see_map_button1);
        dialogSeeMapButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                observingCard = cardChoices.get(0);
                showDestinationCanvas(mShowMapView);
                dialogMap.show();
            }
        });
        dialogSeeMapButton2 = (Button) mView.findViewById(R.id.dialog_see_map_button2);
        dialogSeeMapButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                observingCard = cardChoices.get(1);
                showDestinationCanvas(mShowMapView);
                dialogMap.show();
            }
        });
        dialogSeeMapButton3 = (Button) mView.findViewById(R.id.dialog_see_map_button3);
        dialogSeeMapButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                observingCard = cardChoices.get(2);
                showDestinationCanvas(mShowMapView);
                dialogMap.show();
            }
        });

        //dialog.setCanceledOnTouchOutside(false);
        //dialogBeginTurn.setCanceledOnTouchOutside(false);
        dialogMap.setCanceledOnTouchOutside(false);

//        dialogDestinationButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(TicketToRideActivity.this, "Bilbo Baggins!", Toast.LENGTH_SHORT).show();
//                presenter.chooseDestinationCards(getChosenDestinationCards(), getNotChosenDestinationCards());
//                dialog.dismiss();
//            }
//        });

        dialogBeginTurnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBeginTurn.dismiss();
            }
        });

        presenter.updateAll();

        //dialogDesinationCards.show();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dialogMap.getWindow().setLayout((int)(size.x*.7), (int)(size.y*.7));

        //test
        Map<String, City> cities = GameComponentFactory.createCities();
        observingCard = new DestinationCard(cities.get("Seattle"), cities.get("New York"), 22);
    }

    private void showDestinationCanvas(View mShowMapView){
        ImageView imageView = new ImageView(this);
        imageView =(ImageView) mShowMapView.findViewById(R.id.dialog_map_image);
        View map = mShowMapView.findViewById(R.id.dialog_map_image);

        if (imageView.getWidth() > 0 && imageView.getHeight() > 0) {
            /*if(hasDrawn){
                ((BitmapDrawable)imageView.getDrawable()).getBitmap().recycle();
            }
            else{
                hasDrawn = true;
            }*/

            Bitmap bmp = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(bmp);
            imageView.draw(c);

            City city1 = observingCard.getCity1();
            City city2 = observingCard.getCity2();
            PointF cityOnePoint = convertToImageCoordinates((float) city1.getXCord(), (float) city1.getYCord(), map);
            PointF cityTwoPoint = convertToImageCoordinates((float) city2.getXCord(), (float) city2.getYCord(), map);

            Paint p = new Paint();
            p.setColor(Color.parseColor("#009900"));

            //c.drawCircle(firstCity.x, firstCity.y, 15, p);
            //c.drawCircle(secondCity.x, secondCity.y, 15, p);
            Bitmap something = BitmapFactory.decodeResource(getResources(), R.drawable.star);
            c.drawBitmap(something, cityOnePoint.x - (something.getWidth()/2), cityOnePoint.y - (something.getHeight()/2), null);
            c.drawBitmap(something, cityTwoPoint.x - (something.getWidth()/2), cityTwoPoint.y - (something.getHeight()/2), null);

            /*if(imageView.getDrawable() != null){
                ((BitmapDrawable)imageView.getDrawable()).getBitmap().recycle();
            }*/

            /*((BitmapDrawable)imageView.getDrawable()).getBitmap()!= null ;

            if (mBitmap != null && !mBitmap.isRecycled()) {
                mBitmap.recycle();
                mBitmap = null;
            }*/

            imageView.setImageBitmap(bmp);
        }
    }

    private Map<String, Integer> getLongestRoutePlayer() {
        Map<String, Integer> longestRoutePlayer = presenter.getLongestPlayer();
        return longestRoutePlayer;
    }

    private void setOnCreateFields(View mView, View mBeginTurnView, View mMapView, View mWildDialogView){
        presenter = new PresenterTicketToRide(this);

        mChatRecView = (RecyclerView) findViewById(R.id.rec_chat_list);
        mRoutesRecView = (RecyclerView) findViewById(R.id.routes_list);
        mGameHistoryRecView = (RecyclerView) findViewById(R.id.games_history_list);
        mPlayerStatsView = (RecyclerView) findViewById(R.id.player_stats);
        longest_route_player = (TextView) findViewById(R.id.longest_route_text);
        String longest_route = presenter.longestPath();
        System.out.println("ACTIVITY LONGEST ROUTE IS " + longest_route);

        //longest_route_player.setText(longest_route);

//

        //        TextView longest_route_player = (TextView) findViewById(R.id.longest_route_text);
//        String name = player_with_longest_route.getName();
//        int longest_route_size = player_with_longest_route.getPointCount();
//        longest_route_player.setText(name + " has the longest route of " + Integer.toString(longest_route_size));
//

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

        boxCountView = (TextView) findViewById(R.id.yellow_cards);
        passengerCountView = (TextView) findViewById(R.id.blue_cards);
        tankerCountView = (TextView) findViewById(R.id.orange_cards);
        reeferCountView = (TextView) findViewById(R.id.white_cards);
        freightCountView = (TextView) findViewById(R.id.purple_cards);
        hopperCountView = (TextView) findViewById(R.id.black_cards);
        coalCountView = (TextView) findViewById(R.id.red_cards);
        caboosecountView = (TextView) findViewById(R.id.green_cards);
        locomotiveCountView = (TextView) findViewById(R.id.rainbow_cards);

        anyRouteRed = (TextView) mWildDialogView.findViewById(R.id.dialog_any_route_red);
        anyRouteOrange = (TextView) mWildDialogView.findViewById(R.id.dialog_any_route_orange);
        anyRouteYellow = (TextView) mWildDialogView.findViewById(R.id.dialog_any_route_yellow);
        anyRouteGreen = (TextView) mWildDialogView.findViewById(R.id.dialog_any_route_green);
        anyRouteBlue = (TextView) mWildDialogView.findViewById(R.id.dialog_any_route_blue);
        anyRoutePurple = (TextView) mWildDialogView.findViewById(R.id.dialog_any_route_purple);
        anyRouteWhite = (TextView) mWildDialogView.findViewById(R.id.dialog_any_route_white);
        anyRouteBlack = (TextView) mWildDialogView.findViewById(R.id.dialog_any_route_black);

//        placeRoutesButton = (Button) findViewById(R.id.placeRoute);

        firstCard = (TextView) mView.findViewById(R.id.firstCard);
        firstCardLayout = mView.findViewById(R.id.firstCardLayout);
        destinationPrompt = (TextView) mView.findViewById(R.id.choose_destination_prompt);
        start_turn_prompt = (TextView) mBeginTurnView.findViewById(R.id.dialog_begin_turn_message);

        secondCard = (TextView) mView.findViewById(R.id.secondCard);
        secondCardLayout = mView.findViewById(R.id.secondCardLayout);
        thirdCard = (TextView) mView.findViewById(R.id.thirdCard);
        thirdCardLayout = mView.findViewById(R.id.thirdCardLayout);
        dialogDestinationButton = (Button) mView.findViewById(R.id.doneButton);
        dialogBeginTurnButton = (Button) mBeginTurnView.findViewById(R.id.dialog_begin_turn_button);
        dialogMapButton = (Button) mMapView.findViewById(R.id.dialog_map_button);

        destinationChoiceCount = 2;
        destCardChoices = new HashMap<>();
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(TicketToRideActivity.this);
        mBuilder.setView(mView);
        destinationDialog = mBuilder.create();

        stateIndicator = (TextView) findViewById(R.id.state_indicator);
    }

    private void setOnCreateOnCreateListeners(){
        mCreateChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEditTextChat.getText().toString().isEmpty()) {
                    Chat chat = new Chat(ClientModel.getInstance().getCurrentPlayerName(), ClientModel.getInstance().getGameID(), mEditTextChat.getText().toString(), ClientModel.getInstance().getCurrentPlayer().getColor());
                    mEditTextChat.setText("");
                    presenter.sendChat(chat);
                }
            }
        });

        deckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameState state = ClientModel.getInstance().getGameState();

                if(state instanceof StartTurnState || state instanceof OneDrawnCardState || state instanceof OnePickedCardState){
                    presenter.drawTrainCard();
                }
            }
        });

        destDeckButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GameState state = ClientModel.getInstance().getGameState();

                if(state instanceof StartTurnState){
                    presenter.drawDestinationCards();
                }
            }
        });

        card1Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GameState state = ClientModel.getInstance().getGameState();

                if(state instanceof StartTurnState || state instanceof OnePickedCardState || state instanceof OneDrawnCardState){
                    presenter.pickTrainCard(0);
                }
            }
        });

        card2Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GameState state = ClientModel.getInstance().getGameState();

                if(state instanceof StartTurnState || state instanceof OnePickedCardState || state instanceof OneDrawnCardState){
                    presenter.pickTrainCard(1);
                }
            }
        });

        card3Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GameState state = ClientModel.getInstance().getGameState();

                if(state instanceof StartTurnState || state instanceof OnePickedCardState || state instanceof OneDrawnCardState){
                    presenter.pickTrainCard(2);
                }
            }
        });

        card4Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GameState state = ClientModel.getInstance().getGameState();

                if(state instanceof StartTurnState || state instanceof OnePickedCardState || state instanceof OneDrawnCardState){
                    presenter.pickTrainCard(3);
                }
            }
        });

        card5Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GameState state = ClientModel.getInstance().getGameState();

                if(state instanceof StartTurnState || state instanceof OnePickedCardState || state instanceof OneDrawnCardState){
                    presenter.pickTrainCard(4);
                }
            }
        });

        /*
        Button placeRoutesButton = (Button) findViewById(R.id.placeRoute);
        placeRoutesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.startDemo();
            }
        });
*/

        //*************************************************
        final ImageView imageView = (ImageView) findViewById(R.id.map);

        //checks if route is selected
        imageView.setOnTouchListener(new ImageView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                PointF imagePoint = new PointF(event.getX(), event.getY());
                checkIfRouteSelected(imagePoint);
                return false;
            }
        });

        routeInfo = (Button) findViewById(R.id.route_info);
        routeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameState state = ClientModel.getInstance().getGameState();

                if(state instanceof StartTurnState){
                    if (currentlySelectedRoute != null) {
                        isTwinRoute = false;

                        if(currentlySelectedRoute.getType().equals(TrainType.ANY)){
                            dialogWildRoute.show();
                        }
                        else{
                            ClientModel.getInstance().setRouteAnyCardType(null);
                            presenter.claimRoute(currentlySelectedRoute.getId(), null);
                        }

                        routeInfo.setVisibility(View.INVISIBLE);
                        twinRouteInfo.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        twinRouteInfo = (Button) findViewById(R.id.twinroute_info);
        twinRouteInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameState state = ClientModel.getInstance().getGameState();

                if(currentlySelectedRoute != null){
                    if(currentlySelectedRoute.getType().equals(TrainType.ANY)){
                        dialogWildRoute.show();
                    }
                    else{
                        if(state instanceof StartTurnState) {
                            isTwinRoute = true;

                            Route twinRoute = ClientModel.getInstance().getMap().getRouteByID(currentlySelectedRoute.getTwinID());
                            if (twinRoute.getOwner() == null) {
                                ClientModel.getInstance().setRouteAnyCardType(null);
                                presenter.claimRoute(twinRoute.getId(), null);
                            }
                        }
                        Route twinRoute = ClientModel.getInstance().getMap().getRouteByID(currentlySelectedRoute.getTwinID());
                        if (twinRoute.getOwner() == null) {
                            ClientModel.getInstance().setRouteAnyCardType(null);
                            presenter.claimRoute(twinRoute.getId(), null);
                        }
                    }
                }

                routeInfo.setVisibility(View.INVISIBLE);
                twinRouteInfo.setVisibility(View.INVISIBLE);
            }
        });

        dialogDestinationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TicketToRideActivity.this, "Destination card(s) added to hand", Toast.LENGTH_SHORT).show();
                presenter.chooseDestinationCards(getChosenDestinationCards(), getNotChosenDestinationCards());
                firstCard.setBackgroundColor(Color.TRANSPARENT);
                secondCard.setBackgroundColor(Color.TRANSPARENT);
                thirdCard.setBackgroundColor(Color.TRANSPARENT);
                dialogDestinationButton.setEnabled(false);
                destinationDialog.dismiss();

            }
        });

        firstCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(destCardChoices.get(firstCard) == false){
                    destCardChoices.put(firstCard, true);
                    firstCard.setBackgroundColor(Color.parseColor("#8866B2FF"));
                }
                else{
                    destCardChoices.put(firstCard, false);
                    firstCard.setBackgroundColor(Color.TRANSPARENT);
                }

                checkNumSelectedDestCards();
            }
        });

        secondCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(destCardChoices.get(secondCard) == false){
                    destCardChoices.put(secondCard, true);
                    secondCard.setBackgroundColor(Color.parseColor("#8866B2FF"));
                }
                else{
                    destCardChoices.put(secondCard, false);
                    secondCard.setBackgroundColor(Color.TRANSPARENT);
                }

                checkNumSelectedDestCards();
            }
        });

        thirdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(destCardChoices.get(thirdCard) == false){
                    destCardChoices.put(thirdCard, true);
                    thirdCard.setBackgroundColor(Color.parseColor("#8866B2FF"));
                }
                else{
                    destCardChoices.put(thirdCard, false);
                    thirdCard.setBackgroundColor(Color.TRANSPARENT);
                }

                checkNumSelectedDestCards();
            }
        });

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if(s.equals("tab_test1")){

                }

                if(s.equals("tab_test2")){

                }

                if(s.equals("tab_test3")){

                }

                if(s.equals("tab_test4")){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            android.os.Handler h = new android.os.Handler(Looper.getMainLooper());
                            h.post(new Runnable() {
                                @Override
                                public void run() {
                                    updateMap(ClientModel.getInstance().getMap());
                                }
                            });

                        }
                    }).start();

                }

                if(s.equals("tab_test5")){
                    ((TextView)mTabHost.getTabWidget().getChildAt(4).findViewById(android.R.id.title)).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }

                if(s.equals("tab_test6")){

                }
            }
        });

        anyRouteRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientModel.getInstance().setRouteAnyCardType(TrainType.COAL);

                if(isTwinRoute){
                    presenter.claimRoute(ClientModel.getInstance().getMap().getRouteByID(currentlySelectedRoute.getTwinID()).getId(), COAL);
                }
                else{
                    presenter.claimRoute(currentlySelectedRoute.getId(), COAL);
                }

                dialogWildRoute.dismiss();
            }
        });

        anyRouteOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientModel.getInstance().setRouteAnyCardType(TrainType.TANKER);

                if(isTwinRoute){
                    presenter.claimRoute(ClientModel.getInstance().getMap().getRouteByID(currentlySelectedRoute.getTwinID()).getId(), TANKER);
                }
                else{
                    presenter.claimRoute(currentlySelectedRoute.getId(), TANKER);
                }

                dialogWildRoute.dismiss();
            }
        });

        anyRouteYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientModel.getInstance().setRouteAnyCardType(TrainType.BOX);

                if(isTwinRoute){
                    presenter.claimRoute(ClientModel.getInstance().getMap().getRouteByID(currentlySelectedRoute.getTwinID()).getId(), BOX);
                }
                else{
                    presenter.claimRoute(currentlySelectedRoute.getId(), BOX);
                }

                dialogWildRoute.dismiss();
            }
        });

        anyRouteGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientModel.getInstance().setRouteAnyCardType(TrainType.CABOOSE);

                if(isTwinRoute){
                    presenter.claimRoute(ClientModel.getInstance().getMap().getRouteByID(currentlySelectedRoute.getTwinID()).getId(), CABOOSE);
                }
                else{
                    presenter.claimRoute(currentlySelectedRoute.getId(), CABOOSE);
                }

                dialogWildRoute.dismiss();
            }
        });

        anyRouteBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientModel.getInstance().setRouteAnyCardType(TrainType.PASSENGER);

                if(isTwinRoute){
                    presenter.claimRoute(ClientModel.getInstance().getMap().getRouteByID(currentlySelectedRoute.getTwinID()).getId(), PASSENGER);
                }
                else{
                    presenter.claimRoute(currentlySelectedRoute.getId(), PASSENGER);
                }

                dialogWildRoute.dismiss();
            }
        });

        anyRoutePurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientModel.getInstance().setRouteAnyCardType(TrainType.FREIGHT);

                if(isTwinRoute){
                    presenter.claimRoute(ClientModel.getInstance().getMap().getRouteByID(currentlySelectedRoute.getTwinID()).getId(), FREIGHT);
                }
                else{
                    presenter.claimRoute(currentlySelectedRoute.getId(), FREIGHT);
                }

                dialogWildRoute.dismiss();
            }
        });

        anyRouteWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientModel.getInstance().setRouteAnyCardType(TrainType.REEFER);

                if(isTwinRoute){
                    presenter.claimRoute(ClientModel.getInstance().getMap().getRouteByID(currentlySelectedRoute.getTwinID()).getId(), REEFER);
                }
                else{
                    presenter.claimRoute(currentlySelectedRoute.getId(), REEFER);
                }

                dialogWildRoute.dismiss();
            }
        });

        anyRouteBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientModel.getInstance().setRouteAnyCardType(TrainType.HOPPER);

                if(isTwinRoute){
                    presenter.claimRoute(ClientModel.getInstance().getMap().getRouteByID(currentlySelectedRoute.getTwinID()).getId(), HOPPER);
                }
                else{
                    presenter.claimRoute(currentlySelectedRoute.getId(), HOPPER);
                }

                dialogWildRoute.dismiss();
            }
        });
    }

    public void drawRouteLine(City city1, City city2, String color, int lineWidth) {
        ImageView imageView = new ImageView(this);
        imageView = (ImageView) findViewById(R.id.map);
        System.out.println("width is "+imageView.getWidth());
        System.out.println("height is "+imageView.getHeight());

        if (imageView.getWidth() > 0 && imageView.getHeight() > 0) {
            Bitmap bmp = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(bmp);
            imageView.draw(c);

            Paint p = new Paint();
            p.setStrokeWidth(lineWidth);
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

            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            //p.setAlpha(75);
            View map = findViewById(R.id.map);
            PointF firstCity = convertToImageCoordinates((float) city1.getXCord(), (float) city1.getYCord(), map);
            PointF secondCity = convertToImageCoordinates((float) city2.getXCord(), (float) city2.getYCord(), map);

            c.drawLine(secondCity.x, secondCity.y, firstCity.x, firstCity.y, p);

            imageView.setImageBitmap(bmp);
        }
    }

    public int checkIfRouteSelected(PointF selectedPoint) {
        Map<Integer, Route> routes = ClientModel.getInstance().getMap().getRoutes();
        for (Route currentRoute : routes.values()){
            City city1 = currentRoute.getCity1();
            City city2 = currentRoute.getCity2();
            View map = findViewById(R.id.map);
            PointF point1 = convertToImageCoordinates((float)city1.getXCord(), (float)city1.getYCord(), map);
            PointF point2 = convertToImageCoordinates((float)city2.getXCord(), (float)city2.getYCord(), map);

            if (pointOnLine(new Point((int)point1.x, (int)point1.y), new Point((int)point2.x, (int)point2.y), selectedPoint) == true){
                currentlySelectedRoute = currentRoute;

                if((currentlySelectedRoute.getOwner() == null))
                {
                    if (ClientModel.getInstance().getNumberPlayers() <= 3)
                    {
                        if (currentlySelectedRoute.getTwinID() != -1 ){
                            if (ClientModel.getInstance().getMap().getRouteByID(currentlySelectedRoute.getTwinID()).getOwner() == null){
                                routeInfo.setVisibility(View.VISIBLE);
                            }
                        }
                        else
                        {
                            routeInfo.setVisibility(View.VISIBLE);
                        }
                    }
                    else
                    {
                        routeInfo.setVisibility(View.VISIBLE);
                    }
                }

                if (currentlySelectedRoute.getTwinID() != -1){
                    Route twinRoute = ClientModel.getInstance().getMap().getRouteByID(currentlySelectedRoute.getTwinID());
                    if(twinRoute.getOwner() == null){
                        if ((ClientModel.getInstance().getNumberPlayers() <= 3) && currentlySelectedRoute.getOwner() == null){
                            twinRouteInfo.setVisibility(View.VISIBLE);
                        }
                        else if (ClientModel.getInstance().getNumberPlayers() > 3){
                            twinRouteInfo.setVisibility(View.VISIBLE);
                        }
                    }

                    routeInfo.setText("  Claim " + currentlySelectedRoute.getCity1().getName() + " to " +
                            currentlySelectedRoute.getCity2().getName() + ": " + currentlySelectedRoute.getLength()
                            + " " +  currentlySelectedRoute.getType() + "  ");
                    twinRouteInfo.setText("  Claim " + twinRoute.getCity1().getName()
                            + " to " + twinRoute.getCity2().getName() + ": " + twinRoute.getLength()
                            + " " +  twinRoute.getType()+ "  ");
                  //  routeInfo.setBackgroundColor(Color.parseColor("#00FFFF"));
                   // twinRouteInfo.setBackgroundColor(Color.parseColor("#00FFFF"));
                    changeTextColorBasedOnPart(routeInfo, currentRoute);
                    changeTextColorBasedOnPart(twinRouteInfo, twinRoute);
                }
                else{
                    routeInfo.setText("  Claim " + currentlySelectedRoute.getCity1().getName() + " to "
                            + currentlySelectedRoute.getCity2().getName() + ": "
                            + currentlySelectedRoute.getLength() + " " +  currentlySelectedRoute.getType() + "  ");
                   // routeInfo.setBackgroundColor(Color.parseColor("#00FFFF"));
                    twinRouteInfo.setVisibility(View.INVISIBLE);
                    changeTextColorBasedOnPart(routeInfo, currentRoute);
                }
                return currentRoute.getId();
            }

        }
        currentlySelectedRoute = null;
        routeInfo.setVisibility(View.INVISIBLE);
        twinRouteInfo.setVisibility(View.INVISIBLE);
        return -1;
    }

    private void changeTextColorBasedOnPart(Button button, Route route){
        TrainType type = route.getType();
        button.setTextColor(Color.parseColor("#606060"));

        if(type == BOX){
            button.setTextColor(Color.YELLOW);

        }
        if(type == PASSENGER){
            button.setTextColor(Color.BLUE);

        }
        if(type == TANKER){
            button.setTextColor(Color.parseColor("#FF8000"));
        }
        if(type == REEFER){
            button.setTextColor(Color.WHITE);

        }
        if(type == FREIGHT){
            button.setTextColor(Color.parseColor("#FF00FF"));

        }
        if(type == HOPPER){
            button.setTextColor(Color.BLACK);
        }
        if(type == COAL){
            button.setTextColor(Color.RED);
        }
        if(type == CABOOSE){
            button.setTextColor(Color.parseColor("#999900"));
        }
    }

    public boolean pointOnLine(Point start, Point end, PointF selectedPoint) {

        double routeDistance = lineDistance(new PointF(start.x, start.y), new PointF(end.x, end.y));
        double ptDistance = lineDistance(new PointF(start.x, start.y), selectedPoint) + lineDistance(new PointF(end.x, end.y), selectedPoint);

        double distanceFromRoute = routeDistance - ptDistance;
        if (distanceFromRoute < 0){
            distanceFromRoute = distanceFromRoute/-1;
        }
        if (distanceFromRoute <= 6){
            return true;
        }
        return false;
    }

    public double lineDistance(PointF a, PointF b){
        return Math.sqrt((a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y));
    }

    public PointF convertToImageCoordinates(float xInput, float yInput, View map) {
        final int ORIGINAL_PIC_WIDTH = 2460;
        final int ORIGINAL_PIC_HEIGHT = 1522;

        float y = (map.getHeight()*yInput)/ORIGINAL_PIC_HEIGHT;

        float xPicWidth = (ORIGINAL_PIC_WIDTH*map.getHeight())/ORIGINAL_PIC_HEIGHT;
        float extraSpace = (map.getWidth() - xPicWidth);

        float x = ((xPicWidth * xInput)/ORIGINAL_PIC_WIDTH) + (extraSpace/2);
        return new PointF(x, y);
    }

    public void setImageButton(ImageButton button, TrainType id) {
        if(id == null) {
            button.setImageResource(R.drawable.back);
            return;
        }
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
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
        presenter = null;
    }

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
//

    private void switchStateByTrainPicked(GameState state){
        if(state instanceof StartTurnState){
            return;
        }

        if(state instanceof OneDrawnCardState){

            return;
        }

        if(state instanceof OnePickedCardState){

            return;
        }

        if(state instanceof OneDrawnOnePickedCardState){

            return;
        }
    }

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
        if(mTabHost.getCurrentTab() != 5 && chats.size() != 0){
            ((TextView)mTabHost.getTabWidget().getChildAt(4).findViewById(android.R.id.title)).setCompoundDrawablesWithIntrinsicBounds(R.drawable.chat_notice, 0, 0, 0);
        }
        //tab6.setIndicator("CHAT",getResources().getDrawable(R.drawable.chat_notice));
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
        setImageButton(card1Button, cards.get(0) == null ? null : cards.get(0).getType());
        setImageButton(card2Button, cards.get(1) == null ? null : cards.get(1).getType());
        setImageButton(card3Button, cards.get(2) == null ? null : cards.get(2).getType());
        setImageButton(card4Button, cards.get(3) == null ? null : cards.get(3).getType());
        setImageButton(card5Button, cards.get(4) == null ? null : cards.get(4).getType());
    }

    @Override
    public void updateMap(GameMap map) {
        Map<Route, Route> drawnRoutes = new HashMap<>();

        for (int i = 1; i <= 100; i++) {
            Route route = map.getRouteByID(i);
            IPlayer owner = route.getOwner();
            if (owner != null) {
                if(route.getTwinID() != -1){
                    if(ClientModel.getInstance().getMap().getRouteByID(route.getTwinID()).getOwner() == null){
                        drawRouteLine(route.getCity1(), route.getCity2(), owner.getColor(), 11);
                    }
                    else{
                        if(!drawnRoutes.containsKey(route)){
                            drawnRoutes.put(route, route);
                            drawnRoutes.put(ClientModel.getInstance().getMap().getRouteByID(route.getTwinID()), ClientModel.getInstance().getMap().getRouteByID(route.getTwinID()));
                            drawRouteLine(route.getCity1(), route.getCity2(), owner.getColor(), 11);
                        }
                        else{
                            drawRouteLine(route.getCity1(), route.getCity2(), owner.getColor(), 5);
                        }
                    }
                }
                else{
                    drawRouteLine(route.getCity1(), route.getCity2(), owner.getColor(), 11);
                }
            }

        }

        // Change color of destination cards to indicate if they are met.
        mRoutesRecyclerAdapter.updateData();
    }

    @Override
    public void pickDestinationCards(List<DestinationCard> cards) {
        destinationChoiceCount = 1;

        cardChoices = cards;

        displayCardChoiceDialog(false);
    }

    @Override
    public void initPickDestinationCards(List<DestinationCard> cards) {
        destinationChoiceCount = 2;

        cardChoices = cards;

        displayCardChoiceDialog(true);
    }

    @Override
    public void onTurnStart() {
        System.out.println("hello");
        if(lastTurns()){
            start_turn_prompt.setText("LAST TURN!");
        }
        dialogBeginTurn.show();

        longest_route_player.setText(longestPathString());
    }

    private String longestPathString(){
        Map<String, Integer> map = ClientModel.getInstance().getLongestRoute();
        if(map != null){
            StringBuilder sb = new StringBuilder();
            int length = 0;
            int playerCount = 0;

            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                playerCount++;

                if(playerCount != 1){
                    sb.append(", ");
                }
                Map.Entry pair = (Map.Entry)it.next();
                sb.append(((String)pair.getKey()));
                length = (int)pair.getValue();
                //it.remove(); // avoids a ConcurrentModificationException
            }

            if(playerCount == 1){
                sb.append(" has longest route with " + Integer.toString(length));
            }
            else{
                sb.append(" have longest route with " + Integer.toString(length));
            }

            return sb.toString();
        }

        return "";
    }

    private boolean lastTurns(){
        int numPlayers = ClientModel.getInstance().getGame().getNumberPlayers();
        ArrayList<IPlayer> players = new ArrayList<>();

        for(int i = 0; i < numPlayers; i++){
            players.add(ClientModel.getInstance().getGame().getPlayer(i));
        }

        for(int i = 0; i < players.size(); i++){
            if(players.get(i).getTrainCount() < 3){
                return true;
            }
        }

        return false;
    }

    @Override
    public void onGameFinished() {
        startActivity(new Intent(TicketToRideActivity.this, TicketToRideEndGameActivity.class));
    }

    @Override
    public void updateGameState(GameState state) {
        if(state instanceof EndTurnState && ClientModel.getInstance().getCurrentPlayer().getName().equals(ClientModel.getInstance().getGame().getCurrentPlayerTurn())){

        }
        else{
            stateIndicator.setText(state.toString());
        }
    }

    @Override
    public void handleWarning(StateWarning warning) {
        Toast.makeText(TicketToRideActivity.this, warning.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateLongestPath(Map<String, Integer> longestRoute) {
//        String name = "No one";
//        String message = " has the longest route of ";
//        boolean firstIter = true;
//        String routeLength = "";
//        if (longestRoute != null) {
//            for (Map.Entry<String, Integer> entry : longestRoute.entrySet()) {
//                String key = entry.getKey();
//                int value = entry.getValue();
//                if (firstIter) {
//                    name = key;
//                    firstIter = false;
//                } else {
//                    name = name + ", " + key;
//                }
//                routeLength = Integer.toString(value);
//                System.out.println("Game map PLAYER: " + name + " " + routeLength);
//            }
//        }
//        System.out.println("Activity " + name + " " + routeLength + "!@#$");
//        System.out.println(presenter.longestPath() + "!!!!!");
        longest_route_player.setText(presenter.longestPath());
    }

    private void displayCardChoiceDialog(boolean firstTurn) {
        destCardChoices.put(firstCard, false);
        destCardChoices.put(secondCard, false);
        destCardChoices.put(thirdCard, false);
        // Set the text of the boxes to the destination cards's toString if they exist.
        // Hide boxes when there are less than 3 destination cards.
        if (cardChoices.size() >= 1) {
            firstCard.setText(cardChoices.get(0).toString());
            firstCardLayout.setVisibility(View.VISIBLE);
        } else {
            firstCard.setText("INVALID");
            firstCardLayout.setVisibility(View.GONE);
        }
        if (cardChoices.size() >= 2){
            secondCard.setText(cardChoices.get(1).toString());
            secondCardLayout.setVisibility(View.VISIBLE);
        } else {
            secondCard.setText("INVALID");
            secondCardLayout.setVisibility(View.GONE);
        }
        if (cardChoices.size() >= 3){
            thirdCard.setText(cardChoices.get(2).toString());
            thirdCardLayout.setVisibility(View.VISIBLE);
        } else {
            thirdCard.setText("INVALID");
            thirdCardLayout.setVisibility(View.GONE);
        }
        if (!firstTurn){
            destinationPrompt.setText("CHOOSE AT LEAST 1 DESTINATION CARDS");
        }

        destinationDialog.setCanceledOnTouchOutside(false);
        destinationDialog.show();
    }

    private List<DestinationCard> getChosenDestinationCards() {
        List<DestinationCard> chosen = new ArrayList<>();
        if(destCardChoices.get(firstCard) && cardChoices.size() >= 1) {
            chosen.add(cardChoices.get(0));
        }
        if(destCardChoices.get(secondCard) && cardChoices.size() >= 2) {
            chosen.add(cardChoices.get(1));
        }
        if(destCardChoices.get(thirdCard) && cardChoices.size() >= 3) {
            chosen.add(cardChoices.get(2));
        }
        return chosen;
    }

    private List<DestinationCard> getNotChosenDestinationCards() {
        List<DestinationCard> notChosen = new ArrayList<>();
        if(!destCardChoices.get(firstCard) && cardChoices.size() >= 1) {
            notChosen.add(cardChoices.get(0));
        }
        if(!destCardChoices.get(secondCard) && cardChoices.size() >= 2) {
            notChosen.add(cardChoices.get(1));
        }
        if(!destCardChoices.get(thirdCard) && cardChoices.size() >= 3) {
            notChosen.add(cardChoices.get(2));
        }
        return notChosen;
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

            holder.message.setText(message.getMessage() + " -" + message.getName());

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

            ClientModel model = ClientModel.getInstance();
            GameMap map = model.getMap();
            int textColor = Color.BLACK;
            if (map != null && model.hasCurrentGame()) {
                if (map.checkIfDestinationComplete2(destinationCard, model.getCurrentPlayerName())) {
                    textColor = Color.GREEN;
                } else {
                    textColor = Color.RED;
                }
            }

            holder.city1.setText(destinationCard.getCity1().getName());
            holder.city1.setTextSize(24);
            holder.city1.setTextColor(textColor);
            holder.city2.setText(destinationCard.getCity2().getName());
            holder.city2.setTextSize(24);
            holder.city2.setTextColor(textColor);
            holder.destination_points.setText(Integer.toString(destinationCard.getValue()));
            holder.destination_points.setTextSize(24);
            holder.destination_points.setTextColor(textColor);

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

        public void updateData() {
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
            holder.name.setTextSize(20);
            holder.points.setText(Integer.toString(stat.getPoints()));
            holder.points.setTextSize(20);
            holder.points.setTextColor(getColorFromName(stat.getColor()));
            holder.trainCount.setText(Integer.toString(stat.getTrainCount()));
            holder.trainCount.setTextColor(getColorFromName(stat.getColor()));
            holder.trainCount.setTextSize(20);
            holder.trainCards.setTextSize(20);
            holder.trainCards.setText(Integer.toString(stat.getTrainCards()));
            holder.trainCards.setTextColor(getColorFromName(stat.getColor()));
            holder.destinationCards.setText(Integer.toString(stat.getDestinationCards()));
            holder.destinationCards.setTextColor(getColorFromName(stat.getColor()));
            holder.destinationCards.setTextSize(20);
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
        boxCountView.setTextSize(18);
        passengerCountView.setText(Integer.toString(passengerCount));
        passengerCountView.setTextSize(18);
        tankerCountView.setText(Integer.toString(tankerCount));
        tankerCountView.setTextSize(18);
        reeferCountView.setText(Integer.toString(reeferCount));
        reeferCountView.setTextSize(18);
        freightCountView.setText(Integer.toString(freightCount));
        freightCountView.setTextSize(18);
        hopperCountView.setText(Integer.toString(hopperCount));
        hopperCountView.setTextSize(18);
        coalCountView.setText(Integer.toString(coalCount));
        coalCountView.setTextSize(18);
        caboosecountView.setText(Integer.toString(cabooseCount));
        caboosecountView.setTextSize(18);
        locomotiveCountView.setText(Integer.toString(locomotiveCount));
        locomotiveCountView.setTextSize(18);
    }

    private void checkNumSelectedDestCards(){
        int count = 0;

        if(destCardChoices.get(firstCard) == true){
            count++;
        }
        if(destCardChoices.get(secondCard) == true){
            count++;
        }
        if(destCardChoices.get(thirdCard) == true){
            count++;
        }

        if(count >= destinationChoiceCount){
            dialogDestinationButton.setEnabled(true);
        }
        else{
            dialogDestinationButton.setEnabled(false);
        }
    }

    @Override
    public void onBackPressed() {
        //remove player from game;
        //Poller.getInstance().setListGamePolling();
        //super.onBackPressed();
    }
}

