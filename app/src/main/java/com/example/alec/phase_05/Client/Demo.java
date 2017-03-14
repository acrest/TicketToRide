package com.example.alec.phase_05.Client;

import android.os.Handler;
import android.os.Looper;

import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Shared.model.IPlayer;
import com.example.alec.phase_05.Shared.model.OtherPlayer;
import com.example.alec.phase_05.Shared.model.Chat;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameMap;
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
                            testRemoveTrainCards();
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
                } catch (InterruptedException e) {
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

    private void testRemoveTrainCards() {
        System.out.println("testing remove train cards");
        model.removeTrainCard(0);
        model.removeTrainCard(0);
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
        model.setRouteOwner(model.getCurrentPlayer().getName(), 10);
        String player = findOtherPlayer();
        if (player == null) {
            System.out.println("there are no other players to test");
            return;
        }
        model.setRouteOwner(player, 95);
    }

    private void testPlayerPoints() {
        System.out.println("testing player points");
        model.setPlayerPoints(model.getCurrentPlayer().getName(), 10);
        String player = findOtherPlayer();
        if (player == null) {
            System.out.println("there are no other players to test");
            return;
        }
        model.setPlayerPoints(player, 4);
    }

    private void testAddChat() {
        System.out.println("testing add chat");
        model.addChat(new Chat(model.getCurrentPlayer().getName(), 0, "test chat 2", ClientModel.getInstance().getCurrentPlayer().getColor()));
        String player = findOtherPlayer();
        if (player == null) {
            System.out.println("there are no other players to test");
            return;
        }
        model.addChat(new Chat(player, 0, "test chat 1", ClientModel.getInstance().findPlayerByName(player).getColor()));
    }

    private void testOtherAddTrainCards() {
        System.out.println("testing other player add train card count");
        String player = findOtherPlayer();
        if (player == null) {
            System.out.println("there are no other players to test");
            return;
        }
        model.addTrainCard(player);
        model.addTrainCard(player);
        model.addTrainCard(player);
        model.addTrainCard(player);
    }

    private void testOtherRemoveTrainCards() {
        System.out.println("testing other player remove train card count");
        String player = findOtherPlayer();
        if (player == null) {
            System.out.println("there are no other players to test");
            return;
        }
        model.removeTrainCard(player);
        model.removeTrainCard(player);
    }

    private void testOtherAddDestinationCards() {
        System.out.println("testing other player add destination card count");
        String player = findOtherPlayer();
        if (player == null) {
            System.out.println("there are no other players to test");
            return;
        }
        GameMap map = ClientModel.getInstance().getGameMap();
        model.addDestinationCard(player);
        model.addDestinationCard(player);
        model.addDestinationCard(player);
        model.addDestinationCard(player);
    }

    private void testOtherRemoveDestinationCards() {
        System.out.println("testing other player remove destination card count");
        String player = findOtherPlayer();
        if (player == null) {
            System.out.println("there are no other players to test");
            return;
        }
        model.removeDestinationCard(player);
        model.removeDestinationCard(player);
    }

    private String findOtherPlayer() {
        for (int i = 0; i < model.getGameMaxPlayers(); ++i) {
            IPlayer player = model.getPlayer(i);
            String name = player == null ? null : player.getName();
            if (name != null && !name.equals(model.getCurrentPlayerName())) return name;
        }
        return null;
    }
}
