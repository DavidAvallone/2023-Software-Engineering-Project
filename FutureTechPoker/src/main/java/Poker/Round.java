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
    private int current_player_turn;

    private boolean gameover = false;
    private String[] player_status;
    private double[] player_bets;

    public Round(ArrayList<Player> players, double starting_bet) {
        this.players = players;
        this.player_status = new String[players.size()];
        this.player_bets = new double[players.size()];
        for (int i = 0; i < players.size(); i++) {
            player_bets[i] = 0;
            player_status[i] = "Playing";
        }
        this.starting_bet = starting_bet;
        this.small_blind = starting_bet / 2;
        this.big_blind = starting_bet;
        deck = new Deck();
        deck.shuffle();
        deal_out();
        current_pot += small_blind + big_blind;
        this.river = new ArrayList<Card>();
        current_player_turn = 0;
        this.current_bet = starting_bet;
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
        p1.setCurrency(p1.getCurrency()-small_blind);
        player_bets[0] = small_blind;
        Player p2 = players.get(1);
        p2.setCurrentBet(big_blind);
        p2.setCurrency(p2.getCurrency()-big_blind);
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

    public double getCurrent_pot(){
        return this.current_pot;
    }
    public boolean getGameOver(){
        return gameover;
    }

    public void setGameover(boolean b){
        this.gameover = b;
    }
    public void player_turn(int p, String choice, double bet) {
        Player current_player = players.get(p);
        if(current_player_turn != p){
            return;
        }
        if(player_status[p].equals("fold") || player_status[p].equals("all in")){
            return;//effectively skipping
        }
        switch (choice) {
            case "check":
                player_status[p] = "check";
                current_player_turn++;
                return;
            case "fold":
                current_player.setFold(true);
                player_status[p] = "fold";
                current_player_turn++;
                return;
            case "raise":
                current_player.addtoCurrentBet(bet);
                this.current_bet += bet;
                player_status[p] = "raise";
                current_player_turn++;
                return;
            case "all in":
                players.get(p).setAllIn(true);
                current_bet += current_player.getCurrency();
                current_player.addtoCurrentBet(current_player.getCurrency());
                current_pot += current_player.getCurrency();
                current_player.setCurrency(0.0);
                player_status[p] = "all in";
                current_player_turn++;
                return;
            case "call":
                double call = current_bet - current_player.getCurrentBet();
                current_player.setCurrency(current_player.getCurrency() - call);
                current_player.addtoCurrentBet(call);
                current_pot += call;
                // current bet stays the same
                player_status[p] = "call";
                current_player_turn++;
                return;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

//    public boolean isRoundOver () {
//        // Count the number of active players and the number of players who have finished their actions.
//        int activePlayers = 0;
//        int finishedPlayers = 0;
//
//        for (int i = 0; i < player_status.length; i++) {
//            if (!player_status[i].equals("fold")) {
//                activePlayers++;
//
//                // If a player has called, checked, or gone all-in and their bet is equal to or greater than the current bet, they have finished their action.
//                if (player_status[i].equals("call") || player_status[i].equals("check") || player_status[i].equals("all in")) {
//                    if (player_bets[i] >= current_bet) {
//                        finishedPlayers++;
//                    }
//                }
//            }
//        }
//        // The round is over if all active players have finished their actions.
//        return activePlayers > 0 && activePlayers == finishedPlayers;
//    }

    public boolean isRoundOver(){
        if(everyone_folded()){
            return true;
        }

        return false;
    }

    public void update_round(){
        if(everyone_folded()){
            gameover = true;
        }
        if(isRoundOver() && round_num == 1){// first round is over add 3 to the river
            this.round_num++;
            for(int i = 0; i < players.size(); i++){
                if(!(player_status[i].equals("fold") || player_status[i].equals("all in"))){
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
            this.round_num++;
            for(int i = 0; i < players.size(); i++){
                if(!(player_status[i].equals("fold") || player_status[i].equals("all in"))){
                    player_status[i] = "";
                }
            }
            this.deck.draw();
            river.add(deck.draw());
        }
        else if(isRoundOver() && round_num == 4){ // end of game
            gameover = true;
            // hand evaluations and make a function to update all of the players information in the database
        }
    }

    public boolean everyone_folded(){
        int num_players = players.size();
        int folded = 0;
        for(int i = 0; i < num_players; i++){
            if(player_status[i].equals("fold")){
                folded++;
            }
        }
        if(num_players - folded == 1){
            return true;
        }
        else{
            return false;
        }
    }

    public int getRound_num(){
        return this.round_num;
    }

    public String toString(){
        String result = "Current Players: \n";
        for (Player player : players) {
            result += player + "\n";
        }
        result += "Round Info: \n";
        result += "Current Pot: " + current_pot + "\n";
        result += "Current Bet: " + current_bet + "\n";
        result += "Current River: ";
        for(Card card : river){
            result += card + "\n";
        }
        result += "Current Player Turn: " + (current_player_turn+1) + "\n";
        return result;
    }

}
