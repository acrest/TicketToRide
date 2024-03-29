package com.example.alec.phase_05.Client.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.alec.phase_05.Client.Presenter.IPresenterTicketToRideEndGame;
import com.example.alec.phase_05.Client.Presenter.ITicketToRideEndGameListener;
import com.example.alec.phase_05.Client.Presenter.PresenterTicketToRideEndGame;
import com.example.alec.phase_05.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class TicketToRideEndGameActivity extends Activity implements ITicketToRideEndGameListener {
    private IPresenterTicketToRideEndGame presenter;
    private ResultsAdapter mResultsAdapter;
    private TextView mWinner;
    private TextView mLongestRoute;
    private Button mReturnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ticket_to_ride_end_game);

        presenter = new PresenterTicketToRideEndGame(this);

        RecyclerView mResults = (RecyclerView) findViewById(R.id.results_list);
        mWinner = (TextView) findViewById(R.id.results_winner_text);
        mLongestRoute = (TextView) findViewById(R.id.results_longest_route);

        mWinner.setText(getWinnerText());
        mLongestRoute.setText(getLongestRouteText());

        List<ResultEntry> entries = new ArrayList<>();
        Iterator<String> players = presenter.getPlayerNames();
        while(players.hasNext()) {
            entries.add(createEntry(players.next()));
        }
        mResultsAdapter = new ResultsAdapter(entries, this);

        mResults.setLayoutManager(new LinearLayoutManager(this));
        mResults.setAdapter(mResultsAdapter);

        mReturnButton = (Button) findViewById(R.id.results_return_button);
        mReturnButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                presenter.onReturnButtonPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
        presenter = null;
    }

    private String getWinnerText() {
        List<String> winners = presenter.getWinners();
        if(winners.size() == 0) {
            return "There are no winners";
        } else if(winners.size() == 1) {
            return winners.get(0) + " has won";
        } else if(winners.size() == 2) {
            return winners.get(0) + " and " + winners.get(1) + " have won";
        } else {
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < winners.size() - 1; i++) {
                builder.append(winners.get(i) + ", ");
            }
            builder.append("and " + winners.get(winners.size() - 1) + " have won");
            return builder.toString();
        }
    }

    private String getLongestRouteText() {
        List<String> holders = presenter.getLongestRouteHolders();
        if(holders.size() == 0) {
            return "Nobody";
        } else if(holders.size() == 1) {
            return holders.get(0);
        } else if(holders.size() == 2) {
            return holders.get(0) + " and " + holders.get(1);
        } else {
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < holders.size() - 1; i++) {
                builder.append(holders.get(i) + ", ");
            }
            builder.append(" and " + holders.get(holders.size() - 1));
            return builder.toString();
        }
    }

    @Override
    public void updatePlayer(String playerName) {
        mResultsAdapter.updateEntry(playerName);
    }

    @Override
    public void returnToGameList() {
        Intent intent = new Intent(TicketToRideEndGameActivity.this, GameStationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultHolder> {
        private List<ResultEntry> data;
        private LayoutInflater inflater;

        private ResultsAdapter(List<ResultEntry> data, Context context) {
            this.data = data;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public ResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ResultHolder(inflater.inflate(R.layout.results_entry, parent, false));
        }

        @Override
        public void onBindViewHolder(ResultHolder holder, int position) {
            ResultEntry entry = data.get(position);
            holder.name.setText(entry.getName());
            holder.name.setTextColor(getColorFromName(entry.getColor()));
            holder.points.setText(String.format(Locale.getDefault(), "%d", entry.getPoints()));
            holder.points.setTextColor(getColorFromName(entry.getColor()));
            holder.additions.setText(String.format(Locale.getDefault(), "%d", entry.getAdditions()));
            holder.additions.setTextColor(getColorFromName(entry.getColor()));
            holder.total.setText(String.format(Locale.getDefault(), "%d", entry.getTotal()));
            holder.total.setTextColor(getColorFromName(entry.getColor()));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        private int getPlayerIndex(String playerName) {
            for(int i = 0; i < data.size(); i++) {
                if(data.get(i).getName().equals(playerName)) {
                    return i;
                }
            }
            return -1;
        }

        private void addEntry(ResultEntry entry) {
            data.add(entry);
            notifyItemInserted(data.size() - 1);
        }

        private void updateEntry(String playerName) {
            int playerIndex = getPlayerIndex(playerName);
            if(playerIndex == -1) {
                throw new IllegalStateException("Attempt to update a nonexistent player in end game gui");
            }
            data.set(playerIndex, createEntry(playerName));
            notifyItemChanged(playerIndex);
        }

        class ResultHolder extends RecyclerView.ViewHolder {
            private TextView name;
            private TextView points;
            private TextView additions;
            private TextView total;

            private ResultHolder(View itemView) {
                super(itemView);

                name = (TextView) itemView.findViewById(R.id.results_name);
                points = (TextView) itemView.findViewById(R.id.results_points);
                additions = (TextView) itemView.findViewById(R.id.results_additions);
                total = (TextView) itemView.findViewById(R.id.results_total);
            }
        }
    }

    private ResultEntry createEntry(String playerName) {
        return new ResultEntry(playerName, presenter.getPoints(playerName),
                presenter.getAdditions(playerName), presenter.getTotal(playerName), presenter.getColor(playerName));
    }

    private static int getColorFromName(String colorName) {
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

    private static class ResultEntry {
        private String name;
        private int points;
        private int additions;
        private int total;
        private String color;

        private ResultEntry(String name, int points, int additions, int total, String color) {
            this.name = name;
            this.points = points;
            this.additions = additions;
            this.total = total;
            this.color = color;
        }

        private String getName() {
            return name;
        }

        private int getPoints() {
            return points;
        }

        private int getAdditions() {
            return additions;
        }

        private int getTotal() {
            return total;
        }

        private String getColor() {
            return color;
        }
    }
}
