package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Shared.model.Chat;
import com.example.alec.phase_05.Shared.model.ChatManager;
import com.example.alec.phase_05.Shared.model.Chat_Item;

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
        ChatManager chats = new ChatManager();

        for(int i = 0; i < 20; i++)
        {
            int tracker = i%5;

            if(tracker == 0)
            {
                Chat_Item message = new Chat_Item("This is an example of a chat! -Andrew", "blue");
                //Chat message = new Chat("Andrew", 1, "This is an example of a chat! -Andrew");
                messages.add(message);
                //chats.addChat(message)
            }
            else if(tracker == 1)
            {
                Chat_Item message = new Chat_Item("I suppose that is kinda cool. -Sam", "red");
                //Chat message = new Chat("Sam", 1, "I suppose that is kinda cool. -Sam");
                messages.add(message);
                //chats.addChat(message);
            }
            else if(tracker == 2)
            {
                Chat_Item message = new Chat_Item("I got lined up with a job after graduation! -Alec", "yellow");
                //Chat message = new Chat("Alec", 1, "I got lined up with a job after graduation! -Alec");
                messages.add(message);
                //chats.addChat(message);
            }
            else if(tracker == 3)
            {
                Chat_Item message = new Chat_Item("Ha, just took the longest road! -Clark", "black");
                //Chat message = new Chat("Clark", 1, "Ha, just took the longest road! -Clark");
                messages.add(message);
                //chats.addChat(message);
            }
            else if(tracker == 4)
            {
                Chat_Item message = new Chat_Item("Not for long! -Molly", "green");
                //Chat message = new Chat("Molly", 1, "Not for long! -Molly");
                messages.add(message);
                //chats.addChat(message);
            }
        }

        return messages;
    }
}
