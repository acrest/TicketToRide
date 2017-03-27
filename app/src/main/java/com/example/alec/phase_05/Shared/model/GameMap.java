package com.example.alec.phase_05.Shared.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.alec.phase_05.R.id.map;

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

    public Map<Integer, Route> getRoutes(){
        return routes;
    }

    public boolean isInPlayerList(ArrayList<Player> playerList, Route tempRoute) {
        Player playerInRoute = (Player) tempRoute.getOwner();
        String playerName = playerInRoute.getName();

        for(Player player: playerList) {
            String tempName = player.getName();
            if (tempName.equals(playerName)) {
                return true;
            }
        }
        return false;
    }

    private boolean connectsTo(City city, Route route) {
        String cityName = city.getName();
        String city1Name = route.getCity1().getName();
        String city2Name = route.getCity2().getName();
        if (cityName.equals(city1Name) || cityName.equals(city2Name)) {
            return true;
        }
        return false;
    }

    public Map<Player, Integer> findLongestRoute() {
        int numberOfRoutes = 100;
        ArrayList<Player> playerList = new ArrayList<Player>();
        Map<Player, Integer> longestPlayerMap = new HashMap<Player, Integer>();
        Map<Player, ArrayList<Route>> routesToPlayersMap = new HashMap<Player, ArrayList<Route>>();
        for(int i = 1; i <= 100; i++ ) {
            Route tempRoute = getRouteByID(i);
            boolean inPList = isInPlayerList(playerList, tempRoute);
            Player newPlayer = (Player) tempRoute.getOwner();
            if (!inPList) {
                playerList.add(newPlayer);
                ArrayList<Route> newRoutes = new ArrayList<>();
                routesToPlayersMap.put(newPlayer, newRoutes);
            }
            routesToPlayersMap.get(newPlayer).add(tempRoute);

        }

        Player longestPlayer = null;
        int longestRoute = 0;


        for(Map.Entry<Player, ArrayList<Route>> entry : routesToPlayersMap.entrySet()) {
            Player player = entry.getKey();
            ArrayList<Route> routeList = entry.getValue();
            ArrayList<Route> visited = new ArrayList<>();
            Collections.sort(routeList, new Comparator<Route>() {
                @Override
                public int compare(Route o1, Route o2) {
                    return o1.getLength() - o2.getLength();
                }
            });
            for (int i = 0; i < routeList.size(); i++) {
                Route tempRoute = routeList.get(i);
                if (!visited.contains(tempRoute)) {
                    visited.add(tempRoute);
                    routeList.remove(tempRoute);
                    visited = getLongestRoute(tempRoute, routeList, visited);
                    int longestRouteLength = getVisitedLength(visited);
                    if (longestRouteLength > longestRoute) {
                        longestRoute = longestRouteLength;
                        longestPlayerMap = new HashMap<Player, Integer>();
                        longestPlayerMap.put(player, longestRoute);
                    } else if( longestRouteLength == longestRoute) {
                        longestPlayerMap.put(player, longestRoute);
                    }
                }
            }



        }
        return longestPlayerMap;
    }

    private int getVisitedLength(ArrayList<Route> visited) {
        int totalLength = 0;
        for(Route route : visited) {
            int tempLength = route.getLength();
            totalLength+=tempLength;
        }
        return totalLength;
    }

    private ArrayList<Route> connectedTo(City city, ArrayList<Route> routes) {
        ArrayList<Route> connectedRoutes = new ArrayList<>();
        if (routes.size() > 0) {
            for (Route route : routes) {
                if (connectsTo(city, route)) {
                    connectedRoutes.add(route);

                }
            }
            Collections.sort(connectedRoutes, new Comparator<Route>() {
                @Override
                public int compare(Route o1, Route o2) {
                    return o1.getLength() - o2.getLength();
                }
            });
        }
        return connectedRoutes;
    }


    private ArrayList<Route> getLongestRoute(Route tempRoute, ArrayList<Route> routeList, ArrayList<Route> visited) {
        ArrayList<Route> firstCityRoutes = null;
        ArrayList<Route> secondCityRoutes = null;
        ArrayList<Route> routesWithCity1 = connectedTo(tempRoute.getCity1(), routeList);
        ArrayList<Route> routesWithCity2 = connectedTo(tempRoute.getCity2(), routeList);
        if (routesWithCity1.size() == 0 && routesWithCity2.size() == 0) {
            return visited;
        }
        if (routesWithCity1.size() > 0 && routesWithCity2.size() > 0 )  {
            if (routesWithCity1.get(0).getLength() >= routesWithCity2.get(0).getLength()) {
                firstCityRoutes = routesWithCity1;
                secondCityRoutes = routesWithCity2;
            } else {
                firstCityRoutes = routesWithCity2;
                secondCityRoutes = routesWithCity1;
            }
        } else if(routesWithCity1.size() > 0) {
            firstCityRoutes = routesWithCity1;
            secondCityRoutes = routesWithCity2;
        } else {
            firstCityRoutes = routesWithCity2;
            secondCityRoutes = routesWithCity1;
        }

        if (firstCityRoutes.size() > 0) {
            for (Route route : firstCityRoutes) {
                if (!visited.contains(route)) {
                    visited.add(route);
                    routeList.remove(route);
                    //currentLength = currentLength + route.getLength();

                    ArrayList<Route> testVisited = getLongestRoute(route, routeList, visited);
                    int visitedLength = getVisitedLength(visited);
                    int testVisitedLength = getVisitedLength(testVisited);
                    if (testVisitedLength > visitedLength) {
                        visited = testVisited;
                    }

                }
            }
        }

        if (secondCityRoutes.size() > 0) {
            for (Route route : secondCityRoutes) {
                if (!visited.contains(route)) {
                    visited.add(route);
                    routeList.remove(route);
                    //currentLength = currentLength + route.getLength();
                    ArrayList<Route> testVisited = getLongestRoute(route, routeList, visited);
                    int visitedLength = getVisitedLength(visited);
                    int testVisitedLength = getVisitedLength(testVisited);
                    if (testVisitedLength > visitedLength) {
                        visited = testVisited;
                    }
                }
            }
        }


        return visited;
    }


}
