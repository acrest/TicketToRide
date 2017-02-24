package com.example.alec.phase_05.Shared.model;

import android.graphics.Point;

import com.example.alec.phase_05.Server.CommandManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.alec.phase_05.Shared.model.TrainType.*;

/**
 * Created by samuel on 2/23/17.
 */

public final class GameComponentFactory {

    private GameComponentFactory() {}

    public static Game createGame(int id, String name, int maxPlayers) {
        Map<String, City> cities = createCities();
        List<Route> routes = createRoute(cities);
        GameMap map = createGameMap(cities, routes);
        Bank bank = createBank(cities);
        CommandManager commandManager = createCommandManager();
        IChatManager chatManager = createChatManager();
        return new Game(id, name, maxPlayers, commandManager, chatManager, bank, map);
    }

    public static CommandManager createCommandManager() {
        return new CommandManager();
    }

    public static IChatManager createChatManager() {
        return null; //TODO: create a real chat manager
    }

    public static Bank createBank(Map<String, City> cities) {
        return new Bank(createTrainCardDeck(), createDestinationCardDeck(cities));
    }

    public static GameMap createGameMap(Map<String, City> cities, List<Route> routes) {
        return new GameMap(cities, routes);
    }

    public static List<TrainCard> createTrainCardDeck() {
        List<TrainCard> deck = new ArrayList<>();
        for(int i = 0; i < 12; ++i) {
            deck.add(new TrainCard(BOX));
            deck.add(new TrainCard(PASSENGER));
            deck.add(new TrainCard(TANKER));
            deck.add(new TrainCard(REEFER));
            deck.add(new TrainCard(FREIGHT));
            deck.add(new TrainCard(HOPPER));
            deck.add(new TrainCard(COAL));
            deck.add(new TrainCard(CABOOSE));
            deck.add(new TrainCard(LOCOMOTIVE));
        }
        deck.add(new TrainCard(LOCOMOTIVE));
        deck.add(new TrainCard(LOCOMOTIVE));
        return deck;
    }

    public static List<DestinationCard> createDestinationCardDeck(Map<String, City> cities) {
        List<DestinationCard> deck = new ArrayList<>();
        deck.add(new DestinationCard(cities.get("Denver"), cities.get("El Paso"), 4));
        deck.add(new DestinationCard(cities.get("Kansas City"), cities.get("Houston"), 5));
        deck.add(new DestinationCard(cities.get("New York"), cities.get("Atlanta"), 6));
        deck.add(new DestinationCard(cities.get("Chicago"), cities.get("New Orleans"), 7));
        deck.add(new DestinationCard(cities.get("Calgary"), cities.get("Salt Lake City"), 7));
        deck.add(new DestinationCard(cities.get("Helena"), cities.get("Los Angeles"), 8));
        deck.add(new DestinationCard(cities.get("Duluth"), cities.get("Houston"), 8));
        deck.add(new DestinationCard(cities.get("Sault Ste Marie"), cities.get("Nashville"), 8));
        deck.add(new DestinationCard(cities.get("Montreal"), cities.get("Atlanta"), 9));
        deck.add(new DestinationCard(cities.get("Sault Ste Marie"), cities.get("Oklahoma City"), 9));
        deck.add(new DestinationCard(cities.get("Seattle"), cities.get("Los Angeles"), 9));
        deck.add(new DestinationCard(cities.get("Chicago"), cities.get("Santa Fe"), 9));
        deck.add(new DestinationCard(cities.get("Duluth"), cities.get("El Paso"), 10));
        deck.add(new DestinationCard(cities.get("Toronto"), cities.get("Miami"), 10));
        deck.add(new DestinationCard(cities.get("Portland"), cities.get("Phoenix"), 11));
        deck.add(new DestinationCard(cities.get("Dallas"), cities.get("New York City"), 11));
        deck.add(new DestinationCard(cities.get("Denver"), cities.get("Pittsburgh"), 11));
        deck.add(new DestinationCard(cities.get("Winnipeg"), cities.get("Little Rock"), 11));
        deck.add(new DestinationCard(cities.get("Winnipeg"), cities.get("Houston"), 12));
        deck.add(new DestinationCard(cities.get("Boston"), cities.get("Miami"), 12));
        deck.add(new DestinationCard(cities.get("Vancouver"), cities.get("Santa Fe"), 13));
        deck.add(new DestinationCard(cities.get("Calgary"), cities.get("Phoenix"), 13));
        deck.add(new DestinationCard(cities.get("Montreal"), cities.get("New Orleans"), 13));
        deck.add(new DestinationCard(cities.get("Los Angeles"), cities.get("Chicago"), 16));
        deck.add(new DestinationCard(cities.get("San Francisco"), cities.get("Atlanta"), 17));
        deck.add(new DestinationCard(cities.get("Portland"), cities.get("Nashville"), 17));
        deck.add(new DestinationCard(cities.get("Vancouver"), cities.get("Montreal"), 20));
        deck.add(new DestinationCard(cities.get("Los Angeles"), cities.get("Miami"), 20));
        deck.add(new DestinationCard(cities.get("Los Angeles"), cities.get("New York City"), 21));
        deck.add(new DestinationCard(cities.get("Seattle"), cities.get("New York City"), 22));
        return deck;
    }

