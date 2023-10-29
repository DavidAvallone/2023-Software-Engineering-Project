package Poker;


import java.util.ArrayList;

import java.util.List;



public class HandEvaluator {

    private static final int NUM_SUITS = 4;

    /**
     * Determines the best kind of hand the
     * @param cards List of Cards to be evaluated
     * @return int representing the best type of hand
     */
    public static int evaluate(List<Card> cards) {
        if (isRoyalFlush(cards)) {
            return 9;
        } else if (isStraightFlush(cards)) {
            return 8;
        } else if (isFourOfKind(cards)) {
            return 7;
        } else if (isFullHouse(cards)) {
            return 6;
        } else if (isFlush(cards)) {
            return 5;
        } else if (isStraight(cards)) {
            return 4;
        } else if (isThreeOfKind(cards)) {
            return 3;
        } else if (isTwoPair(cards)) {
            return 2;
        } else if (isPair(cards)) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Compares two sets of Cards that have the same kind of hand
     * @param cards1 List of Cards to be compared
     * @param cards2 List of Cards to be compared
     * @return int, greater than 0 if cards1 is better than cards2, less than 0 if cards2 is better than cards1, 0 if they are equal
     */
    public static int tieBreaker(List<Card> cards1, List<Card> cards2){
        int evaluationScore = evaluate(cards1);
        if(evaluationScore == 9){
            return 0;
        }
        else if (evaluationScore == 8){
            return compareStraightFlushes(cards1, cards2);
        }
        else if (evaluationScore == 7){
            return compareFourOfKinds(cards1, cards2);
        }
        else if (evaluationScore == 6){
            return compareFullHouse(cards1, cards2);
        }
        else if (evaluationScore == 5){
            return compareFlush(cards1, cards2);
        }
        else if (evaluationScore == 4){
            return compareStraights(cards1, cards2);
        }
        else if (evaluationScore == 3){
            return compareThreeOfKind(cards1, cards2);
        }
        else if (evaluationScore == 2){
            return compareTwoPair(cards1, cards2);
        }
        else if (evaluationScore == 1){
            return comparePair(cards1, cards2);
        }
        else {
            return compareHighCard(cards1, cards2);
        }
    }

    /**
     * Compares two sets of cards when their best type of hand is a pair
     * @param cards1 List of Cards that have a pair best hand
     * @param cards2 List of Cards that have a pair best hand
     * @return int, greater than 0 if cards1 is better, less than 0 if cards2 is better, 0 if they are equal
     */
    public static int comparePair(List<Card> cards1, List<Card> cards2){
        int pair1 = getHighestPair(cards1);
        int pair2 = getHighestPair(cards2);
        if(pair1 == pair2) {
            List<Card> withoutPair1 = getCardsWithoutRank(cards1, pair1);
            List<Card> withoutPair2 = getCardsWithoutRank(cards2, pair2);
            return compareNextNumHighCards(withoutPair1, withoutPair2, 3);
        }
        return pair1 - pair2;
    }

    /**
     * Compares two sets of cards that have a two pair as their best hand
     * @param cards1 List of Cards that have a two pair as its best hand
     * @param cards2 List of Cards that have a two pair as its best hand
     * @return int, greater than 0 if cards1 is better, less than 0 if cards2 is better, 0 if they are equal
     */
    public static int compareTwoPair(List<Card> cards1, List<Card> cards2){
        int pair1 = getHighestPair(cards1);
        int pair2 = getHighestPair(cards2);
        if(pair1 == pair2) {
            List<Card> withoutPair1 = getCardsWithoutRank(cards1, pair1);
            List<Card> withoutPair2 = getCardsWithoutRank(cards2, pair2);
            int secondPair1 = getHighestPair(withoutPair1);
            int secondPair2 = getHighestPair(withoutPair2);
            if(secondPair1 == secondPair2){
                List<Card> withoutTwoPair1 = getCardsWithoutRank(withoutPair1, secondPair1);
                List<Card> withoutTwoPair2 = getCardsWithoutRank(withoutPair2, secondPair2);
                sortByRank(withoutTwoPair1);
                sortByRank(withoutTwoPair2);
                return withoutTwoPair1.get(0).getRank() - withoutTwoPair2.get(0).getRank();
            }
            else{
                return secondPair1 - secondPair2;
            }
        }
        else{
            return pair1 - pair2;
        }
    }

    /**
     * Compares two set of Cards that have a three of a kind best hand
     * @param cards1 List of Cards that have a three of a kind as its best hand
     * @param cards2 List of Cards that have a three of a kind as its best hand
     * @return int, greater than 0 if cards1 is better, less than 0 if cards2 is better, 0 if they are equal
     */
    public static int compareThreeOfKind(List<Card> cards1, List<Card> cards2){
        int threeOfKind1 = getHighestThreeOfKind(cards1);
        int threeOfKind2 = getHighestThreeOfKind(cards2);
        if(threeOfKind1 == threeOfKind2) {
            List<Card> withoutThreeOfKind1 = getCardsWithoutRank(cards1, threeOfKind1);
            List<Card> withoutThreeOfKind2 = getCardsWithoutRank(cards2, threeOfKind2);
            return compareNextNumHighCards(withoutThreeOfKind1, withoutThreeOfKind2, 2);
        }
        else{
            return threeOfKind1 - threeOfKind2;
        }
    }

    /**
     * Compares two sets of Cards that have a flush best hand
     * @param cards1 List of Cards that have a flush best hand
     * @param cards2 List of Cards that have a flush best hand
     * @return int, greater than 0 if cards1 is better, less than 0 if cards2 is better, 0 if they are equal
     */
    public static int compareFlush(List<Card> cards1, List<Card> cards2){
        List<Card> flush1 = findFlush(cards1);
        List<Card> flush2 = findFlush(cards2);
        return compareHighCard(flush1, flush2);
    }

    /**
     * Compares a specified number of next high cards in a set of Cards
     * @param cards1 List of Cards to compare
     * @param cards2 List of Cards to compare
     * @param numHighCardsToCompare number of high cards to be included in the comparison
     * @return  int, greater than 0 if cards1 is better, less than 0 if cards2 is better, 0 if they are equal
     */
    public static int compareNextNumHighCards(List<Card> cards1, List<Card> cards2, int numHighCardsToCompare){
        int numCardsToCompare = numHighCardsToCompare;
        List<Card> orderedCards1 = new ArrayList<>();
        orderedCards1.addAll(cards1);
        List<Card> orderedCards2 = new ArrayList<>();
        orderedCards2.addAll(cards2);
        sortByRank(orderedCards1);
        sortByRank(orderedCards2);

        for(int i = 0; i < numCardsToCompare; i++){
            if(orderedCards1.get(i).getRank() - orderedCards2.get(i).getRank() != 0){
                return orderedCards1.get(i).getRank() - orderedCards2.get(i).getRank();
            }
        }
        //Best 5 cards are same rank
        return 0;
    }

    /**
     * Compares high cards of two sets of cards
     * @param cards1 List of Cards to be compared
     * @param cards2 List of Cards to be compared
     * @return int, greater than 0 if cards1 is better, less than 0 if cards2 is better, 0
     */
    public static int compareHighCard(List<Card> cards1, List<Card> cards2){
        return compareNextNumHighCards(cards1, cards2, 5);
    }

    /**
     * Compares two sets of Cards with full house best hand
     * @param cards1 List of cards with full house best hand
     * @param cards2 List of cards with full house best hand
     * @return int, greater than 0 if cards1 is better, less than 0 if cards2 is better, 0
     */
    public static int compareFullHouse(List<Card> cards1, List<Card> cards2){
        int threeOfKind1 = getHighestThreeOfKind(cards1);
        int threeOfKind2 = getHighestThreeOfKind(cards2);
        if(threeOfKind1 == threeOfKind2) {
            List<Card> withoutThreeOfKind1 = getCardsWithoutRank(cards1, threeOfKind1);
            List<Card> withoutThreeOfKind2 = getCardsWithoutRank(cards2, threeOfKind2);
            int pair1 = getHighestPair(withoutThreeOfKind1);
            int pair2 = getHighestPair(withoutThreeOfKind2);
            return pair1 - pair2;
            }
        else{
            return threeOfKind1 - threeOfKind2;
        }
    }

    /**
     * comapres two sets of Cards with straight best hand
     * @param cards1 List of Cards with straight best hand
     * @param cards2 List of Cards with straight best hand
     * @return int, greater than 0 if cards1 is better, less than 0 if cards2 is better, 0
     */
    public static int compareStraights(List<Card> cards1, List<Card> cards2){
        List<Card> straight1 = findHighestStraight(cards1);
        List<Card> straight2 = findHighestStraight(cards2);
        sortByRank(straight1);
        sortByRank(straight2);
        return straight1.get(0).getRank() - straight2.get(0).getRank();

    }

    /**
     * Compares two sets of Cards with four of a kind best hand
     * @param cards1 List of Cards with four of a kind best hand
     * @param cards2 List of Cards with four of a kind best hand
     * @return int, greater than 0 if cards1 is better, less than 0 if cards2 is better, 0
     */
    public static int compareFourOfKinds(List<Card> cards1, List<Card> cards2){
        int fourOfKind1 = getHighestFourOfKind(cards1);
        int fourOfKind2 = getHighestFourOfKind(cards2);
        if(fourOfKind1 == fourOfKind2){
            List<Card> highCard1 = getCardsWithoutRank(cards1, fourOfKind1);
            List<Card> highCard2 = getCardsWithoutRank(cards2, fourOfKind2);
            sortByRank(highCard1);
            sortByRank(highCard2);
            return highCard1.get(0).getRank() - highCard2.get(0).getRank();
        }
        return fourOfKind1 - fourOfKind2;
    }

    /**
     * Compares two sets of Cards with straight flush best hand
     * @param cards1 List of Cards with best hand straight flush
     * @param cards2 List og Cards with best hand straight flush
     * @return int, greater than 0 if cards1 is better, less than 0 if cards2 is better, 0
     */
    public static int compareStraightFlushes(List<Card> cards1, List<Card> cards2){
        int suit1 = obtainMostCommonSuit(cards1);
        List<Card> flushCards1 = getCardsOfSameSuit(cards1, suit1);
        int suit2 = obtainMostCommonSuit(cards2);
        List<Card> flushCards2 = getCardsOfSameSuit(cards2, suit2);
        List<Card> straightFlush1 = findHighestStraight(flushCards1);
        List<Card> straightFlush2 = findHighestStraight(flushCards2);
        sortByRank(straightFlush1);
        sortByRank(straightFlush2);
        return straightFlush1.get(0).getRank() - straightFlush2.get(0).getRank();
    }

    /**
     * Determines if a set of Cards is a pair
     * @param cards List of Cards
     * @return true if is a pair, false otherwise
     */
    public static boolean isPair(List<Card> cards) {
        sortByRank(cards);
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getRank() == cards.get(i + 1).getRank()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the highest pair in a set of Cards
     * @param cards List of Cards with at least one pair
     * @return the rank of the pair, -1 if there is no pair
     */
    public static int getHighestPair(List<Card> cards) {
        sortByRank(cards);
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getRank() == cards.get(i + 1).getRank()) {
                return cards.get(i).getRank();
            }
        }
        return -1;
    }

