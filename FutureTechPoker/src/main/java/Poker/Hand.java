package Poker;

import java.util.ArrayList;
import java.util.List;

public class Hand implements Comparable<Hand>{
    private ArrayList<Card> cards; //Size starts at 2 then increases to 7 by end of round


    public Hand(ArrayList<Card> cards){
        this.cards = cards;
    }

    public List<Card> getCards(){
        return this.cards;
    }

    public void setCards(ArrayList<Card> cards){
        this.cards = cards;
    }

    public int evaluateHand(){
    /*
    Royal Flush = 9
    Straight Flush = 8
    Four of a Kind = 7
    Full House = 6
    Flush = 5
    Straight = 4
    Three of a Kind = 3
    Two Pair = 2
    One pair = 1
    High Card = 0
     */
    return 0;
    }

    @Override public int compareTo(Hand otherHand){
        return this.evaluateHand() - otherHand.evaluateHand();
    }
    public String toString(){
        String totalString = "";
        for(int i = 0; i < this.cards.size(); i++){
            totalString = totalString + this.cards.get(i).toString();
        }
        return totalString;
    }
}