    public static Map<String, City> createCities() {
        Map<String, City> cities = new HashMap<>();
        cities.put("Denver", new City("Denver", new Point(0, 0)));
        cities.put("El Paso", new City("El Paso", new Point(0, 0)));
        cities.put("Kansas City", new City("Kansas City", new Point(0, 0)));
        cities.put("Houston", new City("Houston", new Point(0, 0)));
        cities.put("New York", new City("New York", new Point(0, 0)));
        cities.put("Atlanta", new City("Atlanta", new Point(0, 0)));
        cities.put("Chicago", new City("Chicago", new Point(0, 0)));
        cities.put("New Orleans", new City("New Orleans", new Point(0, 0)));
        cities.put("Calgary", new City("Calgary", new Point(0, 0)));
        cities.put("Salt Lake City", new City("Salt Lake City", new Point(0, 0)));
        cities.put("Halena", new City("Halena", new Point(0, 0)));
        cities.put("Los Angeles", new City("Los Angeles", new Point(0, 0)));
        cities.put("Las Vegas", new City("Las Vegas", new Point(0, 0)));
        cities.put("Duluth", new City("Duluth", new Point(0, 0)));
        cities.put("Sault Ste Marie", new City("Sault Ste Marie", new Point(0, 0)));
        cities.put("Nashville", new City("Nashville", new Point(0, 0)));
        cities.put("Montreal", new City("Montreal", new Point(0, 0)));
        cities.put("Oklahoma City", new City("Oklahoma City", new Point(0, 0)));
        cities.put("Seattle", new City("Seattle", new Point(0, 0)));
        cities.put("Santa Fe", new City("Santa Fe", new Point(0, 0)));
        cities.put("Toronto", new City("Toronto", new Point(0, 0)));
        cities.put("Miami", new City("Miami", new Point(0, 0)));
        cities.put("Portland", new City("Portland", new Point(0, 0)));
        cities.put("Phoenix", new City("Phoenix", new Point(0, 0)));
        cities.put("Dallas", new City("Dallas", new Point(0, 0)));
        cities.put("Pittsburgh", new City("Pittsburgh", new Point(0, 0)));
        cities.put("Winnipeg", new City("Winnipeg", new Point(0, 0)));
        cities.put("Little Rock", new City("Little Rock", new Point(0, 0)));
        cities.put("Boston", new City("Boston", new Point(0, 0)));
        cities.put("Vancouver", new City("Vancouver", new Point(0, 0)));
        cities.put("San Francisco", new City("San Francisco", new Point(0, 0)));
        cities.put("Omaha", new City("Omaha", new Point(0, 0)));
        cities.put("Saint Louis", new City("Saint Louis", new Point(0, 0)));
        cities.put("Raleigh", new City("Raleigh", new Point(0, 0)));
        cities.put("Washington", new City("Washington", new Point(0, 0)));
        cities.put("Charleston", new City("Charleston", new Point(0, 0)));
        return cities;
    }