    /**
     * Determine if the set of cards has a three of a kind
     * @param cards List of cards
     * @return true if there is a three of a kind, false otherwise
     */
    public static boolean isThreeOfKind(List<Card> cards) {
        sortByRank(cards);
        for (int i = 0; i < cards.size() - 2; i++) {
            if (cards.get(i).getRank() == cards.get(i + 1).getRank() && cards.get(i).getRank() == cards.get(i + 2).getRank()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the highest three of a kind in a List of Cards
     * @param cards List of Cards with at least one three of a kind
     * @return the rank of the highest three of a kind, -1 if there is no three of a kind
     */
    public static int getHighestThreeOfKind(List<Card> cards) {
        sortByRank(cards);
        for (int i = 0; i < cards.size() - 2; i++) {
            if (cards.get(i).getRank() == cards.get(i + 1).getRank() && cards.get(i).getRank() == cards.get(i + 2).getRank()) {
                return cards.get(i).getRank();
            }
        }
        return -1;
    }

    /**
     * Determines if the set of Cards is a two pair
     * @param cards List of Cards
     * @return true if it is a two pair, false otherwise
     */
    public static boolean isTwoPair(List<Card> cards) {
        if (isPair(cards)) {
            List<Card> withoutRank = getCardsWithoutRank(cards, getHighestPair(cards));
            return isPair(withoutRank);

        }
        return false;
    }

    /**
     * Determines if the set of Cards is a full house
     * @param cards List of Cards
     * @return true if the Cards have a full house, false otherwise
     */
    public static boolean isFullHouse(List<Card> cards) {
        if (isThreeOfKind(cards)) {
            List<Card> withoutThreeOfKind = getCardsWithoutRank(cards, getHighestThreeOfKind(cards));
            return isPair(withoutThreeOfKind);

        }
        return false;
    }

    /**
     * Determines if the set of Cards is a four of a kind
     * @param cards List of Cards
     * @return true if the Cards have a four of a kind, false otherwise
     */
    public static boolean isFourOfKind(List<Card> cards) {
        sortByRank(cards);
        for (int i = 0; i < cards.size() - 3; i++) {
            if (cards.get(i).getRank() == cards.get(i + 1).getRank() && cards.get(i).getRank() == cards.get(i + 2).getRank() && cards.get(i).getRank() == cards.get(i + 3).getRank()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the highest four of a Kind in a set of Cards
     * @param cards List of Cards with a four of a kind
     * @return the rank of the highest four of a kind, -1 if there is no four of a kind
     */
    public static int getHighestFourOfKind(List<Card> cards) {
        sortByRank(cards);
        for (int i = 0; i < cards.size() - 3; i++) {
            if (cards.get(i).getRank() == cards.get(i + 1).getRank() && cards.get(i).getRank() == cards.get(i + 2).getRank() && cards.get(i).getRank() == cards.get(i + 3).getRank()) {
                return cards.get(i).getRank();
            }
        }
        return -1;
    }

    /**
     * Determines if a set of Cards has a flush
     * @param cards list of Cards
     * @return true if the list of Cards contains a flush, false otherwise
     */
    public static boolean isFlush(List<Card> cards) {
        int flushCount = 0;
        int suit = obtainMostCommonSuit(cards);
        for (int i = 0; i < cards.size(); i++) {
            if (suit == cards.get(i).getSuit()) {
                flushCount++;
            }
            if (flushCount >= 5) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if the set of Cards contains a straight
     * @param cards List of Cards
     * @return true if the list of Cards contain a straight
     */
    public static boolean isStraight(List<Card> cards) {
        List<Card> sortedCards = HandEvaluator.sortByRank(cards);
        int inOrderCount;
        for (int i = 0; i < sortedCards.size(); i++) {
            inOrderCount = 1;
            for (int j = i; j < sortedCards.size() - 1; j++) {

                if (sortedCards.get(j).getRank() - sortedCards.get(j + 1).getRank() == 1) {
                    inOrderCount++;

                } else if (sortedCards.get(j).getRank() - sortedCards.get(j + 1).getRank() != 0) {
                    break;
                }
                if (inOrderCount == 4 && sortedCards.get(j + 1).getRank() == 2 && sortedCards.get(0).getRank() == 14) {
                    inOrderCount++;
                }
                if (inOrderCount == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines if the list of Cards contains a straight flush
     * @param cards List of Cards
     * @return true if the list of Cards contains a straight flush, false otherwise
     */
    public static boolean isStraightFlush(List<Card> cards) {
        List<Card> sameSuitCards = getCardsOfSameSuit(cards, obtainMostCommonSuit(cards));
        if (isStraight(sameSuitCards)) {
            return true;
        }
        return false;
    }

    /**
     * Determines if th list of Cards contains a royal flush
     * @param cards List of Cards
     * @return true if the list of Cards contains a royal flush, false otherwise
     */
    public static boolean isRoyalFlush(List<Card> cards) {
        List<Card> sameSuitCards = getCardsOfSameSuit(cards, obtainMostCommonSuit(cards));
        sortByRank(sameSuitCards);
        if (isStraight(sameSuitCards)) {
            List<Card> straightFlushCards = findHighestStraight(sameSuitCards);
            if (straightFlushCards.get(0).getRank() == 14 && straightFlushCards.get(1).getRank() == 13)
                return true;
        }
        return false;
    }

    /**
     * Filters out a specified rank from a list of Cards
     * @param cards List of Cards
     * @param rank int, the rank to be filtered out
     * @return The list of Cards without a specified rank
     */
    public static List<Card> getCardsWithoutRank(List<Card> cards, int rank) {
        List<Card> newCards = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getRank() != rank) {
                newCards.add(cards.get(i));
            }
        }
        return newCards;
    }

    /**
     * Sorts a list of Cards by its rank from greatest to least
     * @param cards List of Cards
     * @return A list of sorted Cards
     */
    public static List<Card> sortByRank(List<Card> cards) {
        if (cards.isEmpty() || cards.size() == 1) {
            return cards;
        }
        Card temp;
        boolean swapped = false;
        for (int i = 0; i < cards.size() - 1; i++) {
            for (int j = 0; j < cards.size() - i - 1; j++) {
                if (cards.get(j).compareTo(cards.get(j + 1)) < 1) {
                    temp = cards.get(j);
                    cards.set(j, cards.get(j + 1));
                    cards.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (swapped == false) {
                return cards;
            }
        }
        return cards;
    }

    /**
     * Finds the best possible straight in a set of Cards
     * @param cards List of Cards
     * @return A list of Cards containing the best possible straight
     */
    public static ArrayList<Card> findHighestStraight(List<Card> cards) {
        ArrayList<Card> straightCards = new ArrayList<>();
        List<Card> sortedCards = HandEvaluator.sortByRank(cards);

        int inOrderCount;
        for (int i = 0; i < sortedCards.size(); i++) {
            straightCards.clear();
            straightCards.add(cards.get(i));
            inOrderCount = 1;
            for (int j = i; j < sortedCards.size(); j++) {

                if (inOrderCount == 4 && sortedCards.get(j).getRank() == 2 && sortedCards.get(0).getRank() == 14) {
                    straightCards.add(sortedCards.get(0));
                    inOrderCount++;
                } else if (sortedCards.get(j).getRank() - sortedCards.get(j + 1).getRank() == 1) {
                    straightCards.add(sortedCards.get(j + 1));
                    inOrderCount++;
                } else if (sortedCards.get(j).getRank() - sortedCards.get(j + 1).getRank() != 0) {
                    break;
                }
                if (inOrderCount == 5) {
                    return straightCards;
                }
            }
        }
        return null;
    }

    /**
     * Determines the most common suit in a set of Cards
     * @param cards List of cards
     * @return int, the most common suit
     */
    public static int obtainMostCommonSuit(List<Card> cards) {
        int[] suitCounts = new int[NUM_SUITS];
        int mostCommonSuit = 0;
        for (int i = 0; i < cards.size(); i++) {
            for (int j = 0; j < NUM_SUITS; j++) {
                if (cards.get(i).getSuit() == j) {
                    suitCounts[j] += 1;
                }
            }
        }
        for (int j = 1; j < suitCounts.length; j++) {
            if (suitCounts[mostCommonSuit] < suitCounts[j]) {
                mostCommonSuit = j;
            }
        }
        return mostCommonSuit;
    }

    /**
     * Gets only the Cards with a specified suit from a list of Cards
     * @param cards List of Cards
     * @param suit int, suit
     * @return list of Cards with the specified suit
     */
    public static List<Card> getCardsOfSameSuit(List<Card> cards, int suit) {
        List<Card> sameSuitCards = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getSuit() == suit) {
                sameSuitCards.add(cards.get(i));

            }
        }
        return sameSuitCards;
    }

    /**
     * Gets the best possible flush in a set of Cards
     * @param cards List of Cards
     * @return List of Cards containing the best possible flush from the list of Cards
     */
    public static List<Card> findFlush(List<Card> cards)
    {
        int flushSuit = obtainMostCommonSuit(cards);
        List<Card> flushCards = getCardsOfSameSuit(cards, flushSuit);
        if(flushCards.size() >= 5){
            HandEvaluator.sortByRank(flushCards);
            return flushCards.subList(0, 5);
        }
        return null;
    }
}
