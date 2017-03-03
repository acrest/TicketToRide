package com.example.alec.phase_05.Client.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 3/2/2017.
 */
public class Derpness    //*** SIMPLY A MODEL CLASS FOR TESTING PURPOSES, RECYCLER VIEWS ***
{
    private static Derpness ourInstance = new Derpness();

    public static Derpness getInstance() {
        return ourInstance;
    }

    private Derpness() {
    }

    public List<Chat_Item> generateFakeChat()
    {
        List<Chat_Item> messages = new ArrayList<>();

        for(int i = 0; i < 20; i++)
        {
            int tracker = i%2;

            if(tracker == 0)
            {
                Chat_Item message = new Chat_Item("This is an example of a chat! -Andrew");
                messages.add(message);
            }
            else
            {
                Chat_Item message = new Chat_Item("I suppose that is kinda cool. -Sam");
                messages.add(message);
            }
        }

        return messages;
    }
}