    public static List<Route> createRoute(Map<String, City> cities) {
        List<Route> routes = new ArrayList<>();
        routes.add(new Route(cities.get("Vancouver"), cities.get("Seattle"), 1, null, ANY));
        routes.add(new Route(cities.get("Vancouver"), cities.get("Seattle"), 1, null, ANY));
        routes.add(new Route(cities.get("Seattle"), cities.get("Portland"), 1, null, ANY));
        routes.add(new Route(cities.get("Seattle"), cities.get("Portland"), 1, null, ANY));
        routes.add(new Route(cities.get("Vancouver"), cities.get("Calgary"), 3, null, ANY));
        routes.add(new Route(cities.get("Seattle"), cities.get("Calgary"), 4, null, ANY));
        routes.add(new Route(cities.get("Calgary"), cities.get("Winnipeg"), 6, null, PASSENGER));
        routes.add(new Route(cities.get("Calgary"), cities.get("Helena"), 4, null, ANY));
        routes.add(new Route(cities.get("Seattle"), cities.get("Helena"), 6, null, REEFER));
        routes.add(new Route(cities.get("Portland"), cities.get("Salt Lake City"), 6, null, TANKER));
        routes.add(new Route(cities.get("Portland"), cities.get("San Francisco"), 5, null, CABOOSE));
        routes.add(new Route(cities.get("Portland"), cities.get("San Francisco"), 5, null, BOX));
        routes.add(new Route(cities.get("San Francisco"), cities.get("Salt Lake City"), 5, null, FREIGHT));
        routes.add(new Route(cities.get("San Francisco"), cities.get("Salt Lake City"), 5, null, PASSENGER));
        routes.add(new Route(cities.get("San Francisco"), cities.get("Los Angeles"), 3, null, REEFER));
        routes.add(new Route(cities.get("San Francisco"), cities.get("Los Angeles"), 3, null, BOX));
        routes.add(new Route(cities.get("Los Angeles"), cities.get("Las Vegas"), 2, null, ANY));
        routes.add(new Route(cities.get("Las Vegas"), cities.get("Salt Lake City"), 3, null, FREIGHT));
        routes.add(new Route(cities.get("Los Angeles"), cities.get("Phoenix"), 3, null, ANY));
        routes.add(new Route(cities.get("Los Angeles"), cities.get("El Paso"), 6, null, HOPPER));
        routes.add(new Route(cities.get("Phoenix"), cities.get("El Paso"), 3, null, ANY));
        routes.add(new Route(cities.get("Phoenix"), cities.get("Santa Fe"), 3, null, ANY));
        routes.add(new Route(cities.get("Santa Fe"), cities.get("El Paso"), 2, null, ANY));
        routes.add(new Route(cities.get("Phoenix"), cities.get("Denver"), 5, null, PASSENGER));
        routes.add(new Route(cities.get("Salt Lake City"), cities.get("Denver"), 3, null, REEFER));
        routes.add(new Route(cities.get("Salt Lake City"), cities.get("Denver"), 3, null, COAL));
        routes.add(new Route(cities.get("Salt Lake City"), cities.get("Helena"), 3, null, BOX));
        routes.add(new Route(cities.get("Helena"), cities.get("Winnipeg"), 4, null, TANKER));
        routes.add(new Route(cities.get("Helena"), cities.get("Duluth"), 6, null, FREIGHT));
        routes.add(new Route(cities.get("Helena"), cities.get("Omaha"), 5, null, COAL));
        routes.add(new Route(cities.get("Helena"), cities.get("Denver"), 4, null, CABOOSE));
        routes.add(new Route(cities.get("Denver"), cities.get("Omaha"), 4, null, BOX));
        routes.add(new Route(cities.get("Denver"), cities.get("Kansas City"), 4, null, HOPPER));
        routes.add(new Route(cities.get("Denver"), cities.get("Kansas City"), 4, null, FREIGHT));
        routes.add(new Route(cities.get("Denver"), cities.get("Oklahoma City"), 4, null, COAL));
        routes.add(new Route(cities.get("Denver"), cities.get("Santa Fe"), 2, null, ANY));
        routes.add(new Route(cities.get("Oklahoma City"), cities.get("Kansas City"), 2, null, ANY));
        routes.add(new Route(cities.get("Oklahoma City"), cities.get("Kansas City"), 2, null, ANY));
        routes.add(new Route(cities.get("Oklahoma City"), cities.get("Little Rock"), 2, null, ANY));
        routes.add(new Route(cities.get("Oklahoma City"), cities.get("Dallas"), 2, null, ANY));
        routes.add(new Route(cities.get("Oklahoma City"), cities.get("Dallas"), 2, null, ANY));
        routes.add(new Route(cities.get("Oklahoma City"), cities.get("Santa Fe"), 3, null, TANKER));
        routes.add(new Route(cities.get("Oklahoma City"), cities.get("El Paso"), 5, null, REEFER));
        routes.add(new Route(cities.get("El Paso"), cities.get("Dallas"), 4, null, COAL));
        routes.add(new Route(cities.get("El Paso"), cities.get("Houston"), 6, null, CABOOSE));
        routes.add(new Route(cities.get("Dallas"), cities.get("Houston"), 1, null, ANY));
        routes.add(new Route(cities.get("Dallas"), cities.get("Houston"), 1, null, ANY));
        routes.add(new Route(cities.get("Houston"), cities.get("New Orleans"), 2, null, ANY));
        routes.add(new Route(cities.get("Dallas"), cities.get("Little Rock"), 2, null, ANY));
        routes.add(new Route(cities.get("Omaha"), cities.get("Kansas City"), 1, null, ANY));
        routes.add(new Route(cities.get("Omaha"), cities.get("Kansas City"), 1, null, ANY));
        routes.add(new Route(cities.get("Kansas City"), cities.get("Saint Louis"), 2, null, TANKER));
        routes.add(new Route(cities.get("Kansas City"), cities.get("Saint Louis"), 2, null, BOX));
        routes.add(new Route(cities.get("Omaha"), cities.get("Duluth"), 2, null, ANY));
        routes.add(new Route(cities.get("Omaha"), cities.get("Duluth"), 2, null, ANY));
        routes.add(new Route(cities.get("Duluth"), cities.get("Winnipeg"), 4, null, HOPPER));
        routes.add(new Route(cities.get("Duluth"), cities.get("Sault Ste Marie"), 3, null, ANY));
        routes.add(new Route(cities.get("Duluth"), cities.get("Toronto"), 6, null, BOX));
        routes.add(new Route(cities.get("Duluth"), cities.get("Chicago"), 3, null, COAL));
        routes.add(new Route(cities.get("Sault Ste Marie"), cities.get("Winnipeg"), 6, null, ANY));
        routes.add(new Route(cities.get("Sault Ste Marie"), cities.get("Montreal"), 5, null, HOPPER));
        routes.add(new Route(cities.get("Sault Ste Marie"), cities.get("Toronto"), 2, null, ANY));
        routes.add(new Route(cities.get("Chicago"), cities.get("Saint Louis"), 2, null, CABOOSE));
        routes.add(new Route(cities.get("Chicago"), cities.get("Saint Louis"), 2, null, PASSENGER));
        routes.add(new Route(cities.get("Chicago"), cities.get("Omaha"), 4, null, TANKER));
        routes.add(new Route(cities.get("Chicago"), cities.get("Pittsburgh"), 3, null, HOPPER));
        routes.add(new Route(cities.get("Chicago"), cities.get("Pittsburgh"), 3, null, FREIGHT));
        routes.add(new Route(cities.get("Chicago"), cities.get("Toronto"), 4, null, PASSENGER));
        routes.add(new Route(cities.get("Pittsburgh"), cities.get("Toronto"), 2, null, ANY));
        routes.add(new Route(cities.get("Pittsburgh"), cities.get("Saint Louis"), 5, null, CABOOSE));
        routes.add(new Route(cities.get("Pittsburgh"), cities.get("Nashville"), 4, null, REEFER));
        routes.add(new Route(cities.get("Pittsburgh"), cities.get("Raleigh"), 2, null, ANY));
        routes.add(new Route(cities.get("Pittsburgh"), cities.get("Washington"), 2, null, ANY));
        routes.add(new Route(cities.get("Pittsburgh"), cities.get("New York"), 2, null, PASSENGER));
        routes.add(new Route(cities.get("Pittsburgh"), cities.get("New York"), 2, null, CABOOSE));
        routes.add(new Route(cities.get("Montreal"), cities.get("Toronto"), 3, null, ANY));
        routes.add(new Route(cities.get("Montreal"), cities.get("New York"), 3, null, TANKER));
        routes.add(new Route(cities.get("Montreal"), cities.get("Boston"), 2, null, ANY));
        routes.add(new Route(cities.get("Montreal"), cities.get("Boston"), 2, null, ANY));
        routes.add(new Route(cities.get("Boston"), cities.get("New York"), 2, null, REEFER));
        routes.add(new Route(cities.get("Boston"), cities.get("New York"), 2, null, COAL));
        routes.add(new Route(cities.get("New York"), cities.get("Washington"), 2, null, FREIGHT));
        routes.add(new Route(cities.get("New York"), cities.get("Washington"), 2, null, HOPPER));
        routes.add(new Route(cities.get("Raleigh"), cities.get("Washington"), 2, null, ANY));
        routes.add(new Route(cities.get("Raleigh"), cities.get("Washington"), 2, null, ANY));
        routes.add(new Route(cities.get("Raleigh"), cities.get("Atlanta"), 2, null, ANY));
        routes.add(new Route(cities.get("Raleigh"), cities.get("Atlanta"), 2, null, ANY));
        routes.add(new Route(cities.get("Raleigh"), cities.get("Nashville"), 3, null, HOPPER));
        routes.add(new Route(cities.get("Raleigh"), cities.get("Charleston"), 2, null, ANY));
        routes.add(new Route(cities.get("Saint Louis"), cities.get("Little Rock"), 2, null, ANY));
        routes.add(new Route(cities.get("Nashville"), cities.get("Saint Louis"), 2, null, ANY));
        routes.add(new Route(cities.get("Nashville"), cities.get("Little Rock"), 3, null, PASSENGER));
        routes.add(new Route(cities.get("Nashville"), cities.get("Atlanta"), 1, null, ANY));
        routes.add(new Route(cities.get("New Orleans"), cities.get("Houston"), 2, null, ANY));
        routes.add(new Route(cities.get("New Orleans"), cities.get("Little Rock"), 3, null, CABOOSE));
        routes.add(new Route(cities.get("New Orleans"), cities.get("Miami"), 6, null, COAL));
        routes.add(new Route(cities.get("New Orleans"), cities.get("Atlanta"), 4, null, REEFER));
        routes.add(new Route(cities.get("New Orleans"), cities.get("Atlanta"), 4, null, FREIGHT));
        routes.add(new Route(cities.get("Atlanta"), cities.get("Charleston"), 2, null, ANY));
        routes.add(new Route(cities.get("Atlanta"), cities.get("Miami"), 5, null, TANKER));
        routes.add(new Route(cities.get("Charleston"), cities.get("Miami"), 4, null, BOX));
        return routes;
    }
}
