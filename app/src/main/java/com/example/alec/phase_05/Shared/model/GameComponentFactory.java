package com.example.alec.phase_05.Shared.model;

import android.graphics.PointF;
import android.view.View;

import com.example.alec.phase_05.R;
import com.example.alec.phase_05.Server.model.CommandManager;
import com.example.alec.phase_05.Server.model.IServerBank;
import com.example.alec.phase_05.Server.model.ServerBank;
import com.example.alec.phase_05.Server.model.ServerGame;

import java.util.HashMap;
import java.util.Map;

import static com.example.alec.phase_05.Shared.model.TrainType.ANY;
import static com.example.alec.phase_05.Shared.model.TrainType.BOX;
import static com.example.alec.phase_05.Shared.model.TrainType.CABOOSE;
import static com.example.alec.phase_05.Shared.model.TrainType.COAL;
import static com.example.alec.phase_05.Shared.model.TrainType.FREIGHT;
import static com.example.alec.phase_05.Shared.model.TrainType.HOPPER;
import static com.example.alec.phase_05.Shared.model.TrainType.LOCOMOTIVE;
import static com.example.alec.phase_05.Shared.model.TrainType.PASSENGER;
import static com.example.alec.phase_05.Shared.model.TrainType.REEFER;
import static com.example.alec.phase_05.Shared.model.TrainType.TANKER;

/**
 * Created by samuel on 2/23/17.
 */

public final class GameComponentFactory {

    private GameComponentFactory() {
    }

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

    public static TrainCardDeck createTrainCardDeck() {
        TrainCardDeck deck = new TrainCardDeck();
        for (int i = 0; i < 12; ++i) {
            deck.addCard(new TrainCard(BOX));
            deck.addCard(new TrainCard(PASSENGER));
            deck.addCard(new TrainCard(TANKER));
            deck.addCard(new TrainCard(REEFER));
            deck.addCard(new TrainCard(FREIGHT));
            deck.addCard(new TrainCard(HOPPER));
            deck.addCard(new TrainCard(COAL));
            deck.addCard(new TrainCard(CABOOSE));
            deck.addCard(new TrainCard(LOCOMOTIVE));
        }
        deck.addCard(new TrainCard(LOCOMOTIVE));
        deck.addCard(new TrainCard(LOCOMOTIVE));
        return deck;
    }

    public static DestinationCardDeck createDestinationCardDeck(Map<String, City> cities) {
        Map<Integer, DestinationCard> deck = new HashMap<>();
        deck.put(1, new DestinationCard(cities.get("Denver"), cities.get("El Paso"), 4));
        deck.put(2, new DestinationCard(cities.get("Kansas City"), cities.get("Houston"), 5));
        deck.put(3, new DestinationCard(cities.get("New York"), cities.get("Atlanta"), 6));
        deck.put(4, new DestinationCard(cities.get("Chicago"), cities.get("New Orleans"), 7));
        deck.put(5, new DestinationCard(cities.get("Calgary"), cities.get("Salt Lake City"), 7));
        deck.put(6, new DestinationCard(cities.get("Helena"), cities.get("Los Angeles"), 8));
        deck.put(7, new DestinationCard(cities.get("Duluth"), cities.get("Houston"), 8));
        deck.put(8, new DestinationCard(cities.get("Sault Ste Marie"), cities.get("Nashville"), 8));
        deck.put(9, new DestinationCard(cities.get("Montreal"), cities.get("Atlanta"), 9));
        deck.put(10, new DestinationCard(cities.get("Sault Ste Marie"), cities.get("Oklahoma City"), 9));
        deck.put(11, new DestinationCard(cities.get("Seattle"), cities.get("Los Angeles"), 9));
        deck.put(12, new DestinationCard(cities.get("Chicago"), cities.get("Santa Fe"), 9));
        deck.put(13, new DestinationCard(cities.get("Duluth"), cities.get("El Paso"), 10));
        deck.put(14, new DestinationCard(cities.get("Toronto"), cities.get("Miami"), 10));
        deck.put(15, new DestinationCard(cities.get("Portland"), cities.get("Phoenix"), 11));
        deck.put(16, new DestinationCard(cities.get("Dallas"), cities.get("New York"), 11));
        deck.put(17, new DestinationCard(cities.get("Denver"), cities.get("Pittsburgh"), 11));
        deck.put(18, new DestinationCard(cities.get("Winnipeg"), cities.get("Little Rock"), 11));
        deck.put(19, new DestinationCard(cities.get("Winnipeg"), cities.get("Houston"), 12));
        deck.put(20, new DestinationCard(cities.get("Boston"), cities.get("Miami"), 12));
        deck.put(21, new DestinationCard(cities.get("Vancouver"), cities.get("Santa Fe"), 13));
        deck.put(22, new DestinationCard(cities.get("Calgary"), cities.get("Phoenix"), 13));
        deck.put(23, new DestinationCard(cities.get("Montreal"), cities.get("New Orleans"), 13));
        deck.put(24, new DestinationCard(cities.get("Los Angeles"), cities.get("Chicago"), 16));
        deck.put(25, new DestinationCard(cities.get("San Francisco"), cities.get("Atlanta"), 17));
        deck.put(26, new DestinationCard(cities.get("Portland"), cities.get("Nashville"), 17));
        deck.put(27, new DestinationCard(cities.get("Vancouver"), cities.get("Montreal"), 20));
        deck.put(28, new DestinationCard(cities.get("Los Angeles"), cities.get("Miami"), 20));
        deck.put(29, new DestinationCard(cities.get("Los Angeles"), cities.get("New York"), 21));
        deck.put(30, new DestinationCard(cities.get("Seattle"), cities.get("New York"), 22));
        return new DestinationCardDeck(deck.values());
    }

