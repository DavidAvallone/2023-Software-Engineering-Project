package Poker;

import java.util.ArrayList;

public class Match {

    private ArrayList<Player> players;
    private int starting_bet;

    private Round round;

    public Match(ArrayList<Player> players, int starting_bet){
        this.players = players;
        this.starting_bet = starting_bet;
        round = new Round(players, starting_bet);
    }

    public void playMatch(){
        while(!round.getGameOver()){
            // starting the round
            // get values from the servlet
            round.player_turn(0,"fold",0);
            round.update_round();
        }
    }
}
