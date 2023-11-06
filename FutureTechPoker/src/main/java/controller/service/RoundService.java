package controller.service;

import Poker.*;
import java.util.ArrayList;

public class RoundService {

    public Round round;

    public void game_create(){
        Player p1 = new Player(0, "dave", 10000);
        Player p2 = new Player(1, "bolden", 10000);
        Player p3 = new Player(2, "alex", 10000);
        Player p4 = new Player(3, "neil", 10000);

        p1.setTurnOrder(0);
        p2.setTurnOrder(1);
        p3.setTurnOrder(2);
        p4.setTurnOrder(3);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        int starting_bet = 50;
        long seed = 42;
        this.round = new Round(players, starting_bet, seed);
    }
}
