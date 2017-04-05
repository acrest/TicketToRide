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
    private Map<City, ArrayList<Route>> cityToRoutes;

    public GameMap(Map<String, City> cities, Map<Integer, Route> routes) {
        this.cities = cities;
        this.routes = routes;

        cityToRoutes = null;
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

    public boolean isInPlayerList(ArrayList<IPlayer> playerList, Route tempRoute) {
        IPlayer playerInRoute = tempRoute.getOwner();
        String playerName = playerInRoute.getName();

        for(IPlayer player: playerList) {
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

    public Map<IPlayer, Integer> findLongestRoute() {
        ArrayList<IPlayer> playerList = new ArrayList<>();
        Map<IPlayer, Integer> longestPlayerMap = new HashMap<>();
        Map<IPlayer, ArrayList<Route>> routesToPlayersMap = new HashMap<>();
        for(int i = 1; i <= routes.size(); i++ ) {//Maybe this should be set to number of Routes not magic number 100 or routes.size() or something
            Route tempRoute = getRouteByID(i);
            if (tempRoute.getOwner() != null) {
                boolean inPList = isInPlayerList(playerList, tempRoute);
                IPlayer newPlayer = tempRoute.getOwner();
                if (!inPList) {
                    playerList.add(newPlayer);
                    ArrayList<Route> newRoutes = new ArrayList<>();
                    routesToPlayersMap.put(newPlayer, newRoutes);
                }
                routesToPlayersMap.get(newPlayer).add(tempRoute);
            }
        }

        IPlayer longestPlayer = null;
        int longestRoute = 0;

        for(Map.Entry<IPlayer, ArrayList<Route>> entry : routesToPlayersMap.entrySet()) {
            IPlayer player = entry.getKey();
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
                        longestPlayerMap = new HashMap<>();
                        longestPlayerMap.put(player, longestRoute);
                    } else if( longestRouteLength == longestRoute) {
                        longestPlayerMap.put(player, longestRoute);
                    }
                }
            }
        }
        System.out.println("OUT OF SECOND LOOP " + longestPlayerMap);
        for (Map.Entry<IPlayer, Integer> entry : longestPlayerMap.entrySet()) {
            IPlayer key = entry.getKey();
            int value = entry.getValue();
            System.out.println("Game map PLAYER: "  + key.getName() + " " + Integer.toString(value));
        }
        return longestPlayerMap;
    }

    public boolean checkIfDestinationComplete(DestinationCard destinationCard, String player){
        if(cityToRoutes == null) {
            initCityToRoutes();
        }

        City city1 = destinationCard.getCity1();
        City city2 = destinationCard.getCity2();
        ArrayList<Route> rootRoutes = new ArrayList<>();
        Map<City, Boolean> markedCities = initializeCitiesToFalse();

        markedCities.put(city1, true);

        for(Route route : routes.values()){
            if(route.getCity1().equals(city1) || route.getCity2().equals(city2)){
                IPlayer owner = route.getOwner();
                if(owner != null && owner.getName().equals(player)){
                    rootRoutes.add(route);
                }
            }
        }

        for(Route route : routes.values()){
            City firstCity = route.getCity1();
            City secondCity = route.getCity2();

            if(firstCity.equals(city2) || secondCity.equals(city2)){
                return true;
            }

            if(markedCities.get(firstCity) != true){
                markedCities.put(firstCity, true);

                for(int j = 0; j < cityToRoutes.get(firstCity).size(); j++){
                    IPlayer owner = cityToRoutes.get(firstCity).get(j).getOwner();
                    if(owner != null && owner.getName().equals(player)){
                        if(checkIfDestinationCompleteRec(city2, firstCity, cityToRoutes.get(firstCity).get(j), markedCities, player)){
                            return true;
                        }
                    }
                }
            }

            if(markedCities.get(secondCity) != true){
                markedCities.put(secondCity, true);

                for(int j = 0; j < cityToRoutes.get(secondCity).size(); j++){
                    IPlayer owner = cityToRoutes.get(secondCity).get(j).getOwner();
                    if(owner != null && owner.getName().equals(player)){
                        if(checkIfDestinationCompleteRec(city2, secondCity, cityToRoutes.get(secondCity).get(j), markedCities, player)){
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private boolean checkIfDestinationCompleteRec(City city2, City city, Route rootRoute, Map<City, Boolean> markedCities, String player){

        for(int i = 0; i < cityToRoutes.get(city).size(); i++){
            Route route = cityToRoutes.get(city).get(i);

            IPlayer owner = route.getOwner();
            if(owner != null && owner.getName().equals(player)){

                if(route != rootRoute){
                    City firstCity = route.getCity1();
                    City secondCity = route.getCity2();

                    if(firstCity.equals(city2) || secondCity.equals(city2)){
                        return true;
                    }

                    if(markedCities.get(firstCity) != true){
                        markedCities.put(firstCity, true);

                        for(int j = 0; j < cityToRoutes.get(firstCity).size(); j++){

                            Route route2 = cityToRoutes.get(firstCity).get(j);
                            IPlayer owner2 = route2.getOwner();
                            if(owner2 != null && owner2.getName().equals(player)){
                                if(checkIfDestinationCompleteRec(city2, firstCity, route2, markedCities, player)){
                                    return true;
                                }
                            }
                        }
                    }

                    if(markedCities.get(secondCity) != true){
                        markedCities.put(secondCity, true);

                        for(int j = 0; j < cityToRoutes.get(secondCity).size(); j++){

                            Route route2 = cityToRoutes.get(secondCity).get(j);
                            IPlayer owner2 = route2.getOwner();
                            if(owner2 != null && owner2.getName().equals(player)){
                                if(checkIfDestinationCompleteRec(city2, secondCity, route2, markedCities, player)){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
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

    private void initCityToRoutes() {
        cityToRoutes = new HashMap<>();

        for(Map.Entry<String, City> entry : cities.entrySet()) {
            cityToRoutes.put(entry.getValue(), new ArrayList<Route>());
        }

        for(Route route : routes.values()){
            cityToRoutes.get(route.getCity1()).add(route);
            cityToRoutes.get(route.getCity2()).add(route);
        }
    }


    private Map<City, Boolean> initializeCitiesToFalse(){
        Map<City, Boolean> map = new HashMap<>();

        for(City city : cities.values()) {
            map.put(city, false);
        }

        return map;
    }
}
