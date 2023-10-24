package Poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Round {
    private ArrayList<Player> players;
    private ArrayList<Card> river;
    private Deck deck;

    int current_pot = 0;
    int starting_bet;
    public Round(ArrayList<Player> players, int starting_bet) {
        this.players = players;
        this.starting_bet = starting_bet;
        deck = new Deck();
        deck.shuffle();
    }

    public void sort_players(){
        Comparator<Player> byTurnOrder = Comparator.comparing(Player::getTurnOrder);// get turn order function of player

        // Sort the list of players using the custom comparator
        Collections.sort(players, byTurnOrder);
    }

    public void update_blinds(){
        Player p1 = players.get(0);
        p1.setSmallBlind(true);
        Player p2 = players.get(1);
        p2.setBigBlind(true);
        for(int i = 2; i < players.size();i++){
            Player temp = players.get(i);
            temp.setSmallBlind(false);
            temp.setBigBlind(false);
        }
    }

    public void deal_out(){
        sort_players();
        update_blinds();
        int all_drew = 0;
        for(int i = 0; i < players.size();i++){
            //add a card to players hand
            Player temp = players.get(i);
            temp.addCardToHand(deck.draw());
            if(i + 1 == players.size() && all_drew == 0){
                i = 0;
                all_drew++;
            }
        }
    }

    public int get_player_choice(Player p, int call, String choice){//returns an int incase the user wants to raise
        if (choice == "fold"){
            p.setFold(true);
        }
        if (choice == "call"){
            
        }
        return 0;
    }

    public void round1{
        boolean still_playing = true;
        int small_blind = starting_bet/2;
        int big_blind = starting_bet;
        int call;
        int raise;
        int player_turn = 0;
        while(still_playing){
            if(player_turn == 0) { // small blind goes first
                call = small_blind;
                //raise = get_player_choice(players.get(player_turn),call,choice);
            }

            // big blind
            // other players
        }
    }


}