package Poker;

import java.util.ArrayList;
import java.util.List;

public class Hand implements Comparable<Hand>{
    private ArrayList<Card> cards;
    private ArrayList<Card> cardsAndRiver;

    public Hand(ArrayList<Card> cards){

        this.cards = cards;
        this.cardsAndRiver = new ArrayList<>(cards);
    }

    public Hand(){
        // nothing ?
    }

    public List<Card> getCards(){
        return this.cards;
    }

    public void setCards(ArrayList<Card> cards){
        this.cards = cards;
    }

    public ArrayList<Card> getTotalCards() {
        return cardsAndRiver;
    }

    public void setTotalCards(ArrayList<Card> cardsAndRiver) {
        this.cardsAndRiver = cardsAndRiver;
    }

    public void addRiverCard(Card card){
        this.cardsAndRiver.add(card);
    }
    public int evaluateHand(){
        Hand.sortByRank(this.getTotalCards());
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

    public static ArrayList<Card> findStraight(ArrayList<Card> cards){
        ArrayList<Card> straightCards = new ArrayList<>();
        ArrayList<Card> sortedCards = sortByRank(cards);
        int inOrderCount;
        for(int i = 0; i < sortedCards.size(); i++){
            straightCards.clear();
            straightCards.add(cards.get(i));
            inOrderCount = 1;
            for(int j = i; j < sortedCards.size(); j++){

                if(inOrderCount == 4 && sortedCards.get(j).getRank() == 2 && sortedCards.get(0).getRank() == 14) {
                    straightCards.add(sortedCards.get(0));
                    inOrderCount++;

                }
                else if(sortedCards.get(j).getRank() - sortedCards.get(j+1).getRank() == 1){
                    straightCards.add(sortedCards.get(j+1));
                    inOrderCount ++;
                }
                else if(sortedCards.get(j).getRank() - sortedCards.get(j+1).getRank() == 0){
                   //skip this card
                }
                else{
                    break;
                }
                if(inOrderCount == 5){
                    return straightCards;
                }
            }
        }
        return null;
    }



    public static ArrayList<Card> sortByRank(ArrayList<Card> cards){
        if (cards.isEmpty() || cards.size() == 1){
            return cards;
        }
        Card temp;
        boolean swapped = false;
        for(int i = 0; i < cards.size()-1; i++){
            for (int j = 0; j < cards.size()-i-1; j++){
                if(cards.get(j).compareTo(cards.get(j+1)) < 1) {
                    temp = cards.get(j);
                    cards.set(j, cards.get(j + 1));
                    cards.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (swapped == false){
                return cards;
            }

        }
        return cards;
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


