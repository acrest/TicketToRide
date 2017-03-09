package com.example.alec.phase_05.Client;

import android.os.Handler;
import android.os.Looper;

import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Shared.model.Chat;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.TrainCard;
import com.example.alec.phase_05.Shared.model.TrainType;

/**
 * Created by samuel on 3/8/17.
 */

public final class Demo {

    private ClientModel model;

    public static void startDemo() {
        new Demo().start();
    }

    private Demo() {
        model = ClientModel.getInstance();
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            testAddTrainCards();
                        }
                    });
                    Thread.sleep(5000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            testAddDestinationCards();
                        }
                    });
                    Thread.sleep(5000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            testRemoveDestinationCards();
                        }
                    });
                    Thread.sleep(5000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            testClaimRoute();
                        }
                    });
                    Thread.sleep(5000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            testPlayerPoints();
                        }
                    });
                    Thread.sleep(5000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            testAddChat();
                        }
                    });
                    Thread.sleep(5000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            testOtherAddTrainCards();
                        }
                    });
                    Thread.sleep(5000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            testOtherRemoveTrainCards();
                        }
                    });
                    Thread.sleep(5000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            testOtherAddDestinationCards();
                        }
                    });
                    Thread.sleep(5000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            testOtherRemoveDestinationCards();
                        }
                    });
                    Thread.sleep(5000);
                } catch(InterruptedException e) {
                    System.out.println("testing was interrupted");
                }
            }
        }).start();
    }

    private void testAddTrainCards() {
        System.out.println("testing add train cards");
        model.addTrainCard(new TrainCard(TrainType.FREIGHT));
        model.addTrainCard(new TrainCard(TrainType.TANKER));
        model.addTrainCard(new TrainCard(TrainType.LOCOMOTIVE));
        model.addTrainCard(new TrainCard(TrainType.REEFER));
    }

    private void testAddDestinationCards() {
        System.out.println("testing add destination cards");
        GameMap map = model.getGameMap();
        model.addDestinationCard(new DestinationCard(map.getCities().get("Chicago"), map.getCities().get("Santa Fe"), 9));
        model.addDestinationCard(new DestinationCard(map.getCities().get("Seattle"), map.getCities().get("New York"), 22));
        model.addDestinationCard(new DestinationCard(map.getCities().get("New York"), map.getCities().get("Atlanta"), 6));
    }

    private void testRemoveDestinationCards() {
        System.out.println("testing remove destination cards");
        model.removeDestinationCard(0);
    }

    private void testClaimRoute() {
        System.out.println("testing claim route");
        model.setRouteOwner(model.getCurrentPlayer().getName(), 1);
        Player player = findOtherPlayer();
        if(player == null) {
            System.out.println("there are no other players to test");
            return;
        }
        model.setRouteOwner(player.getName(), 2);
    }

    private void testPlayerPoints() {
        System.out.println("testing player points");
        model.addPlayerPoints(model.getCurrentPlayer().getName(), 3);
        Player player = findOtherPlayer();
        if(player == null) {
            System.out.println("there are no other players to test");
            return;
        }
        model.addPlayerPoints(player.getName(), 4);
    }

    private void testAddChat() {
        System.out.println("testing add chat");
        model.addChat(new Chat(model.getCurrentPlayer().getName(), 0, "test chat 2", ClientModel.getInstance().getCurrentPlayer().getColor()));
        Player player = findOtherPlayer();
        if(player == null) {
            System.out.println("there are no other players to test");
            return;
        }
        model.addChat(new Chat(player.getName(), 0, "test chat 1", ClientModel.getInstance().getCurrentPlayer().getColor()));
    }

    private void testOtherAddTrainCards() {
        System.out.println("testing other player add train card count");
        Player player = findOtherPlayer();
        if(player == null) {
            System.out.println("there are no other players to test");
            return;
        }
        model.addTrainCard(player.getName(), new TrainCard(TrainType.BOX));
        model.addTrainCard(player.getName(), new TrainCard(TrainType.BOX));
        model.addTrainCard(player.getName(), new TrainCard(TrainType.BOX));
        model.addTrainCard(player.getName(), new TrainCard(TrainType.BOX));
    }

    private void testOtherRemoveTrainCards() {
        System.out.println("testing other player remove train card count");
        Player player = findOtherPlayer();
        if(player == null) {
            System.out.println("there are no other players to test");
            return;
        }
        model.removeTrainCard(player.getName(), 0);
        model.removeTrainCard(player.getName(), 0);
    }

    private void testOtherAddDestinationCards() {
        System.out.println("testing other player add destination card count");
        Player player = findOtherPlayer();
        if(player == null) {
            System.out.println("there are no other players to test");
            return;
        }
        GameMap map = ClientModel.getInstance().getGameMap();
        model.addDestinationCard(player.getName(), new DestinationCard(map.getCities().get("Chicago"), map.getCities().get("Santa Fe"), 9));
        model.addDestinationCard(player.getName(), new DestinationCard(map.getCities().get("Chicago"), map.getCities().get("Santa Fe"), 9));
        model.addDestinationCard(player.getName(), new DestinationCard(map.getCities().get("Chicago"), map.getCities().get("Santa Fe"), 9));
        model.addDestinationCard(player.getName(), new DestinationCard(map.getCities().get("Chicago"), map.getCities().get("Santa Fe"), 9));
    }

    private void testOtherRemoveDestinationCards() {
        System.out.println("testing other player remove destination card count");
        Player player = findOtherPlayer();
        if(player == null) {
            System.out.println("there are no other players to test");
            return;
        }
        model.removeDestinationCard(player.getName(), 0);
        model.removeDestinationCard(player.getName(), 0);
    }

    private Player findOtherPlayer() {
        Player currentPlayer = model.getCurrentPlayer();
        for(int i = 0; i < model.getGameMaxPlayers(); ++i) {
            Player player = model.getPlayer(i);
            if(player == null) continue;
            if(player.getName().equals(currentPlayer.getName())) continue;
            return player;
        }
        return null;
    }

    private Player findPlayer(String... excludeNames) {
        for(int i = 0; i < model.getGameMaxPlayers(); ++i) {
            Player player = model.getPlayer(i);
            if(player == null) continue;
            if(match(player.getName(), excludeNames)) continue;
            return player;
        }
        return null;
    }

    private boolean match(String s, String[] strings) {
        for(String str : strings) {
            if(str.equals(s)) return true;
        }
        return false;
    }
}
