package com.example.alec.phase_05.Shared.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by samuel on 4/17/17.
 */

public class GameMapInfo {
    private Map<String, City> cities;
    private Map<Integer, RouteInfo> routes;

    public GameMapInfo(Map<String, City> cities, Map<Integer, RouteInfo> routes) {
        this.cities = cities;
        this.routes = routes;
    }

    public GameMapInfo(GameMap map) {
        cities = map.getCities();
        routes = new HashMap<>();
        for (Map.Entry<Integer, Route> entry : map.getRoutes().entrySet()) {
            routes.put(entry.getKey(), new RouteInfo(entry.getValue()));
        }
    }

    public Map<String, City> getCities() {
        return cities;
    }

    public City getCityByName(String name){
        return cities.get(name);
    }

    public void addCity(City city){
        cities.put(city.getName(), city);
    }

    public RouteInfo getRouteByID(int routeID){
        return routes.get(routeID);
    }

    public void addRoute(RouteInfo route){
        routes.put(route.getId(), route);
    }

    public Map<Integer, RouteInfo> getRoutes(){
        return routes;
    }
}
