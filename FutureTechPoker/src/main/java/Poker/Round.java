package Poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Round {
    private ArrayList<Player> players;
    private ArrayList<Card> river;
    private Deck deck;
    private int round_num = 1;
    private double current_bet = 0;
    private double starting_bet;
    private double small_blind;
    private double big_blind;
    private double current_pot = 0;
    private int current_player_turn = 0;

    private boolean gameover = false;
    private String[] player_status;
    private double[] player_bets;

    public Round(ArrayList<Player> players, double starting_bet) {
        this.players = players;
        this.player_status = new String[players.size()];
        this.player_bets = new double[players.size()];
        for (int i = 0; i < players.size(); i++) {
            player_bets[i] = 0;
        }
        this.starting_bet = starting_bet;
        this.small_blind = starting_bet / 2;
        this.big_blind = starting_bet;
        deck = new Deck();
        deck.shuffle();
        deal_out();
        current_pot += small_blind + big_blind;
    }

    public void sort_players() {
        Comparator<Player> byTurnOrder = Comparator.comparing(Player::getTurnOrder);// get turn order function of player
        // Sort the list of players using the custom comparator
        Collections.sort(players, byTurnOrder);
    }

    public void update_blinds() {
        Player p1 = players.get(0);
        p1.setSmallBlind(true);
        p1.setCurrentBet(small_blind);
        player_bets[0] = small_blind;
        Player p2 = players.get(1);
        p2.setCurrentBet(big_blind);
        player_bets[1] = big_blind;
        p2.setBigBlind(true);
        for (int i = 2; i < players.size(); i++) {
            Player temp = players.get(i);
            temp.setSmallBlind(false);
            temp.setBigBlind(false);
        }
    }

    public void deal_out() {
        sort_players();
        update_blinds();
        int all_drew = 0;
        for (int i = 0; i < players.size(); i++) {
            //add a card to players hand
            Player temp = players.get(i);
            temp.addCardToHand(deck.draw());
            if (i + 1 == players.size() && all_drew == 0) {
                i = 0;
                all_drew++;
            }
        }
    }

    public boolean getGameOver(){
        return gameover;
    }

    public void setGameover(boolean b){
        this.gameover = b;
    }
    public void player_turn(int p, String choice, double bet) {
        Player current_player = players.get(p);
        this.current_player_turn = p;
        switch (choice) {
            case "check":
                player_status[p] = "check";
            case "fold":
                current_player.setFold(true);
                player_status[p] = "fold";
            case "raise":
                current_player.addtoCurrentBet(bet);
                this.current_bet += bet;
                player_status[p] = "raise";
            case "all in":
                players.get(p).setAllIn(true);
                this.current_bet.addtoCurrentBet(current_player.getCurrency());
                current_pot += current_player.getCurrency();
                player_status[p] = "all in";
            case "call":
                current_player.addtoCurrentBet(this.current_bet);
                current_pot += current_bet;
                // current bet stays the same
                player_status[p] = "call";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    public boolean isRoundOver () {
        // Count the number of active players and the number of players who have finished their actions.
        int activePlayers = 0;
        int finishedPlayers = 0;

        for (int i = 0; i < player_status.length; i++) {
            if (!player_status[i].equals("fold")) {
                activePlayers++;

                // If a player has called, checked, or gone all-in and their bet is equal to or greater than the current bet, they have finished their action.
                if (player_status[i].equals("call") || player_status[i].equals("check") || player_status[i].equals("all in")) {
                    if (player_bets[i] >= current_bet) {
                        finishedPlayers++;
                    }
                }
            }
        }
        // The round is over if all active players have finished their actions.
        return activePlayers > 0 && activePlayers == finishedPlayers;
    }

    public void update_round(){
        if(isRoundOver() && round_num == 1){// first round is over add 3 to the river
            round_num++;
            for(int i = 0; i < players.size(); i++){
                if(!player_status[i].equals("fold")){
                    player_status[i] = "";
                }
            }
            this.deck.draw();
            river.add(deck.draw());
            this.deck.draw();
            river.add(deck.draw());
            this.deck.draw();
            river.add(deck.draw());
        }
        else if(isRoundOver() && (round_num > 1 && round_num < 4)){ // round 2 and 3 add one to river
            round_num++;
            for(int i = 0; i < players.size(); i++){
                if(!player_status[i].equals("fold")){
                    player_status[i] = "";
                }
            }
            this.deck.draw();
            river.add(deck.draw());
        }
        if(isRoundOver() && round_num == 4){ // end of game
            gameover = true;
            // hand evaluations and make a function to update all of the players information in the database
        }
    }

}
