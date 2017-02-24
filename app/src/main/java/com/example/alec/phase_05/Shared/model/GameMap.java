package com.example.alec.phase_05.Shared.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 2/23/17.
 */

public class GameMap {

    private List<City> cities;
    private List<Route> routes;

    public GameMap(List<City> cities, List<Route> routes) {
        this.cities = cities;
        this.routes = routes;
    }

    public List<City> getCities() {
        return cities;
    }

    public List<Route> getRoutes() {
        return routes;
    }
}
