import Poker.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class HandEvaluatorTest {

    @Test public void testIsPairFalse(){
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(1, 14);
        Card card2 = new Card(1, 10);
        Card card3 = new Card(1, 2);
        Card card4 = new Card(4, 13);
        Card card5 = new Card(1, 4);
        Card card6 = new Card(1, 5);
        Card card7 = new Card(2, 7);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertFalse(HandEvaluator.isPair(cards));
    }

    @Test public void testIsPairTrue(){
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(1, 14);
        Card card2 = new Card(1, 10);
        Card card3 = new Card(1, 2);
        Card card4 = new Card(4, 13);
        Card card5 = new Card(1, 4);
        Card card6 = new Card(1, 7);
        Card card7 = new Card(2, 2);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertTrue(HandEvaluator.isPair(cards));
    }

    @Test public void testIsThreeOfKindFalse(){
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(1, 14);
        Card card2 = new Card(1, 10);
        Card card3 = new Card(1, 2);
        Card card4 = new Card(4, 13);
        Card card5 = new Card(1, 4);
        Card card6 = new Card(1, 7);
        Card card7 = new Card(2, 2);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertFalse(HandEvaluator.isThreeOfKind(cards));
    }

    @Test public void testIsThreeOfKindTrue(){
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(1, 14);
        Card card2 = new Card(1, 7);
        Card card3 = new Card(1, 2);
        Card card4 = new Card(4, 7);
        Card card5 = new Card(1, 4);
        Card card6 = new Card(1, 7);
        Card card7 = new Card(2, 2);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertTrue(HandEvaluator.isThreeOfKind(cards));
    }
    @Test public void testIsTwoPairNoPair() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(1, 14);
        Card card2 = new Card(1, 9);
        Card card3 = new Card(1, 3);
        Card card4 = new Card(4, 8);
        Card card5 = new Card(1, 4);
        Card card6 = new Card(1, 7);
        Card card7 = new Card(2, 2);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertFalse(HandEvaluator.isTwoPair(cards));
    }
    @Test public void testIsTwoPairOnePair() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(1, 14);
        Card card2 = new Card(1, 7);
        Card card3 = new Card(1, 3);
        Card card4 = new Card(4, 7);
        Card card5 = new Card(1, 4);
        Card card6 = new Card(1, 7);
        Card card7 = new Card(2, 2);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertFalse(HandEvaluator.isTwoPair(cards));
    }
    @Test public void testIsTwoPairTrue() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(1, 14);
        Card card2 = new Card(3, 14);
        Card card3 = new Card(1, 3);
        Card card4 = new Card(4, 7);
        Card card5 = new Card(1, 4);
        Card card6 = new Card(1, 7);
        Card card7 = new Card(2, 2);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertTrue(HandEvaluator.isTwoPair(cards));
    }
    @Test public void testIsFullHouseNoPair() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(1, 14);
        Card card2 = new Card(3, 14);
        Card card3 = new Card(1, 3);
        Card card4 = new Card(4, 14);
        Card card5 = new Card(1, 4);
        Card card6 = new Card(1, 7);
        Card card7 = new Card(2, 2);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertFalse(HandEvaluator.isFullHouse(cards));
    }
    @Test public void testIsFullHouseNoThreeOfKind() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(1, 14);
        Card card2 = new Card(3, 14);
        Card card3 = new Card(1, 3);
        Card card4 = new Card(4, 2);
        Card card5 = new Card(1, 4);
        Card card6 = new Card(1, 7);
        Card card7 = new Card(2, 2);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertFalse(HandEvaluator.isFullHouse(cards));
    }
    @Test public void testIsFullHouseNoPairOrThreeOfKind() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(1, 14);
        Card card2 = new Card(3, 13);
        Card card3 = new Card(1, 3);
        Card card4 = new Card(4, 5);
        Card card5 = new Card(1, 4);
        Card card6 = new Card(1, 7);
        Card card7 = new Card(2, 2);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertFalse(HandEvaluator.isFullHouse(cards));
    }
    @Test public void testIsFullHouseTrue() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(1, 14);
        Card card2 = new Card(3, 5);
        Card card3 = new Card(1, 13);
        Card card4 = new Card(4, 5);
        Card card5 = new Card(1, 4);
        Card card6 = new Card(3, 13);
        Card card7 = new Card(2, 5);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertTrue(HandEvaluator.isFullHouse(cards));
    }
    @Test public void testIsFourOfKindFalse() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(1, 14);
        Card card2 = new Card(3, 5);
        Card card3 = new Card(1, 13);
        Card card4 = new Card(4, 5);
        Card card5 = new Card(1, 4);
        Card card6 = new Card(3, 13);
        Card card7 = new Card(2, 5);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertFalse(HandEvaluator.isFourOfKind(cards));
    }
    @Test public void testIsFourOfKindTrue() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(2, 13);
        Card card2 = new Card(3, 5);
        Card card3 = new Card(1, 13);
        Card card4 = new Card(4, 13);
        Card card5 = new Card(1, 3);
        Card card6 = new Card(3, 13);
        Card card7 = new Card(2, 5);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertTrue(HandEvaluator.isFourOfKind(cards));
    }
    @Test public void testIsFlushFalse() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(2, 13);
        Card card2 = new Card(3, 5);
        Card card3 = new Card(3, 13);
        Card card4 = new Card(4, 13);
        Card card5 = new Card(3, 3);
        Card card6 = new Card(3, 13);
        Card card7 = new Card(2, 5);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertFalse(HandEvaluator.isFlush(cards));
    }
    @Test public void testIsFlushTrue() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(3, 4);
        Card card2 = new Card(3, 5);
        Card card3 = new Card(1, 11);
        Card card4 = new Card(3, 13);
        Card card5 = new Card(3, 3);
        Card card6 = new Card(3, 12);
        Card card7 = new Card(2, 5);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertTrue(HandEvaluator.isFlush(cards));
    }

    @Test public void testIsStraightFalse() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(3, 4);
        Card card2 = new Card(3, 5);
        Card card3 = new Card(1, 11);
        Card card4 = new Card(3, 13);
        Card card5 = new Card(3, 10);
        Card card6 = new Card(3, 12);
        Card card7 = new Card(2, 5);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertFalse(HandEvaluator.isStraight(cards));
    }
    @Test public void testIsStraightTrue() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(3, 14);
        Card card2 = new Card(3, 5);
        Card card3 = new Card(1, 11);
        Card card4 = new Card(3, 13);
        Card card5 = new Card(3, 10);
        Card card6 = new Card(3, 12);
        Card card7 = new Card(2, 5);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertTrue(HandEvaluator.isStraight(cards));
    }

    @Test public void testIsStraightTrueAceLow() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(3, 14);
        Card card2 = new Card(3, 5);
        Card card3 = new Card(1, 2);
        Card card4 = new Card(3, 3);
        Card card5 = new Card(3, 4);
        Card card6 = new Card(3, 12);
        Card card7 = new Card(2, 5);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertTrue(HandEvaluator.isStraight(cards));
    }
    @Test public void testIsStraightFlushFalseNoStraight() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(3, 14);
        Card card2 = new Card(3, 5);
        Card card3 = new Card(1, 2);
        Card card4 = new Card(3, 5);
        Card card5 = new Card(3, 4);
        Card card6 = new Card(3, 12);
        Card card7 = new Card(2, 5);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertFalse(HandEvaluator.isStraightFlush(cards));
    }
    @Test public void testIsStraightFlushFalseNoFlush() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(2, 14);
        Card card2 = new Card(3, 5);
        Card card3 = new Card(1, 2);
        Card card4 = new Card(3, 3);
        Card card5 = new Card(0, 4);
        Card card6 = new Card(3, 12);
        Card card7 = new Card(2, 5);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertFalse(HandEvaluator.isStraightFlush(cards));
    }
    @Test public void testIsStraightFlushTrue() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(3, 14);
        Card card2 = new Card(1, 5);
        Card card3 = new Card(3, 2);
        Card card4 = new Card(3, 3);
        Card card5 = new Card(3, 4);
        Card card6 = new Card(2, 12);
        Card card7 = new Card(3, 5);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertTrue(HandEvaluator.isStraightFlush(cards));
    }

    @Test public void testIsRoyalFlushFalse() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(3, 14);
        Card card2 = new Card(1, 5);
        Card card3 = new Card(3, 2);
        Card card4 = new Card(3, 3);
        Card card5 = new Card(0, 4);
        Card card6 = new Card(2, 12);
        Card card7 = new Card(3, 5);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertFalse(HandEvaluator.isRoyalFlush(cards));
    }

    @Test public void testIsRoyalFlushFalseNoRoyal() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(3, 14);
        Card card2 = new Card(1, 5);
        Card card3 = new Card(3, 10);
        Card card4 = new Card(3, 13);
        Card card5 = new Card(3, 4);
        Card card6 = new Card(3, 13);
        Card card7 = new Card(0, 11);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertFalse(HandEvaluator.isRoyalFlush(cards));
    }
    @Test public void testIsRoyalFlushFalseNoFlush() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(3, 14);
        Card card2 = new Card(1, 5);
        Card card3 = new Card(3, 10);
        Card card4 = new Card(0, 13);
        Card card5 = new Card(3, 4);
        Card card6 = new Card(2, 12);
        Card card7 = new Card(0, 11);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertFalse(HandEvaluator.isRoyalFlush(cards));
    }
    @Test public void testIsRoyalFlushTrue() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(3, 14);
        Card card2 = new Card(1, 5);
        Card card3 = new Card(3, 10);
        Card card4 = new Card(3, 13);
        Card card5 = new Card(3, 4);
        Card card6 = new Card(3, 12);
        Card card7 = new Card(3, 11);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertTrue(HandEvaluator.isRoyalFlush(cards));
    }
    @Test public void testEvaluateRoyalFlush() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(3, 14);
        Card card2 = new Card(1, 5);
        Card card3 = new Card(3, 10);
        Card card4 = new Card(3, 13);
        Card card5 = new Card(3, 4);
        Card card6 = new Card(3, 12);
        Card card7 = new Card(3, 11);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertEquals(9,HandEvaluator.evaluate(cards));
    }
    @Test public void testEvaluateStraightFlush() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(3, 13);
        Card card2 = new Card(3, 9);
        Card card3 = new Card(3, 10);
        Card card4 = new Card(3, 13);
        Card card5 = new Card(2, 4);
        Card card6 = new Card(3, 12);
        Card card7 = new Card(3, 11);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertEquals(8,HandEvaluator.evaluate(cards));
    }
    @Test public void testEvaluateFourOfKind() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(3, 13);
        Card card2 = new Card(3, 9);
        Card card3 = new Card(3, 10);
        Card card4 = new Card(3, 13);
        Card card5 = new Card(2, 13);
        Card card6 = new Card(3, 13);
        Card card7 = new Card(3, 11);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertEquals(7,HandEvaluator.evaluate(cards));
    }
    @Test public void testEvaluateFullHouse() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(3, 13);
        Card card2 = new Card(3, 9);
        Card card3 = new Card(3, 11);
        Card card4 = new Card(3, 13);
        Card card5 = new Card(2, 13);
        Card card6 = new Card(3, 12);
        Card card7 = new Card(3, 11);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertEquals(6,HandEvaluator.evaluate(cards));
    }
    @Test public void testEvaluateFlush() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(3, 13);
        Card card2 = new Card(3, 9);
        Card card3 = new Card(3, 11);
        Card card4 = new Card(3, 13);
        Card card5 = new Card(2, 12);
        Card card6 = new Card(3, 12);
        Card card7 = new Card(3, 11);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertEquals(5,HandEvaluator.evaluate(cards));
    }
    @Test public void testEvaluateStraight() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(0, 3);
        Card card2 = new Card(0, 4);
        Card card3 = new Card(1, 5);
        Card card4 = new Card(1, 2);
        Card card5 = new Card(2, 13);
        Card card6 = new Card(3, 6);
        Card card7 = new Card(3, 11);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertEquals(4,HandEvaluator.evaluate(cards));
    }
    @Test public void testEvaluateThreeOfKind() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(0, 3);
        Card card2 = new Card(0, 3);
        Card card3 = new Card(1, 5);
        Card card4 = new Card(1, 2);
        Card card5 = new Card(2, 3);
        Card card6 = new Card(3, 6);
        Card card7 = new Card(3, 11);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertEquals(3,HandEvaluator.evaluate(cards));
    }
    @Test public void testEvaluateTwoPair() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(0, 3);
        Card card2 = new Card(1, 3);
        Card card3 = new Card(1, 5);
        Card card4 = new Card(1, 2);
        Card card5 = new Card(2, 2);
        Card card6 = new Card(3, 6);
        Card card7 = new Card(3, 11);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertEquals(2,HandEvaluator.evaluate(cards));
    }
    @Test public void testEvaluatePair() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(0, 8);
        Card card2 = new Card(1, 3);
        Card card3 = new Card(1, 5);
        Card card4 = new Card(1, 2);
        Card card5 = new Card(2, 2);
        Card card6 = new Card(3, 6);
        Card card7 = new Card(3, 11);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertEquals(1,HandEvaluator.evaluate(cards));
    }
    @Test public void testEvaluateHighCard() {
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(0, 8);
        Card card2 = new Card(1, 3);
        Card card3 = new Card(1, 5);
        Card card4 = new Card(1, 2);
        Card card5 = new Card(2, 14);
        Card card6 = new Card(3, 6);
        Card card7 = new Card(3, 11);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        assertEquals(0,HandEvaluator.evaluate(cards));
    }

    @Test public void testTieCompareStraightFlushDifferent() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(0, 10);
        Card card2 = new Card(0, 9);

        Card card3 = new Card(0, 5);
        Card card4 = new Card(1, 8);

        Card card5 = new Card(0, 8);
        Card card6 = new Card(0, 7);
        Card card7 = new Card(0, 6);
        Card card8 = new Card(0, 3);
        Card card9 = new Card(0, 4);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.compareStraightFlushes(cards1,cards2) > 0);
    }

    @Test public void testComapreStraightFlushSame() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(1, 10);
        Card card2 = new Card(1, 9);

        Card card3 = new Card(1, 5);
        Card card4 = new Card(1, 8);

        Card card5 = new Card(0, 8);
        Card card6 = new Card(0, 7);
        Card card7 = new Card(0, 6);
        Card card8 = new Card(0, 5);
        Card card9 = new Card(0, 4);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.compareStraightFlushes(cards1,cards2) == 0);
    }
    @Test public void testCompareFourOfKindDifferent() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(0, 10);
        Card card2 = new Card(1, 10);

        Card card3 = new Card(0, 8);
        Card card4 = new Card(1, 8);

        Card card5 = new Card(3, 10);
        Card card6 = new Card(2, 10);
        Card card7 = new Card(2, 8);
        Card card8 = new Card(3, 8);
        Card card9 = new Card(0, 4);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.compareFourOfKinds(cards1,cards2) > 0);
    }
    @Test public void testCompareFourOfKindSameDifferentHigh() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(0, 11);
        Card card2 = new Card(1, 11);

        Card card3 = new Card(0, 8);
        Card card4 = new Card(1, 8);

        Card card5 = new Card(0, 10);
        Card card6 = new Card(1, 10);
        Card card7 = new Card(2, 10);
        Card card8 = new Card(3, 10);
        Card card9 = new Card(0, 4);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.compareFourOfKinds(cards1,cards2) > 0);
    }

    @Test public void testCompareFourOfKindSameHighSame() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(0, 11);
        Card card2 = new Card(1, 11);

        Card card3 = new Card(3, 11);
        Card card4 = new Card(1, 8);

        Card card5 = new Card(0, 10);
        Card card6 = new Card(1, 10);
        Card card7 = new Card(2, 10);
        Card card8 = new Card(3, 10);
        Card card9 = new Card(0, 4);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.compareFourOfKinds(cards1,cards2) == 0);
    }
    @Test public void testCompareFullHouseDifferentThreeKind() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(0, 11);
        Card card2 = new Card(1, 11);

        Card card3 = new Card(3, 11);
        Card card4 = new Card(1, 8);

        Card card5 = new Card(2, 11);
        Card card6 = new Card(1, 10);
        Card card7 = new Card(2, 10);
        Card card8 = new Card(3, 10);
        Card card9 = new Card(0, 4);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.compareFullHouse(cards1,cards2) > 0);
    }
    @Test public void testCompareFullHouseDifferentPair() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(0, 11);
        Card card2 = new Card(1, 9);

        Card card3 = new Card(3, 11);
        Card card4 = new Card(1, 8);

        Card card5 = new Card(2, 11);
        Card card6 = new Card(1, 11);
        Card card7 = new Card(2, 8);
        Card card8 = new Card(3, 9);
        Card card9 = new Card(0, 4);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.compareFullHouse(cards1,cards2) > 0);
    }
    @Test public void testCompareFullHouseSame() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(0, 11);
        Card card2 = new Card(2, 9);

        Card card3 = new Card(3, 11);
        Card card4 = new Card(1, 9);

        Card card5 = new Card(2, 11);
        Card card6 = new Card(1, 11);
        Card card7 = new Card(2, 8);
        Card card8 = new Card(3, 9);
        Card card9 = new Card(0, 4);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.compareFullHouse(cards1,cards2) == 0);
    }
    @Test public void testCompareFlushDifferent() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(0, 12);
        Card card2 = new Card(1, 9);

        Card card3 = new Card(0, 11);
        Card card4 = new Card(1, 8);

        Card card5 = new Card(0, 2);
        Card card6 = new Card(0, 5);
        Card card7 = new Card(0, 6);
        Card card8 = new Card(0, 9);
        Card card9 = new Card(1, 4);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.compareFlush(cards1,cards2) > 0);
    }
    @Test public void testCompareFlushSame() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(1, 12);
        Card card2 = new Card(1, 9);

        Card card3 = new Card(1, 11);
        Card card4 = new Card(1, 8);

        Card card5 = new Card(0, 2);
        Card card6 = new Card(0, 5);
        Card card7 = new Card(0, 6);
        Card card8 = new Card(0, 9);
        Card card9 = new Card(0, 4);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.compareFlush(cards1,cards2) == 0);
    }
    @Test public void testCompareStraightDifferent() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(1, 12);
        Card card2 = new Card(1, 9);

        Card card3 = new Card(1, 11);
        Card card4 = new Card(1, 7);

        Card card5 = new Card(2, 11);
        Card card6 = new Card(2, 10);
        Card card7 = new Card(3, 9);
        Card card8 = new Card(3, 8);
        Card card9 = new Card(0, 7);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.compareStraights(cards1,cards2) > 0);
    }
    @Test public void testCompareStraightSame() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(0, 11);
        Card card2 = new Card(1, 9);

        Card card3 = new Card(1, 11);
        Card card4 = new Card(1, 7);

        Card card5 = new Card(2, 11);
        Card card6 = new Card(2, 10);
        Card card7 = new Card(3, 9);
        Card card8 = new Card(3, 8);
        Card card9 = new Card(0, 7);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.compareStraights(cards1,cards2) == 0);
    }
    @Test public void testCompareThreeOfKindDifferent() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(1, 12);
        Card card2 = new Card(0, 12);

        Card card3 = new Card(1, 11);
        Card card4 = new Card(0, 11);

        Card card5 = new Card(2, 12);
        Card card6 = new Card(0, 11);
        Card card7 = new Card(3, 2);
        Card card8 = new Card(3, 4);
        Card card9 = new Card(0, 5);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.compareThreeOfKind(cards1,cards2) > 0);
    }

    @Test public void testCompareThreeOfKindDifferentHigh() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(1, 12);
        Card card2 = new Card(0, 10);

        Card card3 = new Card(0, 12);
        Card card4 = new Card(1, 7);

        Card card5 = new Card(2, 12);
        Card card6 = new Card(0, 12);
        Card card7 = new Card(3, 2);
        Card card8 = new Card(3, 4);
        Card card9 = new Card(0, 5);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.compareThreeOfKind(cards1,cards2) > 0);
    }

    @Test public void testCompareThreeOfKindSame() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(1, 12);
        Card card2 = new Card(0, 10);

        Card card3 = new Card(0, 12);
        Card card4 = new Card(1, 10);

        Card card5 = new Card(2, 12);
        Card card6 = new Card(0, 12);
        Card card7 = new Card(3, 2);
        Card card8 = new Card(3, 4);
        Card card9 = new Card(0, 5);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.compareThreeOfKind(cards1,cards2) == 0);
    }

    @Test public void testCompareTwoPairDifferentHighPair() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(1, 13);
        Card card2 = new Card(0, 10);

        Card card3 = new Card(0, 12);
        Card card4 = new Card(1, 10);

        Card card5 = new Card(2, 13);
        Card card6 = new Card(0, 12);
        Card card7 = new Card(3, 10);
        Card card8 = new Card(3, 4);
        Card card9 = new Card(0, 5);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.compareTwoPair(cards1,cards2) > 0);
    }
    @Test public void testCompareTwoPairDifferentLowPair() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(1, 13);
        Card card2 = new Card(0, 12);

        Card card3 = new Card(0, 13);
        Card card4 = new Card(1, 10);

        Card card5 = new Card(2, 13);
        Card card6 = new Card(1, 12);
        Card card7 = new Card(3, 10);
        Card card8 = new Card(3, 4);
        Card card9 = new Card(0, 5);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.compareTwoPair(cards1,cards2) > 0);
    }
    @Test public void testCompareTwoPairDifferentHigh() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(1, 13);
        Card card2 = new Card(0, 12);

        Card card3 = new Card(0, 13);
        Card card4 = new Card(1, 10);

        Card card5 = new Card(2, 13);
        Card card6 = new Card(1, 9);
        Card card7 = new Card(3, 2);
        Card card8 = new Card(3, 5);
        Card card9 = new Card(0, 5);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.compareTwoPair(cards1,cards2) > 0);
    }
    @Test public void testComparePairDifferent() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(1, 13);
        Card card2 = new Card(0, 9);

        Card card3 = new Card(0, 12);
        Card card4 = new Card(1, 10);

        Card card5 = new Card(2, 13);
        Card card6 = new Card(1, 12);
        Card card7 = new Card(3, 2);
        Card card8 = new Card(3, 5);
        Card card9 = new Card(0, 4);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.comparePair(cards1,cards2) > 0);
    }
    @Test public void testComparePairSamePairDifferentHigh() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(1, 13);
        Card card2 = new Card(0, 10);

        Card card3 = new Card(0, 13);
        Card card4 = new Card(1, 9);

        Card card5 = new Card(2, 13);
        Card card6 = new Card(1, 12);
        Card card7 = new Card(3, 2);
        Card card8 = new Card(3, 5);
        Card card9 = new Card(0, 4);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.comparePair(cards1,cards2) > 0);
    }
    @Test public void testComparePairEqual() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(1, 13);
        Card card2 = new Card(0, 9);

        Card card3 = new Card(0, 13);
        Card card4 = new Card(1, 9);

        Card card5 = new Card(2, 13);
        Card card6 = new Card(1, 12);
        Card card7 = new Card(3, 2);
        Card card8 = new Card(3, 5);
        Card card9 = new Card(0, 4);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.comparePair(cards1,cards2) == 0);
    }
    @Test public void testCompareHighCardDifferent() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(1, 10);
        Card card2 = new Card(0, 9);

        Card card3 = new Card(0, 6);
        Card card4 = new Card(1, 9);

        Card card5 = new Card(2, 13);
        Card card6 = new Card(1, 12);
        Card card7 = new Card(3, 2);
        Card card8 = new Card(3, 5);
        Card card9 = new Card(0, 4);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.compareHighCard(cards1,cards2) > 0);
    }
    @Test public void testCompareHighCardSame() {
        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();
        Card card1 = new Card(1, 6);
        Card card2 = new Card(0, 9);

        Card card3 = new Card(0, 6);
        Card card4 = new Card(1, 9);

        Card card5 = new Card(2, 13);
        Card card6 = new Card(1, 12);
        Card card7 = new Card(3, 2);
        Card card8 = new Card(3, 5);
        Card card9 = new Card(0, 4);
        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);

        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        cards1.add(card8);
        cards1.add(card9);

        cards2.add(card5);
        cards2.add(card6);
        cards2.add(card7);
        cards2.add(card8);
        cards2.add(card9);
        assertTrue(HandEvaluator.compareHighCard(cards1,cards2) == 0);
    }
    @Test public void testGetHighestFourKind() {
        ArrayList<Card> cards1 = new ArrayList<>();
        Card card1 = new Card(1, 6);
        Card card2 = new Card(3, 6);
        Card card3 = new Card(0, 6);
        Card card4 = new Card(2, 6);
        Card card5 = new Card(2, 13);
        Card card6 = new Card(1, 12);
        Card card7 = new Card(3, 2);

        cards1.add(card1);
        cards1.add(card2);
        cards1.add(card3);
        cards1.add(card4);
        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);

        assertEquals(6,HandEvaluator.getHighestFourOfKind(cards1));
    }
    @Test public void testGetHighestThreeKind() {
        ArrayList<Card> cards1 = new ArrayList<>();
        Card card1 = new Card(1, 6);
        Card card2 = new Card(3, 6);
        Card card3 = new Card(0, 6);
        Card card4 = new Card(2, 7);
        Card card5 = new Card(2, 13);
        Card card6 = new Card(1, 13);
        Card card7 = new Card(3, 13);

        cards1.add(card1);
        cards1.add(card2);
        cards1.add(card3);
        cards1.add(card4);
        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);

        assertEquals(13,HandEvaluator.getHighestThreeOfKind(cards1));
    }
    @Test public void testGetHighestPair() {
        ArrayList<Card> cards1 = new ArrayList<>();
        Card card1 = new Card(1, 6);
        Card card2 = new Card(3, 6);
        Card card3 = new Card(0, 5);
        Card card4 = new Card(2, 7);
        Card card5 = new Card(0, 7);
        Card card6 = new Card(1, 13);
        Card card7 = new Card(3, 13);

        cards1.add(card1);
        cards1.add(card2);
        cards1.add(card3);
        cards1.add(card4);
        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);

        assertEquals(13,HandEvaluator.getHighestPair(cards1));
    }
    @Test public void testSortRank() {
        ArrayList<Card> cards1 = new ArrayList<>();
        Card card1 = new Card(1, 6);
        Card card2 = new Card(3, 6);
        Card card3 = new Card(0, 6);
        Card card4 = new Card(2, 7);
        Card card5 = new Card(2, 13);
        Card card6 = new Card(1, 13);
        Card card7 = new Card(3, 13);

        cards1.add(card1);
        cards1.add(card2);
        cards1.add(card3);
        cards1.add(card4);
        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        HandEvaluator.sortByRank(cards1);
        assertAll(
                () -> assertEquals(13, cards1.get(0).getRank()),
                () -> assertEquals(13, cards1.get(1).getRank()),
                () -> assertEquals(13, cards1.get(2).getRank()),
                () -> assertEquals(7, cards1.get(3).getRank()),
                () -> assertEquals(6, cards1.get(4).getRank()),
                () -> assertEquals(6, cards1.get(5).getRank()),
                () -> assertEquals(6, cards1.get(6).getRank())
        );
    }
    @Test public void testObtainMostCommonSuit() {
        ArrayList<Card> cards1 = new ArrayList<>();
        Card card1 = new Card(1, 6);
        Card card2 = new Card(3, 6);
        Card card3 = new Card(2, 6);
        Card card4 = new Card(2, 7);
        Card card5 = new Card(2, 13);
        Card card6 = new Card(1, 13);
        Card card7 = new Card(3, 13);

        cards1.add(card1);
        cards1.add(card2);
        cards1.add(card3);
        cards1.add(card4);
        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        int commonSuit = HandEvaluator.obtainMostCommonSuit(cards1);
        assertEquals(2, commonSuit);
    }
    @Test public void testGetCardsWithoutRank() {
        ArrayList<Card> cards1 = new ArrayList<>();
        Card card1 = new Card(1, 6);
        Card card2 = new Card(3, 6);
        Card card3 = new Card(0, 13);
        Card card4 = new Card(2, 7);
        Card card5 = new Card(2, 13);
        Card card6 = new Card(1, 13);
        Card card7 = new Card(3, 13);

        cards1.add(card1);
        cards1.add(card2);
        cards1.add(card3);
        cards1.add(card4);
        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        List<Card> reducedCards = HandEvaluator.getCardsWithoutRank(cards1, 13);
        HandEvaluator.sortByRank(reducedCards);
        assertAll(
                () -> assertEquals(7, reducedCards.get(0).getRank()),
                () -> assertEquals(6, reducedCards.get(1).getRank()),
                () -> assertEquals(6, reducedCards.get(2).getRank())
        );
    }
    @Test public void testGetCardsOfSameSuit() {
        ArrayList<Card> cards1 = new ArrayList<>();
        Card card1 = new Card(1, 6);
        Card card2 = new Card(3, 6);
        Card card3 = new Card(0, 13);
        Card card4 = new Card(2, 7);
        Card card5 = new Card(2, 13);
        Card card6 = new Card(1, 13);
        Card card7 = new Card(3, 13);

        cards1.add(card1);
        cards1.add(card2);
        cards1.add(card3);
        cards1.add(card4);
        cards1.add(card5);
        cards1.add(card6);
        cards1.add(card7);
        List<Card> reducedCards = HandEvaluator.getCardsOfSameSuit(cards1, 1);
        HandEvaluator.sortByRank(reducedCards);
        assertAll(
                () -> assertEquals(1, reducedCards.get(0).getSuit()),
                () -> assertEquals(1, reducedCards.get(1).getSuit())
        );
    }
}
