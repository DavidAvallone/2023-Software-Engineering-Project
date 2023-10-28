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


    public String toString(){
        String totalString = "";
        for(int i = 0; i < this.cards.size(); i++){
            totalString += this.cards.get(i).toString() + " | ";
        }
        return totalString;
    }
}


