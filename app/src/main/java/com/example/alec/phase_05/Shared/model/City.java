package com.example.alec.phase_05.Shared.model;

import android.graphics.Point;

/**
 * Created by Alec on 2/21/17.
 */

public class City {

    private String name;
    private Point point;
    private Integer id;

    public City(String _name, Point _point){
        name = _name;
        point = _point;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getXCord(){
        return point.x;
    }

    public double getYCord(){
        return point.y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
