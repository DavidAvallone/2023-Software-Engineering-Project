package Poker;

import java.util.ArrayList;
import java.util.List;

public class Hand{
    private ArrayList<Card> cards;


    public Hand(ArrayList<Card> cards){

        this.cards = cards;
    }

    public int getHandSize(){
        return this.cards.size();
    }
    public Hand(){
        cards = new ArrayList<Card>();
    }

    public void add(Card c){
        cards.add(c);
    }
    public List<Card> getCards(){
        return this.cards;
    }

    public void setCards(ArrayList<Card> cards){
        this.cards = cards;
    }


    public int compareTo(Hand otherHand){
        return HandEvaluator.evaluate(this.cards) - HandEvaluator.evaluate(otherHand.getCards());
    }


    public String toString(){
        String totalString = "";
        for(int i = 0; i < this.cards.size(); i++){
            totalString += this.cards.get(i).toString() + " | ";
        }
        return totalString;
    }
}


