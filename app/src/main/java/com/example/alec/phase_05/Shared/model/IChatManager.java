package com.example.alec.phase_05.Shared.model;

import java.util.List;

/**
 * Created by samuel on 2/22/17.
 */

public interface IChatManager {

    List<Chat> getChats(Player player);
    void addChat(Chat chat);

}
