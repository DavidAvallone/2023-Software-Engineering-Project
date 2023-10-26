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

    private int last_raise;
    private boolean gameover = false;
    private String[] player_status;
    private double[] player_bets;

    /**
     * This is the constructor for the round class: a round is the entire game of poker
     * @param players An arraylist of player objects to be played in this round of poker
     * @param starting_bet the starting bet for the big blind and small blind
     */
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
        last_raise = 1;
    }

    /**
     * This function sorts the players by the turn order
     */
    public void sort_players() {
        Comparator<Player> byTurnOrder = Comparator.comparing(Player::getTurnOrder);// get turn order function of player
        // Sort the list of players using the custom comparator
        Collections.sort(players, byTurnOrder);
    }

    /**
     * This updates the blinds depending on the turn order.
     */
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

    /**
     * this simulates the beginning of a poker match and sorts players
     * deals out the cards to each players hands
     */
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

    /**
     * a getter for the current pot
     * @return the current pot
     */
    public double getCurrent_pot(){
        return this.current_pot;
    }

    /**
     * This is a getter for the game over boolean
     * @return a boolean if the game is over
     */
    public boolean getGameOver(){
        return gameover;
    }

    /**
     * A setter to set the game to over
     * @param b the boolean to set the game to over or still in play
     */
    public void setGameover(boolean b){
        this.gameover = b;
    }

    /**
     * This function handles the logic for a players turn
     * @param p the player to play their turn
     * @param choice a string to determine what they will do on their turn
     * @param bet if they decide to raise this will be there bet
     */
    public void player_turn(int p, String choice, double bet) {
        Player current_player = players.get(p);
        if(current_player_turn != p){
            System.out.println("Not that players turn!");
            return;
        }
        if(player_status[p].equals("fold") || player_status[p].equals("all in")){
            return;//effectively skipping
        }
        switch (choice) {
            case "check":
                player_status[p] = "check";
                if(p < players.size()-1)
                    current_player_turn++;
                else if(p == players.size()-1)
                    current_player_turn = 0;
                return;
            case "fold":
                current_player.setFold(true);
                player_status[p] = "fold";
                if(p < players.size()-1)
                    current_player_turn++;
                else if(p == players.size()-1)
                    current_player_turn = 0;
                return;
            case "raise":
                if(bet > current_player.getCurrency()){
                    bet = current_player.getCurrency();
                    player_status[p] = "all in";
                    current_player.setCurrency(0.0);
                }
                else{
                    player_status[p] = "raise";
                    current_player.setCurrency(current_player.getCurrency() - bet);
                }

                this.current_pot += bet;
                this.current_bet += bet;
                current_player.setCurrentBet(current_bet);
                player_bets[p] = current_bet;
                last_raise = p;
                if(p < players.size()-1)
                    current_player_turn++;
                else if(p == players.size()-1)
                    current_player_turn = 0;
                return;
            case "all in":
                players.get(p).setAllIn(true);
                current_bet = current_player.getCurrency();
                current_player.setCurrentBet(current_player.getCurrency());
                current_pot += current_player.getCurrency();
                current_player.setCurrency(0.0);
                player_status[p] = "all in";
                player_bets[p] = current_player.getCurrentBet();
                if(p < players.size()-1)
                    current_player_turn++;
                else if(p == players.size()-1)
                    current_player_turn = 0;
                return;
            case "call":
                double call = current_bet - current_player.getCurrentBet();
                if(call > current_player.getCurrency()){
                    call = current_player.getCurrency();
                    player_status[p] = "all in";
                }
                else{
                    player_status[p] = "call";
                }
                current_player.setCurrency(current_player.getCurrency() - call);
                current_player.addtoCurrentBet(call);
                current_pot += call;
                player_bets[p] = current_bet;
                // current bet stays the same
                if(p < players.size()-1)
                    current_player_turn++;
                else if(p == players.size()-1)
                    current_player_turn = 0;
                return;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    /**
     * this function checks to see if the round is over
     * @return a boolean for whether or not the round is over
     */
    public boolean isRoundOver(){
        if(everyone_folded()){
            return true;
        }
        int active_players = 0;
        int folded_players = 0;
        // find folded players
        for(int i = 0; i < players.size();i++){
            if(player_status[i].equals("fold")){
                folded_players++;
            }
        }
        active_players = players.size() - folded_players;
        int i = last_raise + 1;

        if(i == players.size()){// last raise was the last person in the array
            i = 0;
        }
        int called = 1;
        //everyone checks or calls after a raise
        while(i != last_raise){
            if(i == players.size()){
                break;
            }
            if(player_status[i].equals("fold") || player_status[i].equals("all in")){
                if(i + 1 == players.size()){
                    i = 0;
                }
                else{
                    i++;// we skip these
                }
            }
            else if(player_status[i].equals("call") && player_bets[i] == player_bets[last_raise]){
                called++;
                if(i + 1 == players.size()){
                    i = 0;

                }
                else{
                    i++;// we skip these
                }
            }
        }
        if(active_players == called){
            return true;
        }
        return false;
    }

    /**
     * This function determines who will be the first person to play in the next round
     * @return an integer to signify whos turn it is
     */
    public int determine_first_player(){
        int player = 0;
        for(int i = 0; i < players.size();i++){
            if(!(player_status[i].equals("fold") || player_status[i].equals("all in"))){
                player = i;
                break;
            }
        }
        return player;
    }

    /**
     * This function updates the round to the next if the conditions are met.
     * round 1 to round 2 etc. until the game is over at round 4 or if the game ends prior
     */
    public void update_round(){
        if(everyone_folded()){
            gameover = true;
        }
        if(isRoundOver() && round_num == 1){// first round is over add 3 to the river
            current_player_turn = determine_first_player();
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
            current_player_turn = determine_first_player();
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

    /**
     * This function checks to see if everyone folded except one player
     * @return a boolean to see if the game is over or not
     */
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

    /**
     * a getter for the round number 1-4
     * @return this rounds number
     */
    public int getRound_num(){
        return this.round_num;
    }

    /**
     * the tostring method for a round in a human-readable way
     * @return humanreadable string of this round
     */
    public String toString(){
        String result = "Current Players: \n";
        for (Player player : players) {
            result += player + "\n";
        }
        result += "Round Info: \n";
        result += "Current Round: " + round_num + "\n";
        result += "Current Pot: " + current_pot + "\n";
        result += "Current Bet: " + current_bet + "\n";
        result += "Current River: \n";
        for(Card card : river){
            result += "" + card + "\n";
        }
        result += "Current Player Turn: " + (current_player_turn+1) + "\n";
        return result;
    }

}
