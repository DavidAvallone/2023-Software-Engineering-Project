package Poker;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;// from database
    private String name; // from database
    private double currency;// from data
    private Hand hand;
    private int turnOrder;
    private boolean fold = false;
    private boolean allIn = false;
    private double currentBet = 0;
    private boolean smallBlind = false;
    private boolean bigBlind = false;
    private String status;


    public Player(int id, String name, double currency, int turnOrder){
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.hand = new Hand();
        this.status = null;
        this.turnOrder = turnOrder;
        this.currentBet = 0;
    }

    public void setStatus(String s){
        this.status = s;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Player otherPlayer = (Player) obj;
        return id == otherPlayer.id;
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isFold() {
        return fold;
    }

    public void setFold(boolean fold) {
        this.fold = fold;
    }

    public boolean isAllIn() {
        return allIn;
    }

    public void setAllIn(boolean allIn) {
        this.allIn = allIn;
    }

    public double getCurrency() {
        return currency;
    }

    public void setCurrency(double currency) {
        this.currency = currency;
    }

    public int getTurnOrder() {
        return turnOrder;
    }

    public void setTurnOrder(int turnOrder) {
        this.turnOrder = turnOrder;
    }

    public double getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(double currentBet) {
        this.currentBet = currentBet;
    }

    public boolean isSmallBlind() {
        return smallBlind;
    }

    public void setSmallBlind(boolean smallBlind) {
        this.smallBlind = smallBlind;
    }

    public boolean isBigBlind() {
        return bigBlind;
    }

    public void addtoCurrentBet(double bet){
        this.currentBet += bet;
    }
    public void setBigBlind(boolean bigBlind) {
        this.bigBlind = bigBlind;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player dealNewHand(Deck deck){

        this.hand = new Hand(new ArrayList<>(List.of(deck.draw(), deck.draw())));
        return this;
    }

    public void addCardToHand(Card newCard){
        this.hand.add(newCard);
    }

    /**
     * Compares this player's hand with another player's hand
     * @param otherPlayer the other player
     * @return int this player has a better hand if positive.
     * Other player has a better hand if negative. 0 if equal hands.
     */

    public int compareHandTo(Player otherPlayer, ArrayList<Card> river){
        List<Card> totalCardsPlayer1 = new ArrayList<>();
        List<Card> totalCardsPlayer2 = new ArrayList<>();
        totalCardsPlayer1.addAll(this.hand.getCards());
        totalCardsPlayer1.addAll(river);
        totalCardsPlayer2.addAll(otherPlayer.hand.getCards());
        totalCardsPlayer2.addAll(river);

        return HandEvaluator.evaluate(totalCardsPlayer1) - HandEvaluator.evaluate(totalCardsPlayer2);
    }


    public String toString(){

        return "Player: " + this.name + ", Current Bet: " + this.currentBet + ", Currency: " + this.currency
                + ", Folded? " + fold + ", Status: " + this.status;
    }
}
