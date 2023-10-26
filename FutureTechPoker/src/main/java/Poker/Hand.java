package Poker;

import java.util.ArrayList;
import java.util.List;

public class Hand{
    private ArrayList<Card> cards;


    public Hand(ArrayList<Card> cards){

        this.cards = cards;
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



/*
    @Override public int compareTo(Hand otherHand, List<Card> card){
        return HandEvaluator.evaluate() - HandEvaluator.evaluate();
    }

 */
    public String toString(){
        String totalString = "";
        for(int i = 0; i < this.cards.size(); i++){
            totalString = totalString + this.cards.get(i).toString();
        }
        return totalString;
    }
}


