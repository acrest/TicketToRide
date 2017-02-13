package com.example.alec.phase_05.Client.Model;

/**
 * Created by Andrew on 2/8/2017.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 2/3/2017.
 */

public class DerpData {
    public static final String[] players = {"Harry", "Ron", "Hermione", "Neville"};
    public static final String[] inGame = {"1/5"};

    public static List<ListItem> getListData()
    {
        List<ListItem> data = new ArrayList<>();
        int count = 1;

        for(int x = 0; x < 4; x++)
        {
            for(int i = 0; i < players.length; i++)
            {
                ListItem item = new ListItem();
                item.setTitle(Integer.toString(count));
                item.setPlayers(players[i]);
                item.setInGame(inGame[0]);
                data.add(item);
                count++;
            }
        }

        return data;
    }

}
