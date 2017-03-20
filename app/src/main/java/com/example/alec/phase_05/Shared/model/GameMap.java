package com.example.alec.phase_05.Shared.model;

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

    public City getCityByName(String name){
        return cities.get(name);
    }

    public void addCity(City city){
        cities.put(city.getName(), city);
    }

    public Route getRouteByID(int routeID){
        return routes.get(routeID);
    }

    public void addRoute(Route route){
        routes.put(route.getId(), route);
    }
}
