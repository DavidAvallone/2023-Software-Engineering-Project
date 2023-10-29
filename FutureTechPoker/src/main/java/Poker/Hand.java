package Poker;

import java.util.ArrayList;
import java.util.List;

public class Hand{
    private ArrayList<Card> cards;

    /**
     * Hand Constructor
     * @param cards List of Cards to be added to the hand
     */
    public Hand(ArrayList<Card> cards){

        this.cards = cards;
    }

    /**
     * Hand Constructor
     */
    public Hand(){
        cards = new ArrayList<Card>();
    }

    /**
     * Gets the number of Cards in the Hand
     * @return int, number of Cards in the Hand
     */
    public int getHandSize(){
        return this.cards.size();
    }

    /**
     * Adds a Card to the Hand
     * @param c Card to be added to the Hand
     */
    public void add(Card c){
        cards.add(c);
    }

    /**
     * Getter for the cards attribute
     * @return List of Cards in the Hand
     */
    public List<Card> getCards(){
        return this.cards;
    }

    /**
     * Setter for the card attribute
     * @param cards List of Cards to be added to the Hand
     */
    public void setCards(ArrayList<Card> cards){
        this.cards = cards;
    }

    /**
     * Compares this Hand to another Hand
     * @param otherHand the Hand to be compared to
     * @param river List of river Cards
     * @return Greater than 0 if this Hand is better, less than 0 if the other hand is better, 0 if the hands are even
     */
    public int compareTo(Hand otherHand, List<Card> river){
        List<Card> totalCardsPlayer1 = new ArrayList<>();
        totalCardsPlayer1.addAll(this.getCards());
        totalCardsPlayer1.addAll(river);
        List<Card> totalCardsPlayer2 = new ArrayList<>();
        totalCardsPlayer2.addAll(otherHand.getCards());
        totalCardsPlayer2.addAll(river);
        int comparison = HandEvaluator.evaluate(totalCardsPlayer1) - HandEvaluator.evaluate(totalCardsPlayer2);
        if(comparison == 0){
            return HandEvaluator.tieBreaker(totalCardsPlayer1, totalCardsPlayer2);
        }
        return comparison;
    }

    /**
     * Hand class toString
     * @return String representation of a Hand
     */
    public String toString(){
        String totalString = "";
        for(int i = 0; i < this.cards.size(); i++){
            totalString += this.cards.get(i).toString() + " | ";
        }
        return totalString;
    }
}


