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
    private ArrayList<Player> tied;
    private int last_raise;
    private boolean gameover = false;
    private String[] player_status;
    private double[] player_bets;
    private Player winning_player = null;
    boolean isTie = false;

    /**
     * This is the constructor for the round class: a round is the entire game of poker
     * @param players An arraylist of player objects to be played in this round of poker
     * @param starting_bet the starting bet for the big blind and small blind
     */
    public Round(double starting_bet, Long seed) {
        this.players = new ArrayList<>();
        this.player_status = new String[players.size()];
        this.player_bets = new double[players.size()];
        for (int i = 0; i < players.size(); i++) {
            player_bets[i] = 0;
            player_status[i] = "Playing";
        }
        this.starting_bet = starting_bet;
        this.small_blind = starting_bet / 2;
        this.big_blind = starting_bet;
        deck = new Deck(seed);
        deck.shuffle();
        deal_out();
        current_pot += small_blind + big_blind;
        this.river = new ArrayList<Card>();
        current_player_turn = 0;
        this.current_bet = starting_bet;
        last_raise = 1;
    }

    public void add_player(Player p){
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
        players.get(0).addCardToHand(deck.draw());
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
        return this.player_status;
    }

    /**
     * Getter for the double array of player bets
     * @return double array of bets
     */
    public double[] getPlayer_bets(){
        return this.player_bets;
    }

    /**
     * This function handles the logic for when a player folds
     * @param p index in player arrays
     * @param current_player the player object
     */
    public void fold(int p, Player current_player){
        current_player.setFold(true);
        player_status[p] = "fold";
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
        player_status[p] = "check";
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

        if(player_status[last_player].equals("raise")){
            call = current_bet - current_player.getCurrentBet();
            after_raise = true;
        }
        if(player_status[last_player].equals("call")){
            after_call = true;
        }
        if(player_status[last_player].equals("not played")){
            beginning = true;
        }
        if(bet > current_player.getCurrency()){
            bet = current_player.getCurrency();
            player_status[p] = "all in";
            current_player.setCurrency(0.0);
        }
        else{
            player_status[p] = "raise";
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
        player_bets[p] = current_bet;
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
            pot += player_bets[i];
        }
        current_pot = pot;
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
        player_status[p] = "all in";
        player_bets[p] = current_player.getCurrentBet();
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
            player_status[p] = "all in";
        }
        else{
            player_status[p] = "call";
        }
        current_player.setCurrency(current_player.getCurrency() - call);
        current_player.addtoCurrentBet(call);
        player_bets[p] = current_bet;
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
        if(player_status[p].equals("fold") || player_status[p].equals("all in")){
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
            winning_player = last_player_standing();

        }
        if(isRoundOver() && round_num == 1){// first round is over add 3 to the river
            current_player_turn = determine_first_player();
            this.round_num++;
            for(int i = 0; i < players.size(); i++){
                if(!(player_status[i].equals("fold") || player_status[i].equals("all in"))){
                    player_status[i] = "not played";
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
                    player_status[i] = "not played";
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

        for (int i = 0; i < players.size(); i++) {
            Player player1 = players.get(i);

            if (player1.isFold()) {
                continue; // Skip folded players
            }

            Hand hand1 = player1.getHand();

            for (int j = i + 1; j < players.size(); j++) {
                Player player2 = players.get(j);

                if (player2.isFold()) {
                    continue; // Skip folded players
                }

                Hand hand2 = player2.getHand();

                // You need to implement a compareHands method in your Hand class
                int result = hand1.compareTo(hand2, river);

                if (result > 0) {
                    winning_player = player1;
                } else if (result < 0) {
                    winning_player = player2;
                }
            }
        }
        return winning_player;
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
}
