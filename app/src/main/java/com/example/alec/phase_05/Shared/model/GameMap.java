package com.example.alec.phase_05.Shared.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by samuel on 2/23/17.
 */

public class GameMap {

    private Map<String, City> cities;
    private Map<Integer, Route> routes;

    public GameMap(Map<String, City> cities, Map<Integer, Route> routes) {
        this.cities = cities;
        this.routes = routes;
    }

    public Map<String, City> getCities() {
        return cities;
    }

    public Map<Integer, Route> getRoutes() {
        return routes;
    }
}
