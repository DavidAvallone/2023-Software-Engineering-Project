import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


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
        deck = deck.shuffle();
    }

    public void sort_players(){
        Comparator<Player> byTurnOrder = Comparator.comparing(Player::getTurnOrder);// get turn order function of player

        // Sort the list of players using the custom comparator
        Collections.sort(players, byTurnOrder);
    }

    public void update_blinds(){
        Player p1 = players.get(0);
        p1.small_blind = true;
        Player p2 = players.get(1);
        p2.big_blind = true;
        for(int i = 2; i < players.size();i++){
            Player temp = players.get(i);
            temp.small_blind = false;
            temp.big_blind = false;
        }
    }

    public void deal_out(){
        sort_players();
        update_blinds();
        int all_drew = 0
        for(int i = 0; i < players.size();i++){
            //add a card to players hand
            Player temp = players.get(i);
            temp.hand.add(deck.draw());
            if(i + 1 == players.size() && all_drew == 0){
                i = 0;
                all_drew++;
            }
        }
    }

    public int get_player_choice(Player p, int call, string choice){//returns an int incase the user wants to raise
        if choice == "fold"{
            p.setFold(true);
        }
        if choice == "call"{
            
        }
    }

    public void take_round_bets(int round){
        boolean still_playing;
        // deal with the first round betting all others are the same
        if(round == 0){
            int small_blind = starting_bet/2;
            int big_blind = starting_bet;
            int call;
            int raise;
            int player_turn = 0;
            still_playing = true;
            while(still_playing){
                if(player_turn == 0) { // small blind goes first
                    call = small_blind;
                    raise = get_player_choice(players.get(player_turn),call);
                }

                // big blind
                // other players
            }
        }
        else{

        }
    }
    public Round match(){
        deal_out();// dealt out all cards to each player
        //take round one bets
        take_round_bets(1);
        // show the river
        take_round_bets(2);
        // update the river
        take_round_bets(3);
        //update the river
        take_round_bets(4);
        //evaluate who won and deal out winnings
        return;
    }

}