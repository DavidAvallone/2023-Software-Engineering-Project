package Poker;

import java.util.ArrayList;

public class HandEvaluator {
    private static final int NUM_SUITS = 4;

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

    public static ArrayList<Card> findHighestStraight(ArrayList<Card> cards){
        ArrayList<Card> straightCards = new ArrayList<>();
        ArrayList<Card> sortedCards = HandEvaluator.sortByRank(cards);
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
                else if(sortedCards.get(j).getRank() - sortedCards.get(j+1).getRank() != 0){
                    break;
                }
                if(inOrderCount == 5){
                    return straightCards;
                }
            }
        }
        return null;
    }

    public static int obtainMostCommonSuit(ArrayList<Card> cards){
        int[] suitCounts = new int[NUM_SUITS];
        int mostCommonSuit = 0;
        for (int i = 0; i < cards.size(); i++)
        {
            suitCounts[i] += 1;
            for(int j = 0; j < NUM_SUITS; j++){
                if(cards.get(i).getSuit() == j){
                }
            }
        }
        for(int j = 1; j < suitCounts.length; j++){
            if(suitCounts[mostCommonSuit] < suitCounts[j]){
                mostCommonSuit = j;
            }
        }
        return mostCommonSuit;
    }

    public static ArrayList<Card> findCardsOfSameSuit(ArrayList<Card> cards, int suit){

        for(int i = 0; )
    }

    public static ArrayList<Card> findFlush(ArrayList<Card> cards)
    {
        ArrayList<Card> flushCards = new ArrayList<>();
        ArrayList<Card> sortedCards = HandEvaluator.sortByRank(cards);
        for(int i = 0; i < NUM_SUITS; i++){
            if(flushCards.size() >= 5){
                return flushCards;
            }
            if()
        }
    }





}