    public static Map<String, City> createCities() {
        Map<String, City> cities = new HashMap<>();
        cities.put("Denver", new City("Denver", new MyPoint(906, 668)));
        cities.put("El Paso", new City("El Paso", new MyPoint(837, 1132)));
        cities.put("Kansas City", new City("Kansas City", new MyPoint(1263, 686)));
        cities.put("Houston", new City("Houston", new MyPoint(1332, 1228)));
        cities.put("New York", new City("New York", new MyPoint(2055, 401)));
        cities.put("Atlanta", new City("Atlanta", new MyPoint(1788, 925)));
        cities.put("Chicago", new City("Chicago", new MyPoint(1602, 557)));
        cities.put("New Orleans", new City("New Orleans", new MyPoint(1500, 1171)));
        cities.put("Calgary", new City("Calgary", new MyPoint(606, 29)));
        cities.put("Salt Lake City", new City("Salt Lake City", new MyPoint(609, 575)));
        cities.put("Helena", new City("Helena", new MyPoint(807, 320)));
        cities.put("Los Angeles", new City("Los Angeles", new MyPoint(342, 982)));
        cities.put("Las Vegas", new City("Las Vegas", new MyPoint(432, 799)));
        cities.put("Duluth", new City("Duluth", new MyPoint(1344, 353)));
        cities.put("Sault Ste Marie", new City("Sault Ste Marie", new MyPoint(1671, 257)));
        cities.put("Nashville", new City("Nashville", new MyPoint(1662, 865)));
        cities.put("Montreal", new City("Montreal", new MyPoint(2130, 125)));
        cities.put("Oklahoma City", new City("Oklahoma City", new MyPoint(1206, 898)));
        cities.put("Seattle", new City("Seattle", new MyPoint(234, 188)));
        cities.put("Santa Fe", new City("Santa Fe", new MyPoint(879, 874)));
        cities.put("Toronto", new City("Toronto", new MyPoint(1887, 332)));
        cities.put("Miami", new City("Miami", new MyPoint(1980, 1363)));
        cities.put("Portland", new City("Portland", new MyPoint(168, 308)));
        cities.put("Phoenix", new City("Phoenix", new MyPoint(618, 997)));
        cities.put("Dallas", new City("Dallas", new MyPoint(1272, 1111)));
        cities.put("Pittsburgh", new City("Pittsburgh", new MyPoint(1908, 551)));
        cities.put("Winnipeg", new City("Winnipeg", new MyPoint(1137, 77)));
        cities.put("Little Rock", new City("Little Rock", new MyPoint(1407, 949)));
        cities.put("Boston", new City("Boston", new MyPoint(2244, 392)));
        cities.put("Vancouver", new City("Vancouver", new MyPoint(267, 47)));
        cities.put("San Francisco", new City("San Francisco", new MyPoint(156, 755)));
        cities.put("Omaha", new City("Omaha", new MyPoint(1233, 542)));
        cities.put("Saint Louis", new City("Saint Louis", new MyPoint(1482, 722)));
        cities.put("Raleigh", new City("Raleigh", new MyPoint(1944, 802)));
        cities.put("Washington", new City("Washington", new MyPoint(2079, 641)));
        cities.put("Charleston", new City("Charleston", new MyPoint(1959, 1003)));
        return cities;
    }

