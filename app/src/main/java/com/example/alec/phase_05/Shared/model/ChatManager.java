package com.example.alec.phase_05.Shared.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alec on 2/24/17.
 */

public class ChatManager implements IChatManager {

    private Map<String,Integer> recentMessages;
    private List<Chat> chats;

    public ChatManager() {
        recentMessages = new HashMap<>();
        chats = new ArrayList<>();
    }

    @Override
    public List<Chat> getChats(Player player) {

        if(chats.size()<=0){
            return null;
        }

        if(recentMessages.containsKey(player.getName())){
            Integer mostRecent = recentMessages.get(player.getName());
            List<Chat> newChats = new ArrayList<>();
            Integer latestID = -1;
            for(int i=0;i<chats.size();i++){
                if(mostRecent<chats.get(i).getID()){
                    newChats.add(chats.get(i));
                }
                latestID = chats.get(i).getID();
            }
            recentMessages.put(player.getName(),latestID);
            return newChats;
        }
        else{
            recentMessages.put(player.getName(),chats.get(chats.size()-1).getID());
            return chats;
        }
    }

    @Override
    public void addChat(Chat chat){
        chat.setGameID(chats.size()+1);
        chats.add(chat);
    }


}
