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
    private final double starting_bet;
    private double small_blind;
    private double big_blind;
    private double current_pot = 0;
    private int current_player_turn;
    private ArrayList<Player> tied;
    private int last_raise;
    private boolean gameover = false;
    private String[] player_status;
    private double[] player_bets;
    private Player winning_player = null;
    private boolean isTie = false;
    private boolean gameStarted;
    private long seed;

    /**
     * This is the constructor for the round class: a round is the entire game of poker
     * @param starting_bet the starting bet for the big blind and small blind
     * @param seed the seed for the game which is randomly generated
     */
    public Round(double starting_bet, Long seed) {
        this.seed = seed;
        this.players = new ArrayList<>();
        this.starting_bet = starting_bet;
        this.small_blind = starting_bet / 2;
        this.big_blind = starting_bet;
        deck = new Deck(seed);
        deck.shuffle();
        current_pot += small_blind + big_blind;
        this.river = new ArrayList<Card>();
        current_player_turn = 0;
        this.current_bet = starting_bet;
        last_raise = 1;
    }



    public void start_game(int small_blind, int big_blind){
        deal_out(small_blind, big_blind);
        gameStarted = true;
    }

    public void add_player(Player p, String status){
        p.setStatus(status);
        p.clearHand();
        p.setCurrentBet(0);
        this.players.add(p);
    }

    /**
     * this function is the getter for the river
     * @return the array list of cards
     */
    public ArrayList<Card> getRiver(){
        return river;
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
     * this the getter for the players list in the round
     * @return arraylist of the players in the round
     */
    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    public long getSeed(){
        return this.seed;
    }

    /**
     * This updates the blinds depending on the turn order.
     */
    public void update_blinds(int small, int big) {
        for (int i = 0; i < players.size(); i++) {
            Player temp = players.get(i);
            temp.setSmallBlind(false);
            temp.setBigBlind(false);
        }
        Player p1 = players.get(small);
        p1.setSmallBlind(true);
        p1.setCurrentBet(small_blind);
        p1.setCurrency(p1.getCurrency()-small_blind);

        Player p2 = players.get(big);
        p2.setCurrentBet(big_blind);
        p2.setCurrency(p2.getCurrency()-big_blind);
        p2.setBigBlind(true);
    }

    /**
     * this simulates the beginning of a poker match and sorts players
     * deals out the cards to each players hands
     */
    public void deal_out(int small_blind, int big_blind) {
        sort_players();
        update_blinds(small_blind, big_blind);
        int all_drew = 0;
        players.get(0).addCardToHand(deck.draw());
        for (int i = 0; i < players.size(); i++) {
            //add a card to players hand
            players.get(i).addCardToHand(deck.draw());
            if (i + 1 == players.size() && all_drew == 0) {
                i = 0;
                all_drew++;
            }
        }
    }

    /**
     * This function returns each players hand in a string
     * @return a human-readable way to print all hands
     */
    public String player_hands_toString(){
        String result = "Player Hands: \n";
        for(Player p : players){
            result += p.getName() + ": " + p.getHand() + "\n";
        }
        return result;
    }

    /**
     * a getter for the current pot
     * @return the current pot
     */
    public double getCurrent_pot(){
        return this.current_pot;
    }

    public double getCurrent_bet() {
        return current_bet;
    }

    /**
     * This is a getter for the game over boolean
     * @return a boolean if the game is over
     */
    public boolean getGameOver(){
        return gameover;
    }

    public Player getWinning_player(){
        return winning_player;
    }
    /**
     * A setter to set the game to over
     * @param b the boolean to set the game to over or still in play
     */
    public void setGameover(boolean b){
        this.gameover = b;
    }

    /**
     * this is a getter for the string array for the players status
     * @return string array of statuses
     */
    public String[] getPlayer_status(){
        this.player_status = new String[players.size()];
        for(int i = 0; i < players.size(); i++){
            this.player_status[i] = players.get(i).getStatus();
        }
        return this.player_status;
    }

    /**
     * Getter for the double array of player bets
     * @return double array of bets
     */
    public double[] getPlayer_bets(){
        this.player_bets = new double[players.size()];
        for(int i = 0; i < players.size(); i++){
            this.player_bets[i] = players.get(i).getCurrentBet();
        }
        return this.player_bets;
    }

    /**
     * This function handles the logic for when a player folds
     * @param p index in player arrays
     * @param current_player the player object
     */
    public void fold(int p, Player current_player){
        current_player.setFold(true);
        current_player.setStatus("fold");
        if(p < players.size()-1)
            current_player_turn++;
        else if(p == players.size()-1)
            current_player_turn = 0;
    }

    /**
     * This function handles the logic for when a player checks
     * @param p index in player arrays
     * @param current_player the player object
     */
    public void check(int p, Player current_player){
        current_player.setStatus("check");
        if(p < players.size()-1)
            current_player_turn++;
        else if(p == players.size()-1)
            current_player_turn = 0;
    }

    /**
     * This function handles the logic for when a player raise
     * @param p index in player arrays
     * @param current_player the player object
     * @param bet this is how much they want to raise by
     */
    public void raise(int p, Player current_player, double bet){
        boolean after_raise = false;
        boolean after_call = false;
        boolean beginning = false;
        double call = 0;
        int last_player;
        if(p == players.size() - 1){
            last_player = 0;
        }
        else if(p == 0){
            last_player = players.size() - 1;
        }
        else{
            last_player = p -1;
        }

        if(players.get(last_player).getStatus().equals("raise")){
            call = current_bet - current_player.getCurrentBet();
            after_raise = true;
        }
        if(players.get(last_player).getStatus().equals("call")){
            after_call = true;
        }
        if(players.get(last_player).getStatus().equals("not played")){
            beginning = true;
        }
        if(bet > current_player.getCurrency()){
            bet = current_player.getCurrency();
            current_player.setStatus("all in");
            current_player.setCurrency(0.0);
        }
        else{
            current_player.setStatus("raise");
            if(after_raise){
                current_player.setCurrency(current_player.getCurrency() - (call + bet));
            }
            else if(after_call){
                current_player.setCurrency(current_player.getCurrency() - bet);
            }
            else if(beginning){
                current_player.setCurrency(current_player.getCurrency() - bet);
            }
            else{
                current_player.setCurrency(current_player.getCurrency() - (current_bet + bet));
            }
        }

        this.current_bet += bet;
        current_player.setCurrentBet(current_bet);
        last_raise = p;
        if(p < players.size()-1)
            current_player_turn++;
        else if(p == players.size()-1)
            current_player_turn = 0;
        updatePot();
    }

    /**
     * This function updates the pot after each player turn
     */
    public void updatePot(){
        double pot = 0;
        for(int i = 0; i < players.size(); i++){
            pot += players.get(i).getCurrentBet();
        }
        this.current_pot = pot;
    }

    /**
     * This function handles the logic for when a player goes allin
     * @param p index in player arrays
     * @param current_player the player object
     */
    public void all_in(int p, Player current_player){
        players.get(p).setAllIn(true);
        current_bet = current_player.getCurrency();
        current_player.setCurrentBet(current_player.getCurrency());
        current_player.setCurrency(0.0);
        current_player.setStatus("all in");
        last_raise = p;
        if(p < players.size()-1)
            current_player_turn++;
        else if(p == players.size()-1)
            current_player_turn = 0;
        updatePot();
    }

    /**
     * This function handles the logic for when a player calls the raise
     * @param p index in player arrays
     * @param current_player the player object
     */
    public void call(int p, Player current_player){
        double call = current_bet - current_player.getCurrentBet();
        if(call > current_player.getCurrency()){
            call = current_player.getCurrency();
            current_player.setStatus("all in");
        }
        else{
            current_player.setStatus("call");
        }
        current_player.setCurrency(current_player.getCurrency() - call);
        current_player.addtoCurrentBet(call);
        // current bet stays the same
        if(p < players.size()-1)
            current_player_turn++;
        else if(p == players.size()-1)
            current_player_turn = 0;
        updatePot();
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
            throw new IllegalStateException("Wrong Player! Current Turn is : " + current_player_turn);
        }
        if(current_player.getStatus().equals("fold") || current_player.getStatus().equals("all in")){
            if(current_player_turn + 1 == players.size()){
                current_player_turn = 0;
            }
            else{
                current_player_turn++;
            }
            throw new IllegalStateException("Player Has Folded! Current Turn is : " + current_player_turn);
        }
        switch (choice) {
            case "check":
                check(p, current_player);
                return;
            case "fold":
                fold(p, current_player);
                return;
            case "raise":
                raise(p, current_player, bet);
                return;
            case "all in":
                all_in(p, current_player);
                return;
            case "call":
                call(p, current_player);
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
        for(Player p : players){
           if (p.getStatus().equals("playing"))
               return false;
        }

        int active_players = 0;
        int folded_players = 0;
        // find folded players
        for(int i = 0; i < players.size();i++){
            if(players.get(i).getStatus().equals("fold")){
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
            if(players.get(i).getStatus().equals("fold") || players.get(i).getStatus().equals("all in")){
                if(i + 1 == players.size()){
                    i = 0;
                }
                else{
                    i++;// we skip these
                }
            }
            else if(players.get(i).getStatus().equals("call") && players.get(i).getCurrentBet() == players.get(last_raise).getCurrentBet()){
                called++;
                if(i + 1 == players.size()){
                    i = 0;

                }
                else{
                    i++;// we skip these
                }
            }
            else{
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
            if(!(players.get(i).getStatus().equals("fold") || players.get(i).getStatus().equals("all in"))){
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
            winning_player = last_player_standing();

        }
        if(isRoundOver() && round_num == 1){// first round is over add 3 to the river
            current_player_turn = determine_first_player();
            this.round_num++;
            for(int i = 0; i < players.size(); i++){
                if(!(players.get(i).getStatus().equals("fold") || players.get(i).getStatus().equals("all in"))){
                    players.get(i).setStatus("not played");
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
                if(!(players.get(i).getStatus().equals("fold") || players.get(i).getStatus().equals("all in"))){
                    players.get(i).setStatus("not played");
                }
            }
            this.deck.draw();
            river.add(deck.draw());
        }
        else if(isRoundOver() && round_num == 4){ // end of game
            gameover = true;
            if(tie() == null || tie().size() == 1) {
                winning_player = who_won();
                winning_player.setCurrency(winning_player.getCurrency() + current_pot);
            }
            else{
                tied = tie();
                isTie = true;
                int split = tied.size();
                double split_pot = current_pot/split;
                for(Player p : tied){
                    p.setCurrency(p.getCurrency() + current_pot);
                }
            }
            // hand evaluations and make a function to update all of the players information in the database
        }
    }

    public ArrayList<Player> tie(){
        ArrayList<Player> winningPlayers = new ArrayList<>();
        int bestHandValue = -1;

        for (Player player1 : players) {
            if (player1.isFold()) {
                continue; // Skip folded players
            }
            Hand hand1 = player1.getHand();

            for (Player player2 : players) {
                if (player2.isFold() || player1 == player2) {
                    continue; // Skip folded players and self-comparisons
                }
                Hand hand2 = player2.getHand();

                // You need to implement a compareHands method in your Hand class
                int result = hand1.compareTo(hand2, river);

                if (result > 0 && result > bestHandValue) {
                    bestHandValue = result;
                    winningPlayers.clear();
                    winningPlayers.add(player1);
                } else if (result > 0 && result == bestHandValue) {
                    winningPlayers.add(player1);
                } else if (result < 0 && -result > bestHandValue) {
                    bestHandValue = -result;
                    winningPlayers.clear();
                    winningPlayers.add(player2);
                } else if (result < 0 && -result == bestHandValue) {
                    winningPlayers.add(player2);
                }
            }
        }

        if (bestHandValue == 0) {
            // No player had a better hand than any other; it's a tie
            return winningPlayers;
        } else {
            return null;
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
            if(players.get(i).getStatus().equals("fold")){
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
     * this function finds the last player to not fold incase everyone except one folded
     * @return the last standing player
     */
    public Player last_player_standing(){
        if(everyone_folded()){
            for(Player p : players){
                if(!p.isFold()){
                    return p;
                }
            }
        }
        else {
            return null;
        }
        return null;
    }

    /**
     * a getter for the round number 1-4
     * @return this rounds number
     */
    public int getRound_num(){
        return this.round_num;
    }

    /**
     * This function determines who won the game
     * @return the winning player object
     */
    public Player who_won(){
        Player winning_player = null;
        Hand highest_hand = null;
        int highest_value = 0;
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);

            if (player.isFold()) {
                continue; // Skip folded players
            }
            Hand hand = player.getHand();
            if( i == 0) {
                winning_player = player;
                highest_hand = hand;
                highest_value = hand.comparetoRiver(this.river);
            }
            else{
                if (hand.comparetoRiver(this.river) > highest_value){
                    winning_player = player;
                    highest_hand = hand;
                    highest_value = hand.comparetoRiver(this.river);
                }
            }
        }
        return  winning_player;
    }

    public String river_string(){
        String result = "| ";
        for(Card card : river){
            result += card + " | ";
        }
        return result;
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
        if(winning_player != null){
            result += "GAME OVER \n";
            result += "Winning Player: " + winning_player + "\n";
            result += winning_player.getHand() + "\n";
            result += "Other Player Hands: \n";
            result += player_hands_toString();
        }
        if(isTie){
            result += "GAME OVER \n TIE GAME \n Tied: ";

            for(Player p : tied){
                result += p + "\n";
            }
            result += "Player Hands: \n";
            result += player_hands_toString();
        }
        return result;
    }

    public boolean getTie() {
        return this.isTie;
    }

    public Integer getLast_raise() {
        return this.last_raise;
    }

    public Integer getCurrent_player() {
        return this.current_player_turn;
    }

    public double getStarting_bet() {
        return this.starting_bet;
    }

    public int getCurrent_player_turn(){
        return current_player_turn;
    }
    public void remove_player(Player p){
        int removed = p.getTurnOrder();
        for(Player player : players){
            if( p.getId() == player.getId()){
                players.remove(p);
                break;
            }
        }
        update_turn_order();
    }

    public void update_turn_order(){
        int i = 0;
        for(Player player : players){
            player.setTurnOrder(i);
            i++;
        }
    }

    public double getBigBlind(){
        return this.big_blind;
    }

    public double getSmallBlind(){
        return this.small_blind;
    }
}
