import Poker.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    private final long seed = 1;

    @Test public void testGetSize(){
        Deck deck = new Deck(seed);
        assertEquals(52, deck.getSize());
    }
    @Test public void testIsEmptyFalse(){
        Deck deck = new Deck(seed);
        assertFalse(deck.isEmpty());
    }
    @Test public void testIsEmptyTrue(){
        Deck deck = new Deck(seed);
        for(int i = 0; i < 52; i++){
            deck.draw();
        }
        assertTrue(deck.isEmpty());
    }
    @Test public void testShuffle(){
        Deck shuffledDeck = new Deck(seed);
        Deck unshuffledDeck = new Deck(seed);
        shuffledDeck.shuffle();
        assertAll(
                () -> assertEquals(52,shuffledDeck.getSize()),
                () -> assertNotEquals(shuffledDeck.toString(),unshuffledDeck.toString())
        );
    }

    @Test public void testDrawDeck(){
        Deck deck = new Deck(seed);
        Card drawnCard = deck.draw();
        //First card drawn of a not shuffled deck will be Ace of Spades
        assertTrue(drawnCard.getRank() == 14 && drawnCard.getSuit() == 3);
    }
    @Test public void testDrawDeckEmpty(){
        Deck deck = new Deck(seed);
        for(int i = 0; i < 52; i++){
            deck.draw();
        }
        assertNull(deck.draw());
    }
    @Test public void testToString(){
        Deck deck = new Deck(seed);
        assertEquals("2 of Clubs\n" +
                "3 of Clubs\n" +
                "4 of Clubs\n" +
                "5 of Clubs\n" +
                "6 of Clubs\n" +
                "7 of Clubs\n" +
                "8 of Clubs\n" +
                "9 of Clubs\n" +
                "10 of Clubs\n" +
                "Jack of Clubs\n" +
                "Queen of Clubs\n" +
                "King of Clubs\n" +
                "Ace of Clubs\n" +
                "2 of Hearts\n" +
                "3 of Hearts\n" +
                "4 of Hearts\n" +
                "5 of Hearts\n" +
                "6 of Hearts\n" +
                "7 of Hearts\n" +
                "8 of Hearts\n" +
                "9 of Hearts\n" +
                "10 of Hearts\n" +
                "Jack of Hearts\n" +
                "Queen of Hearts\n" +
                "King of Hearts\n" +
                "Ace of Hearts\n" +
                "2 of Diamonds\n" +
                "3 of Diamonds\n" +
                "4 of Diamonds\n" +
                "5 of Diamonds\n" +
                "6 of Diamonds\n" +
                "7 of Diamonds\n" +
                "8 of Diamonds\n" +
                "9 of Diamonds\n" +
                "10 of Diamonds\n" +
                "Jack of Diamonds\n" +
                "Queen of Diamonds\n" +
                "King of Diamonds\n" +
                "Ace of Diamonds\n" +
                "2 of Spades\n" +
                "3 of Spades\n" +
                "4 of Spades\n" +
                "5 of Spades\n" +
                "6 of Spades\n" +
                "7 of Spades\n" +
                "8 of Spades\n" +
                "9 of Spades\n" +
                "10 of Spades\n" +
                "Jack of Spades\n" +
                "Queen of Spades\n" +
                "King of Spades\n" +
                "Ace of Spades\n", deck.toString());
    }
    @Test public void testToStringEmptyDeck() {
        Deck deck = new Deck(seed);
        for(int i = 0; i < 52; i++){
            deck.draw();
        }
        assertEquals("", deck.toString());
    }


}
