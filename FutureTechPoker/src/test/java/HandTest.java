import Poker.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


public class HandTest {

    @Test public void testOrderByRank(){
        ArrayList<Card> handCards = new ArrayList<>();
        Card card1 = new Card(1, 14);
        Card card2 = new Card(2, 10);
        Card card3 = new Card(1, 2);
        Card card4 = new Card(4, 13);
        Card card5 = new Card(3, 4);
        Card card6 = new Card(1, 5);
        Card card7 = new Card(2, 7);
        handCards.add(card1);
        handCards.add(card2);
        Hand hand = new Hand(handCards);
        hand.addRiverCard(card3);
        hand.addRiverCard(card4);
        hand.addRiverCard(card5);
        hand.addRiverCard(card6);
        hand.addRiverCard(card7);
        Hand.sortByRank(hand.getTotalCards());
        assertAll(
                () -> assertEquals(14, hand.getTotalCards().get(0).getRank()),
                () -> assertEquals(13, hand.getTotalCards().get(1).getRank()),
                () -> assertEquals(10, hand.getTotalCards().get(2).getRank()),
                () -> assertEquals(7, hand.getTotalCards().get(3).getRank()),
                () -> assertEquals(5, hand.getTotalCards().get(4).getRank()),
                () -> assertEquals(4, hand.getTotalCards().get(5).getRank()),
                () -> assertEquals(2, hand.getTotalCards().get(6).getRank())
        );
    }

    @Test public void testFindStraight() {
        ArrayList<Card> handCards = new ArrayList<>();
        Card card1 = new Card(1, 14);
        Card card2 = new Card(2, 12);
        Card card3 = new Card(1, 2);
        Card card4 = new Card(4, 13);
        Card card5 = new Card(3, 4);
        Card card6 = new Card(1, 11);
        Card card7 = new Card(2, 10);
        handCards.add(card1);
        handCards.add(card2);
        Hand hand = new Hand(handCards);
        hand.addRiverCard(card3);
        hand.addRiverCard(card4);
        hand.addRiverCard(card5);
        hand.addRiverCard(card6);
        hand.addRiverCard(card7);
        ArrayList<Card> bestHand = Hand.findStraight(hand.getTotalCards());
        assertAll(
                () -> assertEquals(14, bestHand.get(0).getRank()),
                () -> assertEquals(13, bestHand.get(1).getRank()),
                () -> assertEquals(12, bestHand.get(2).getRank()),
                () -> assertEquals(11, bestHand.get(3).getRank()),
                () -> assertEquals(10, bestHand.get(4).getRank())
        );
    }
    @Test public void testFindStraightAceLow(){
        ArrayList<Card> handCards = new ArrayList<>();
        Card card1 = new Card(1, 14);
        Card card2 = new Card(2, 12);
        Card card3 = new Card(1, 2);
        Card card4 = new Card(4, 3);
        Card card5 = new Card(3, 4);
        Card card6 = new Card(1, 5);
        Card card7 = new Card(2, 10);
        handCards.add(card1);
        handCards.add(card2);
        Hand hand = new Hand(handCards);
        hand.addRiverCard(card3);
        hand.addRiverCard(card4);
        hand.addRiverCard(card5);
        hand.addRiverCard(card6);
        hand.addRiverCard(card7);
        ArrayList<Card> bestHand = Hand.findStraight(hand.getTotalCards());
        assertAll(
                () -> assertEquals(5, bestHand.get(0).getRank()),
                () -> assertEquals(4, bestHand.get(1).getRank()),
                () -> assertEquals(3, bestHand.get(2).getRank()),
                () -> assertEquals(2, bestHand.get(3).getRank()),
                () -> assertEquals(14, bestHand.get(4).getRank())
        );
    }

}
