import Poker.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;


public class HandTest {

    @Test public void testGetHandSize(){
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(1,4));
        cards.add(new Card(1,3));
        Hand hand = new Hand(cards);
        assertEquals(2, hand.getHandSize());
    }

    @Test public void testAddCard(){
        Hand hand = new Hand();
        hand.add(new Card(1,3));
        assertTrue(hand.getCards().get(0).getSuit() == 1 && hand.getCards().get(0).getRank() == 3);
    }

    @Test public void testSetCards(){
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(1,4));
        cards.add(new Card(1,3));
        ArrayList<Card> cards2 = new ArrayList<>();
        cards.add(new Card(2,4));
        Hand hand = new Hand(cards);
        hand.setCards(cards2);
        assertTrue(hand.getCards() == cards2);
    }

    @Test public void testCompareToGreater(){
        ArrayList<Card> cards1 = new ArrayList<>();
        cards1.add(new Card(1,4));
        cards1.add(new Card(1,3));
        ArrayList<Card> cards2 = new ArrayList<>();
        cards2.add(new Card(2,4));
        cards2.add( new Card(1, 5));
        Hand hand1 = new Hand(cards1);
        Hand hand2 = new Hand(cards2);
        ArrayList<Card> river = new ArrayList<>();
        river.add(new Card(1,14));
        river.add(new Card(1,13));
        river.add(new Card(1,10));
        river.add(new Card(3,8));
        river.add(new Card(3,5));
        assertTrue(hand1.compareTo(hand2, river) > 0);
    }

    @Test public void testCompareToLess(){
        ArrayList<Card> cards1 = new ArrayList<>();
        cards1.add(new Card(1,4));
        cards1.add(new Card(1,3));
        ArrayList<Card> cards2 = new ArrayList<>();
        cards2.add(new Card(2,4));
        cards2.add( new Card(1, 5));
        Hand hand1 = new Hand(cards1);
        Hand hand2 = new Hand(cards2);
        ArrayList<Card> river = new ArrayList<>();
        river.add(new Card(1,14));
        river.add(new Card(1,13));
        river.add(new Card(1,10));
        river.add(new Card(3,8));
        river.add(new Card(3,5));
        assertTrue(hand2.compareTo(hand1, river) < 0);
    }
    @Test public void testCompareToEqual(){
        ArrayList<Card> cards1 = new ArrayList<>();
        cards1.add(new Card(1,4));
        cards1.add(new Card(1,3));
        ArrayList<Card> cards2 = new ArrayList<>();
        cards2.add(new Card(2,4));
        cards2.add( new Card(1, 5));
        Hand hand1 = new Hand(cards1);
        Hand hand2 = new Hand(cards2);
        ArrayList<Card> river = new ArrayList<>();
        river.add(new Card(1,5));
        river.add(new Card(2,6));
        river.add(new Card(2,7));
        river.add(new Card(3,8));
        river.add(new Card(3,2));
        assertEquals(0, hand2.compareTo(hand1, river));
    }

    @Test public void testToString() {
        ArrayList<Card> cards1 = new ArrayList<>();
        cards1.add(new Card(1, 4));
        cards1.add(new Card(1, 3));
        Hand hand = new Hand(cards1);
        System.out.println(hand);
        assertEquals("| 4 of Hearts | 3 of Hearts | " ,hand.toString());
    }
}