    public static Map<Integer, Route> createRoute(Map<String, City> cities) {
        Map<Integer, Route> routes = new HashMap<>();
        routes.put(1, new Route(cities.get("Vancouver"), cities.get("Seattle"), 1, null, ANY, 1, 2));
        routes.put(2, new Route(cities.get("Vancouver"), cities.get("Seattle"), 1, null, ANY, 2, 1));
        routes.put(3, new Route(cities.get("Seattle"), cities.get("Portland"), 1, null, ANY, 3, 4));
        routes.put(4, new Route(cities.get("Seattle"), cities.get("Portland"), 1, null, ANY, 4, 3));
        routes.put(5, new Route(cities.get("Vancouver"), cities.get("Calgary"), 3, null, ANY, 5, -1));
        routes.put(6, new Route(cities.get("Seattle"), cities.get("Calgary"), 4, null, ANY, 6, -1));
        routes.put(7, new Route(cities.get("Calgary"), cities.get("Winnipeg"), 6, null, REEFER, 7, -1));
        routes.put(8, new Route(cities.get("Calgary"), cities.get("Helena"), 4, null, ANY, 8, -1));
        routes.put(9, new Route(cities.get("Seattle"), cities.get("Helena"), 6, null, BOX, 9, -1));
        routes.put(10, new Route(cities.get("Portland"), cities.get("Salt Lake City"), 6, null, PASSENGER, 10, -1));
        routes.put(11, new Route(cities.get("Portland"), cities.get("San Francisco"), 5, null, CABOOSE, 11, 12));
        routes.put(12, new Route(cities.get("Portland"), cities.get("San Francisco"), 5, null, FREIGHT, 12, 11));
        routes.put(13, new Route(cities.get("San Francisco"), cities.get("Salt Lake City"), 5, null, TANKER, 13, 14));
        routes.put(14, new Route(cities.get("San Francisco"), cities.get("Salt Lake City"), 5, null, REEFER, 14, 13));
        routes.put(15, new Route(cities.get("San Francisco"), cities.get("Los Angeles"), 3, null, REEFER, 15, 16));
        routes.put(16, new Route(cities.get("San Francisco"), cities.get("Los Angeles"), 3, null, BOX, 16, 15));
        routes.put(17, new Route(cities.get("Los Angeles"), cities.get("Las Vegas"), 2, null, ANY, 17, -1));
        routes.put(18, new Route(cities.get("Las Vegas"), cities.get("Salt Lake City"), 3, null, TANKER, 18, -1));
        routes.put(19, new Route(cities.get("Los Angeles"), cities.get("Phoenix"), 3, null, ANY, 19, -1));
        routes.put(20, new Route(cities.get("Los Angeles"), cities.get("El Paso"), 6, null, HOPPER, 20, -1));
        routes.put(21, new Route(cities.get("Phoenix"), cities.get("El Paso"), 3, null, ANY, 21, -1));
        routes.put(22, new Route(cities.get("Phoenix"), cities.get("Santa Fe"), 3, null, ANY, 22, -1));
        routes.put(23, new Route(cities.get("Santa Fe"), cities.get("El Paso"), 2, null, ANY, 23, -1));
        routes.put(24, new Route(cities.get("Phoenix"), cities.get("Denver"), 5, null, REEFER, 24, -1));
        routes.put(25, new Route(cities.get("Salt Lake City"), cities.get("Denver"), 3, null, BOX, 25, 26));
        routes.put(26, new Route(cities.get("Salt Lake City"), cities.get("Denver"), 3, null, COAL, 26, 25));
        routes.put(27, new Route(cities.get("Salt Lake City"), cities.get("Helena"), 3, null, FREIGHT, 27, -1));
        routes.put(28, new Route(cities.get("Helena"), cities.get("Winnipeg"), 4, null, PASSENGER, 28, -1));
        routes.put(29, new Route(cities.get("Helena"), cities.get("Duluth"), 6, null, TANKER, 29, -1));
        routes.put(30, new Route(cities.get("Helena"), cities.get("Omaha"), 5, null, COAL, 30, -1));
        routes.put(31, new Route(cities.get("Helena"), cities.get("Denver"), 4, null, CABOOSE, 31, -1));
        routes.put(32, new Route(cities.get("Denver"), cities.get("Omaha"), 4, null, FREIGHT, 32, -1));
        routes.put(33, new Route(cities.get("Denver"), cities.get("Kansas City"), 4, null, HOPPER, 33, 34));
        routes.put(34, new Route(cities.get("Denver"), cities.get("Kansas City"), 4, null, TANKER, 34, 33));
        routes.put(35, new Route(cities.get("Denver"), cities.get("Oklahoma City"), 4, null, COAL, 35, -1));
        routes.put(36, new Route(cities.get("Denver"), cities.get("Santa Fe"), 2, null, ANY, 36, -1));
        routes.put(37, new Route(cities.get("Oklahoma City"), cities.get("Kansas City"), 2, null, ANY, 37, 38));
        routes.put(38, new Route(cities.get("Oklahoma City"), cities.get("Kansas City"), 2, null, ANY, 38, 37));
        routes.put(39, new Route(cities.get("Oklahoma City"), cities.get("Little Rock"), 2, null, ANY, 39, -1));
        routes.put(40, new Route(cities.get("Oklahoma City"), cities.get("Dallas"), 2, null, ANY, 40, 41));
        routes.put(41, new Route(cities.get("Oklahoma City"), cities.get("Dallas"), 2, null, ANY, 41, 40));
        routes.put(42, new Route(cities.get("Oklahoma City"), cities.get("Santa Fe"), 3, null, PASSENGER, 42, -1));
        routes.put(43, new Route(cities.get("Oklahoma City"), cities.get("El Paso"), 5, null, BOX, 43, -1));
        routes.put(44, new Route(cities.get("El Paso"), cities.get("Dallas"), 4, null, COAL, 44, -1));
        routes.put(45, new Route(cities.get("El Paso"), cities.get("Houston"), 6, null, CABOOSE, 45, -1));
        routes.put(46, new Route(cities.get("Dallas"), cities.get("Houston"), 1, null, ANY, 46, 47));
        routes.put(47, new Route(cities.get("Dallas"), cities.get("Houston"), 1, null, ANY, 47, 46));
        routes.put(48, new Route(cities.get("Houston"), cities.get("New Orleans"), 2, null, ANY, 48, -1));
        routes.put(49, new Route(cities.get("Dallas"), cities.get("Little Rock"), 2, null, ANY, 49, -1));
        routes.put(50, new Route(cities.get("Omaha"), cities.get("Kansas City"), 1, null, ANY, 50, 51));
        routes.put(51, new Route(cities.get("Omaha"), cities.get("Kansas City"), 1, null, ANY, 51, 50));
        routes.put(52, new Route(cities.get("Kansas City"), cities.get("Saint Louis"), 2, null, PASSENGER, 52, 53));
        routes.put(53, new Route(cities.get("Kansas City"), cities.get("Saint Louis"), 2, null, FREIGHT, 53, 52));
        routes.put(54, new Route(cities.get("Omaha"), cities.get("Duluth"), 2, null, ANY, 54, 55));
        routes.put(55, new Route(cities.get("Omaha"), cities.get("Duluth"), 2, null, ANY, 55, 54));
        routes.put(56, new Route(cities.get("Duluth"), cities.get("Winnipeg"), 4, null, HOPPER, 56, -1));
        routes.put(57, new Route(cities.get("Duluth"), cities.get("Sault Ste Marie"), 3, null, ANY, 57, -1));
        routes.put(58, new Route(cities.get("Duluth"), cities.get("Toronto"), 6, null, FREIGHT, 58, -1));
        routes.put(59, new Route(cities.get("Duluth"), cities.get("Chicago"), 3, null, COAL, 59, -1));
        routes.put(60, new Route(cities.get("Sault Ste Marie"), cities.get("Winnipeg"), 6, null, ANY, 60, -1));
        routes.put(61, new Route(cities.get("Sault Ste Marie"), cities.get("Montreal"), 5, null, HOPPER, 61, -1));
        routes.put(62, new Route(cities.get("Sault Ste Marie"), cities.get("Toronto"), 2, null, ANY, 62, -1));
        routes.put(63, new Route(cities.get("Chicago"), cities.get("Saint Louis"), 2, null, CABOOSE, 63, 64));
        routes.put(64, new Route(cities.get("Chicago"), cities.get("Saint Louis"), 2, null, REEFER, 64, 63));
        routes.put(65, new Route(cities.get("Chicago"), cities.get("Omaha"), 4, null, PASSENGER, 65, -1));
        routes.put(66, new Route(cities.get("Chicago"), cities.get("Pittsburgh"), 3, null, HOPPER, 66, 67));
        routes.put(67, new Route(cities.get("Chicago"), cities.get("Pittsburgh"), 3, null, TANKER, 67, 66));
        routes.put(68, new Route(cities.get("Chicago"), cities.get("Toronto"), 4, null, REEFER, 68, -1));
        routes.put(69, new Route(cities.get("Pittsburgh"), cities.get("Toronto"), 2, null, ANY, 69, -1));
        routes.put(70, new Route(cities.get("Pittsburgh"), cities.get("Saint Louis"), 5, null, CABOOSE, 70, -1));
        routes.put(71, new Route(cities.get("Pittsburgh"), cities.get("Nashville"), 4, null, BOX, 71, -1));
        routes.put(72, new Route(cities.get("Pittsburgh"), cities.get("Raleigh"), 2, null, ANY, 72, -1));
        routes.put(73, new Route(cities.get("Pittsburgh"), cities.get("Washington"), 2, null, ANY, 73, -1));
        routes.put(74, new Route(cities.get("Pittsburgh"), cities.get("New York"), 2, null, REEFER, 74, 75));
        routes.put(75, new Route(cities.get("Pittsburgh"), cities.get("New York"), 2, null, CABOOSE, 75, 74));
        routes.put(76, new Route(cities.get("Montreal"), cities.get("Toronto"), 3, null, ANY, 76, -1));
        routes.put(77, new Route(cities.get("Montreal"), cities.get("New York"), 3, null, PASSENGER, 77, -1));
        routes.put(78, new Route(cities.get("Montreal"), cities.get("Boston"), 2, null, ANY, 78, 79));
        routes.put(79, new Route(cities.get("Montreal"), cities.get("Boston"), 2, null, ANY, 79, 78));
        routes.put(80, new Route(cities.get("Boston"), cities.get("New York"), 2, null, BOX, 80, 81));
        routes.put(81, new Route(cities.get("Boston"), cities.get("New York"), 2, null, COAL, 81, 80));
        routes.put(82, new Route(cities.get("New York"), cities.get("Washington"), 2, null, TANKER, 82, 83));
        routes.put(83, new Route(cities.get("New York"), cities.get("Washington"), 2, null, HOPPER, 83, 82));
        routes.put(84, new Route(cities.get("Raleigh"), cities.get("Washington"), 2, null, ANY, 84, 85));
        routes.put(85, new Route(cities.get("Raleigh"), cities.get("Washington"), 2, null, ANY, 85, 84));
        routes.put(86, new Route(cities.get("Raleigh"), cities.get("Atlanta"), 2, null, ANY, 86, 87));
        routes.put(87, new Route(cities.get("Raleigh"), cities.get("Atlanta"), 2, null, ANY, 87, 86));
        routes.put(88, new Route(cities.get("Raleigh"), cities.get("Nashville"), 3, null, HOPPER, 88, -1));
        routes.put(89, new Route(cities.get("Raleigh"), cities.get("Charleston"), 2, null, ANY, 89, -1));
        routes.put(90, new Route(cities.get("Saint Louis"), cities.get("Little Rock"), 2, null, ANY, 90, -1));
        routes.put(91, new Route(cities.get("Nashville"), cities.get("Saint Louis"), 2, null, ANY, 91, -1));
        routes.put(92, new Route(cities.get("Nashville"), cities.get("Little Rock"), 3, null, REEFER, 92, -1));
        routes.put(93, new Route(cities.get("Nashville"), cities.get("Atlanta"), 1, null, ANY, 93, -1));
        routes.put(94, new Route(cities.get("New Orleans"), cities.get("Little Rock"), 2, null, CABOOSE, 94, -1));
        routes.put(95, new Route(cities.get("New Orleans"), cities.get("Miami"), 6, null, COAL, 95, -1));
        routes.put(96, new Route(cities.get("New Orleans"), cities.get("Atlanta"), 4, null, BOX, 96, 97));
        routes.put(97, new Route(cities.get("New Orleans"), cities.get("Atlanta"), 4, null, TANKER, 97, 96));
        routes.put(98, new Route(cities.get("Atlanta"), cities.get("Charleston"), 2, null, ANY, 98, -1));
        routes.put(99, new Route(cities.get("Atlanta"), cities.get("Miami"), 5, null, PASSENGER, 99, -1));
        routes.put(100, new Route(cities.get("Charleston"), cities.get("Miami"), 4, null, FREIGHT, 100, -1));
        return routes;
    }
}
