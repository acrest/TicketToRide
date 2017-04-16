package com.example.alec.phase_05.Shared.model;

import java.io.Serializable;

/**
 * Created by Andrew on 3/2/2017.
 */

public class MyPoint implements Serializable
{
    int x;
    int y;

    public MyPoint(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
