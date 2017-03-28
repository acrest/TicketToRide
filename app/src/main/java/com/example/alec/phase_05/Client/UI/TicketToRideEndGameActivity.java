package com.example.alec.phase_05.Client.UI;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.alec.phase_05.Client.Presenter.IPresenterTicketToRideEndGame;
import com.example.alec.phase_05.Client.Presenter.ITicketToRideEndGameListener;
import com.example.alec.phase_05.Client.Presenter.PresenterTicketToRideEndGame;
import com.example.alec.phase_05.R;
import com.example.alec.phase_05.Shared.model.IPlayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class TicketToRideEndGameActivity extends Activity implements ITicketToRideEndGameListener {
    private IPresenterTicketToRideEndGame presenter;
    private ResultsAdapter mResultsAdapter;
    private TextView mWinner;
    private TextView mLongestRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ticket_to_ride_end_game);

        presenter = new PresenterTicketToRideEndGame(this);

        RecyclerView mResults = (RecyclerView) findViewById(R.id.results_list);
        mWinner = (TextView) findViewById(R.id.results_winner_text);
        mLongestRoute = (TextView) findViewById(R.id.results_longest_route);

        mWinner.setText(presenter.getWinner());
        mLongestRoute.setText(presenter.getLongestRouteHolder());

        List<ResultEntry> entries = new ArrayList<>();
        Iterator<String> players = presenter.getPlayerNames();
        while(players.hasNext()) {
            entries.add(createEntry(players.next()));
        }
        mResultsAdapter = new ResultsAdapter(entries, this);

        mResults.setLayoutManager(new LinearLayoutManager(this));
        mResults.setAdapter(mResultsAdapter);
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
            holder.penalties.setText(String.format(Locale.getDefault(), "%d", entry.getPenalties()));
            holder.penalties.setTextColor(getColorFromName(entry.getColor()));
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

        class ResultHolder extends RecyclerView.ViewHolder {
            private TextView name;
            private TextView points;
            private TextView penalties;
            private TextView total;

            private ResultHolder(View itemView) {
                super(itemView);

                name = (TextView) itemView.findViewById(R.id.results_name);
                points = (TextView) itemView.findViewById(R.id.results_points);
                penalties = (TextView) itemView.findViewById(R.id.results_penalties);
                total = (TextView) itemView.findViewById(R.id.results_total);
            }
        }
    }

    private ResultEntry createEntry(String playerName) {
        return new ResultEntry(playerName, presenter.getPoints(playerName),
                presenter.getPenalties(playerName), presenter.getTotal(playerName), presenter.getColor(playerName));
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
        private int penalties;
        private int total;
        private String color;

        private ResultEntry(String name, int points, int penalties, int total, String color) {
            this.name = name;
            this.points = points;
            this.penalties = penalties;
            this.total = total;
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public int getPoints() {
            return points;
        }

        public int getPenalties() {
            return penalties;
        }

        public int getTotal() {
            return total;
        }

        public String getColor() {
            return color;
        }
    }
}
