package com.example.alec.phase_05.Shared.model;

import android.graphics.Point;

import com.example.alec.phase_05.Server.CommandManager;
import com.example.alec.phase_05.Server.model.IServerBank;
import com.example.alec.phase_05.Server.model.ServerBank;
import com.example.alec.phase_05.Server.model.ServerGame;

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

    public static ServerGame createGame(int id, String name, int maxPlayers) {
        Map<String, City> cities = createCities();
        Map<Integer, Route> routes = createRoute(cities);
        //List<Route> routes = createRoute(cities);
        GameMap map = createGameMap(cities, routes);
        IServerBank bank = createBank(cities);
        CommandManager commandManager = createCommandManager();
        IChatManager chatManager = createChatManager();
        return new ServerGame(id, name, maxPlayers, commandManager, chatManager, bank, map);
    }

    public static CommandManager createCommandManager() {
        return new CommandManager();
    }

    public static IChatManager createChatManager() {
        return new ChatManager();
    }

    public static IServerBank createBank(Map<String, City> cities) {
        return new ServerBank(createTrainCardDeck(), createDestinationCardDeck(cities));
    }

    public static GameMap createGameMap(Map<String, City> cities, Map<Integer, Route> routes) {
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
        cities.put("Denver", new City("Denver", new MyPoint(0, 0)));
        cities.put("El Paso", new City("El Paso", new MyPoint(0, 0)));
        cities.put("Kansas City", new City("Kansas City", new MyPoint(0, 0)));
        cities.put("Houston", new City("Houston", new MyPoint(0, 0)));
        cities.put("New York", new City("New York", new MyPoint(0, 0)));
        cities.put("Atlanta", new City("Atlanta", new MyPoint(0, 0)));
        cities.put("Chicago", new City("Chicago", new MyPoint(0, 0)));
        cities.put("New Orleans", new City("New Orleans", new MyPoint(0, 0)));
        cities.put("Calgary", new City("Calgary", new MyPoint(0, 0)));
        cities.put("Salt Lake City", new City("Salt Lake City", new MyPoint(0, 0)));
        cities.put("Halena", new City("Halena", new MyPoint(0, 0)));
        cities.put("Los Angeles", new City("Los Angeles", new MyPoint(0, 0)));
        cities.put("Las Vegas", new City("Las Vegas", new MyPoint(0, 0)));
        cities.put("Duluth", new City("Duluth", new MyPoint(0, 0)));
        cities.put("Sault Ste Marie", new City("Sault Ste Marie", new MyPoint(0, 0)));
        cities.put("Nashville", new City("Nashville", new MyPoint(0, 0)));
        cities.put("Montreal", new City("Montreal", new MyPoint(0, 0)));
        cities.put("Oklahoma City", new City("Oklahoma City", new MyPoint(0, 0)));
        cities.put("Seattle", new City("Seattle", new MyPoint(0, 0)));
        cities.put("Santa Fe", new City("Santa Fe", new MyPoint(0, 0)));
        cities.put("Toronto", new City("Toronto", new MyPoint(0, 0)));
        cities.put("Miami", new City("Miami", new MyPoint(0, 0)));
        cities.put("Portland", new City("Portland", new MyPoint(0, 0)));
        cities.put("Phoenix", new City("Phoenix", new MyPoint(0, 0)));
        cities.put("Dallas", new City("Dallas", new MyPoint(0, 0)));
        cities.put("Pittsburgh", new City("Pittsburgh", new MyPoint(0, 0)));
        cities.put("Winnipeg", new City("Winnipeg", new MyPoint(0, 0)));
        cities.put("Little Rock", new City("Little Rock", new MyPoint(0, 0)));
        cities.put("Boston", new City("Boston", new MyPoint(0, 0)));
        cities.put("Vancouver", new City("Vancouver", new MyPoint(0, 0)));
        cities.put("San Francisco", new City("San Francisco", new MyPoint(0, 0)));
        cities.put("Omaha", new City("Omaha", new MyPoint(0, 0)));
        cities.put("Saint Louis", new City("Saint Louis", new MyPoint(0, 0)));
        cities.put("Raleigh", new City("Raleigh", new MyPoint(0, 0)));
        cities.put("Washington", new City("Washington", new MyPoint(0, 0)));
        cities.put("Charleston", new City("Charleston", new MyPoint(0, 0)));
        return cities;
    }

    public static Map<Integer, Route> createRoute(Map<String, City> cities) {
        Map<Integer, Route> routes = new HashMap<>();
        routes.put(1, new Route(cities.get("Vancouver"), cities.get("Seattle"), 1, null, ANY, 1));
        routes.put(2, new Route(cities.get("Vancouver"), cities.get("Seattle"), 1, null, ANY, 2));
        routes.put(3, new Route(cities.get("Seattle"), cities.get("Portland"), 1, null, ANY, 3));
        routes.put(4, new Route(cities.get("Seattle"), cities.get("Portland"), 1, null, ANY, 4));
        routes.put(5, new Route(cities.get("Vancouver"), cities.get("Calgary"), 3, null, ANY, 5));
        routes.put(6, new Route(cities.get("Seattle"), cities.get("Calgary"), 4, null, ANY, 6));
        routes.put(7, new Route(cities.get("Calgary"), cities.get("Winnipeg"), 6, null, PASSENGER, 7));
        routes.put(8, new Route(cities.get("Calgary"), cities.get("Helena"), 4, null, ANY, 8));
        routes.put(9, new Route(cities.get("Seattle"), cities.get("Helena"), 6, null, REEFER, 9));
        routes.put(10, new Route(cities.get("Portland"), cities.get("Salt Lake City"), 6, null, TANKER, 10));
        routes.put(11, new Route(cities.get("Portland"), cities.get("San Francisco"), 5, null, CABOOSE, 11));
        routes.put(12, new Route(cities.get("Portland"), cities.get("San Francisco"), 5, null, BOX, 12));
        routes.put(13, new Route(cities.get("San Francisco"), cities.get("Salt Lake City"), 5, null, FREIGHT, 13));
        routes.put(14, new Route(cities.get("San Francisco"), cities.get("Salt Lake City"), 5, null, PASSENGER, 14));
        routes.put(15, new Route(cities.get("San Francisco"), cities.get("Los Angeles"), 3, null, REEFER, 15));
        routes.put(16, new Route(cities.get("San Francisco"), cities.get("Los Angeles"), 3, null, BOX, 16));
        routes.put(17, new Route(cities.get("Los Angeles"), cities.get("Las Vegas"), 2, null, ANY, 17));
        routes.put(18, new Route(cities.get("Las Vegas"), cities.get("Salt Lake City"), 3, null, FREIGHT, 18));
        routes.put(19, new Route(cities.get("Los Angeles"), cities.get("Phoenix"), 3, null, ANY, 19));
        routes.put(20, new Route(cities.get("Los Angeles"), cities.get("El Paso"), 6, null, HOPPER, 20));
        routes.put(21, new Route(cities.get("Phoenix"), cities.get("El Paso"), 3, null, ANY, 21));
        routes.put(22, new Route(cities.get("Phoenix"), cities.get("Santa Fe"), 3, null, ANY, 22));
        routes.put(23, new Route(cities.get("Santa Fe"), cities.get("El Paso"), 2, null, ANY, 23));
        routes.put(24, new Route(cities.get("Phoenix"), cities.get("Denver"), 5, null, PASSENGER, 24));
        routes.put(25, new Route(cities.get("Salt Lake City"), cities.get("Denver"), 3, null, REEFER, 25));
        routes.put(26, new Route(cities.get("Salt Lake City"), cities.get("Denver"), 3, null, COAL, 26));
        routes.put(27, new Route(cities.get("Salt Lake City"), cities.get("Helena"), 3, null, BOX, 27));
        routes.put(28, new Route(cities.get("Helena"), cities.get("Winnipeg"), 4, null, TANKER, 28));
        routes.put(29, new Route(cities.get("Helena"), cities.get("Duluth"), 6, null, FREIGHT, 29));
        routes.put(30, new Route(cities.get("Helena"), cities.get("Omaha"), 5, null, COAL, 30));
        routes.put(31, new Route(cities.get("Helena"), cities.get("Denver"), 4, null, CABOOSE, 31));
        routes.put(32, new Route(cities.get("Denver"), cities.get("Omaha"), 4, null, BOX, 32));
        routes.put(33, new Route(cities.get("Denver"), cities.get("Kansas City"), 4, null, HOPPER, 33));
        routes.put(34, new Route(cities.get("Denver"), cities.get("Kansas City"), 4, null, FREIGHT, 34));
        routes.put(35, new Route(cities.get("Denver"), cities.get("Oklahoma City"), 4, null, COAL, 35));
        routes.put(36, new Route(cities.get("Denver"), cities.get("Santa Fe"), 2, null, ANY, 36));
        routes.put(37, new Route(cities.get("Oklahoma City"), cities.get("Kansas City"), 2, null, ANY, 37));
        routes.put(38, new Route(cities.get("Oklahoma City"), cities.get("Kansas City"), 2, null, ANY, 38));
        routes.put(39, new Route(cities.get("Oklahoma City"), cities.get("Little Rock"), 2, null, ANY, 39));
        routes.put(40, new Route(cities.get("Oklahoma City"), cities.get("Dallas"), 2, null, ANY, 40));
        routes.put(41, new Route(cities.get("Oklahoma City"), cities.get("Dallas"), 2, null, ANY, 41));
        routes.put(42, new Route(cities.get("Oklahoma City"), cities.get("Santa Fe"), 3, null, TANKER, 42));
        routes.put(43, new Route(cities.get("Oklahoma City"), cities.get("El Paso"), 5, null, REEFER, 43));
        routes.put(44, new Route(cities.get("El Paso"), cities.get("Dallas"), 4, null, COAL, 44));
        routes.put(45, new Route(cities.get("El Paso"), cities.get("Houston"), 6, null, CABOOSE, 45));
        routes.put(46, new Route(cities.get("Dallas"), cities.get("Houston"), 1, null, ANY, 46));
        routes.put(47, new Route(cities.get("Dallas"), cities.get("Houston"), 1, null, ANY, 47));
        routes.put(48, new Route(cities.get("Houston"), cities.get("New Orleans"), 2, null, ANY, 48));
        routes.put(49, new Route(cities.get("Dallas"), cities.get("Little Rock"), 2, null, ANY, 49));
        routes.put(50, new Route(cities.get("Omaha"), cities.get("Kansas City"), 1, null, ANY, 50));
        routes.put(51, new Route(cities.get("Omaha"), cities.get("Kansas City"), 1, null, ANY, 51));
        routes.put(52, new Route(cities.get("Kansas City"), cities.get("Saint Louis"), 2, null, TANKER, 52));
        routes.put(53, new Route(cities.get("Kansas City"), cities.get("Saint Louis"), 2, null, BOX, 53));
        routes.put(54, new Route(cities.get("Omaha"), cities.get("Duluth"), 2, null, ANY, 54));
        routes.put(55, new Route(cities.get("Omaha"), cities.get("Duluth"), 2, null, ANY, 55));
        routes.put(56, new Route(cities.get("Duluth"), cities.get("Winnipeg"), 4, null, HOPPER, 56));
        routes.put(57, new Route(cities.get("Duluth"), cities.get("Sault Ste Marie"), 3, null, ANY, 57));
        routes.put(58, new Route(cities.get("Duluth"), cities.get("Toronto"), 6, null, BOX, 58));
        routes.put(59, new Route(cities.get("Duluth"), cities.get("Chicago"), 3, null, COAL, 59));
        routes.put(60, new Route(cities.get("Sault Ste Marie"), cities.get("Winnipeg"), 6, null, ANY, 60));
        routes.put(61, new Route(cities.get("Sault Ste Marie"), cities.get("Montreal"), 5, null, HOPPER, 61));
        routes.put(62, new Route(cities.get("Sault Ste Marie"), cities.get("Toronto"), 2, null, ANY, 62));
        routes.put(63, new Route(cities.get("Chicago"), cities.get("Saint Louis"), 2, null, CABOOSE, 63));
        routes.put(64, new Route(cities.get("Chicago"), cities.get("Saint Louis"), 2, null, PASSENGER, 64));
        routes.put(65, new Route(cities.get("Chicago"), cities.get("Omaha"), 4, null, TANKER, 65));
        routes.put(66, new Route(cities.get("Chicago"), cities.get("Pittsburgh"), 3, null, HOPPER, 66));
        routes.put(67, new Route(cities.get("Chicago"), cities.get("Pittsburgh"), 3, null, FREIGHT, 67));
        routes.put(68, new Route(cities.get("Chicago"), cities.get("Toronto"), 4, null, PASSENGER, 68));
        routes.put(69, new Route(cities.get("Pittsburgh"), cities.get("Toronto"), 2, null, ANY, 69));
        routes.put(70, new Route(cities.get("Pittsburgh"), cities.get("Saint Louis"), 5, null, CABOOSE, 70));
        routes.put(71, new Route(cities.get("Pittsburgh"), cities.get("Nashville"), 4, null, REEFER, 71));
        routes.put(72, new Route(cities.get("Pittsburgh"), cities.get("Raleigh"), 2, null, ANY, 72));
        routes.put(73, new Route(cities.get("Pittsburgh"), cities.get("Washington"), 2, null, ANY, 73));
        routes.put(74, new Route(cities.get("Pittsburgh"), cities.get("New York"), 2, null, PASSENGER, 74));
        routes.put(75, new Route(cities.get("Pittsburgh"), cities.get("New York"), 2, null, CABOOSE, 75));
        routes.put(76, new Route(cities.get("Montreal"), cities.get("Toronto"), 3, null, ANY, 76));
        routes.put(77, new Route(cities.get("Montreal"), cities.get("New York"), 3, null, TANKER, 77));
        routes.put(78, new Route(cities.get("Montreal"), cities.get("Boston"), 2, null, ANY, 78));
        routes.put(79, new Route(cities.get("Montreal"), cities.get("Boston"), 2, null, ANY, 79));
        routes.put(80, new Route(cities.get("Boston"), cities.get("New York"), 2, null, REEFER, 80));
        routes.put(81, new Route(cities.get("Boston"), cities.get("New York"), 2, null, COAL, 81));
        routes.put(82, new Route(cities.get("New York"), cities.get("Washington"), 2, null, FREIGHT, 82));
        routes.put(83, new Route(cities.get("New York"), cities.get("Washington"), 2, null, HOPPER, 83));
        routes.put(84, new Route(cities.get("Raleigh"), cities.get("Washington"), 2, null, ANY, 84));
        routes.put(85, new Route(cities.get("Raleigh"), cities.get("Washington"), 2, null, ANY, 85));
        routes.put(86, new Route(cities.get("Raleigh"), cities.get("Atlanta"), 2, null, ANY, 86));
        routes.put(87, new Route(cities.get("Raleigh"), cities.get("Atlanta"), 2, null, ANY, 87));
        routes.put(88, new Route(cities.get("Raleigh"), cities.get("Nashville"), 3, null, HOPPER, 88));
        routes.put(89, new Route(cities.get("Raleigh"), cities.get("Charleston"), 2, null, ANY, 89));
        routes.put(90, new Route(cities.get("Saint Louis"), cities.get("Little Rock"), 2, null, ANY, 90));
        routes.put(91, new Route(cities.get("Nashville"), cities.get("Saint Louis"), 2, null, ANY, 91));
        routes.put(92, new Route(cities.get("Nashville"), cities.get("Little Rock"), 3, null, PASSENGER, 92));
        routes.put(93, new Route(cities.get("Nashville"), cities.get("Atlanta"), 1, null, ANY, 93));
        routes.put(94, new Route(cities.get("New Orleans"), cities.get("Houston"), 2, null, ANY, 94));
        routes.put(95, new Route(cities.get("New Orleans"), cities.get("Little Rock"), 3, null, CABOOSE, 95));
        routes.put(96, new Route(cities.get("New Orleans"), cities.get("Miami"), 6, null, COAL, 96));
        routes.put(97, new Route(cities.get("New Orleans"), cities.get("Atlanta"), 4, null, REEFER, 97));
        routes.put(98, new Route(cities.get("New Orleans"), cities.get("Atlanta"), 4, null, FREIGHT, 98));
        routes.put(99, new Route(cities.get("Atlanta"), cities.get("Charleston"), 2, null, ANY, 99));
        routes.put(100, new Route(cities.get("Atlanta"), cities.get("Miami"), 5, null, TANKER, 100));
        routes.put(101, new Route(cities.get("Charleston"), cities.get("Miami"), 4, null, BOX, 101));
        return routes;
    }
}
