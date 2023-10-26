package Poker;

import java.util.ArrayList;
import java.util.List;

public class HandEvaluator {
    private static final int NUM_SUITS = 4;




    public static int evaluate(List<Card> cards){
        if(isRoyalFlush(cards)){return 9;}
        else if(isStraightFlush(cards)){return 8;}
        else if(isFourOfKind(cards)){return 7;}
        else if(isFullHouse(cards)){return 6;}
        else if(isFlush(cards)){return 5;}
        else if(isStraight(cards)){return 4;}
        else if(isThreeOfKind(cards)){return 3;}
        else if(isTwoPair(cards)){return 2;}
        else if(isPair(cards)){return 1;}
        else{return 0;}
    }


    public static boolean isPair(List<Card> cards){
        sortByRank(cards);
        for(int i = 0; i < cards.size()-1; i++){
            if(cards.get(i).getRank()==cards.get(i+1).getRank()){
                return true;
            }
        }
        return false;
    }
    public static int getHighestPair(List<Card> cards){
        sortByRank(cards);
        for(int i = 0; i < cards.size()-1; i++){
            if(cards.get(i).getRank()==cards.get(i+1).getRank()){
                return cards.get(i).getRank();
            }
        }
        return -1;
    }


    public static boolean isThreeOfKind(List<Card> cards){
        sortByRank(cards);
        for(int i = 0; i < cards.size()-2; i++){
            if(cards.get(i).getRank()==cards.get(i+1).getRank() && cards.get(i).getRank()==cards.get(i+2).getRank()){
                return true;
            }
        }
        return false;
    }
    public static int getHighestThreeOfKind(List<Card> cards){
        sortByRank(cards);
        for(int i = 0; i < cards.size()-2; i++){
            if(cards.get(i).getRank()==cards.get(i+1).getRank() && cards.get(i).getRank()==cards.get(i+2).getRank()){
                return cards.get(i).getRank();
            }
        }
        return -1;
    }

    public static boolean isTwoPair(List<Card> cards) {
        if (isPair(cards)) {
            List<Card> withoutRank = getCardsWithoutRank(cards, getHighestPair(cards));
            return isPair(withoutRank);

        }
        return false;
    }

    public static boolean isFullHouse(List<Card> cards) {
        if (isThreeOfKind(cards)) {
            List<Card> withoutThreeOfKind = getCardsWithoutRank(cards, getHighestThreeOfKind(cards));
            return isPair(withoutThreeOfKind);

        }
        return false;
    }

    public static boolean isFourOfKind(List<Card> cards){
        sortByRank(cards);
        for(int i = 0; i < cards.size()-3; i++){
            if(cards.get(i).getRank()==cards.get(i+1).getRank() && cards.get(i).getRank()==cards.get(i+2).getRank() && cards.get(i).getRank()==cards.get(i+3).getRank()){
                return true;
            }
        }
        return false;
    }

    public static int getHighestFourOfKind(List<Card> cards){
        sortByRank(cards);
        for(int i = 0; i < cards.size()-3; i++){
            if(cards.get(i).getRank()==cards.get(i+1).getRank() && cards.get(i).getRank()==cards.get(i+2).getRank() && cards.get(i).getRank()==cards.get(i+3).getRank()){
                return cards.get(i).getRank();
            }
        }
        return -1;
    }

    public static boolean isFlush(List<Card> cards)
    {
        int flushCount = 0;
        int suit = obtainMostCommonSuit(cards);
        for(int i = 0; i < cards.size(); i++){
            if(suit == cards.get(i).getSuit()){
                flushCount++;
            }
            if(flushCount >= 5){
                return true;
            }
        }
        return false;
    }

    public static boolean isStraight(List<Card> cards){
        List<Card> sortedCards = HandEvaluator.sortByRank(cards);
        int inOrderCount;
        for(int i = 0; i < sortedCards.size(); i++){
            inOrderCount = 1;
            for(int j = i; j < sortedCards.size()-1; j++){

                if(sortedCards.get(j).getRank() - sortedCards.get(j+1).getRank() == 1) {
                    inOrderCount++;

                }
                else if(sortedCards.get(j).getRank() - sortedCards.get(j+1).getRank() != 0){
                    break;
                }
                if (inOrderCount == 4 && sortedCards.get(j+1).getRank() == 2 && sortedCards.get(0).getRank() == 14) {
                    inOrderCount++;
                }
                if(inOrderCount == 5){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isStraightFlush(List<Card> cards){
        List<Card> sameSuitCards = getCardsOfSameSuit(cards,obtainMostCommonSuit(cards));
        if(isStraight(sameSuitCards)){
            return true;
        }
        return false;
    }
    public static boolean isRoyalFlush(List<Card> cards){
        List<Card> sameSuitCards = getCardsOfSameSuit(cards,obtainMostCommonSuit(cards));
        sortByRank(sameSuitCards);
        if(isStraight(sameSuitCards)){
            List<Card> straightFlushCards = findHighestStraight(sameSuitCards);
            if(straightFlushCards.get(0).getRank() == 14 && straightFlushCards.get(1).getRank() == 13)
                return true;
        }
        return false;
    }

    public static List<Card> getCardsWithoutRank(List<Card> cards, int rank){
        List<Card> newCards = new ArrayList<>();
        for(int i = 0; i < cards.size(); i++ ){
            if(cards.get(i).getRank() != rank){
                newCards.add(cards.get(i));
            }
        }
        return newCards;
    }

    public static List<Card> sortByRank(List<Card> cards){
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

    public static List<Card> findHighestStraight(List<Card> cards){
        List<Card> straightCards = new ArrayList<>();
        List<Card> sortedCards = HandEvaluator.sortByRank(cards);
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

    public static int obtainMostCommonSuit(List<Card> cards){
        int[] suitCounts = new int[NUM_SUITS];
        int mostCommonSuit = 0;
        for (int i = 0; i < cards.size(); i++)
        {
            for(int j = 0; j < NUM_SUITS  ; j++){
                if(cards.get(i).getSuit() == j){
                    suitCounts[j] += 1;
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

    public static List<Card> getCardsOfSameSuit(List<Card> cards, int suit){
        List<Card> sameSuitCards = new ArrayList<>();
        for(int i = 0; i < cards.size(); i++){
            if(cards.get(i).getSuit() == suit){
                sameSuitCards.add(cards.get(i));
            }
        }
        return sameSuitCards;
    }

    public static List<Card> findFlush(List<Card> cards)
    {
        int flushSuit = obtainMostCommonSuit(cards);
        List<Card> flushCards = getCardsOfSameSuit(cards, flushSuit);
        if(flushCards.size() >= 5){
            HandEvaluator.sortByRank(flushCards);
            return flushCards.subList(0, 4);
        }
        return null;
    }
}
