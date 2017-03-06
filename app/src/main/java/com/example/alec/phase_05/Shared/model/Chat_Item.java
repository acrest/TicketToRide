package com.example.alec.phase_05.Shared.model;

/**
 * Created by Andrew on 3/2/2017.
 */

public class Chat_Item
{
    String message;
    String color;

    public Chat_Item(String message, String color) {
        this.message = message;
        this.color = color;
    }

    public String getMessage() {
        return message;
    }

    public String getColor() {
        return color;
    }
}
