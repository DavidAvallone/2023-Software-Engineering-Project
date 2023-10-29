import Poker.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CardTest {

    @Test public void testGetRank(){
        Card card = new Card(1, 4);
        assertEquals(4, card.getRank());
    }

    @Test public void testGetSuit(){
        Card card = new Card(1, 4);
        assertEquals(1, card.getSuit());
    }

    @Test public void testCompareToGreater(){
        Card card = new Card(1, 4);
        Card card2 = new Card(2, 2);
        assertTrue(card.compareTo(card2) > 0);
    }
    @Test public void TestCompareToLessThen(){
        Card card = new Card(1, 4);
        Card card2 = new Card(2, 2);
        assertTrue(card2.compareTo(card) < 0);
    }
    @Test public void testCompareToEqual(){
        Card card = new Card(1, 4);
        Card card2 = new Card(2, 4);
        assertEquals(0, card2.compareTo(card));
    }
    @Test public void testToStringClub(){
        Card card = new Card(0, 4);
        assertEquals("4 of Clubs", card.toString());
    }
    @Test public void testToStringHeart(){
        Card card = new Card(1, 4);
        assertEquals("4 of Hearts", card.toString());
    }
    @Test public void testToStringDiamond(){
        Card card = new Card(2, 4);
        assertEquals("4 of Diamonds", card.toString());
    }
    @Test public void testToStringSpade(){
        Card card = new Card(3, 4);
        assertEquals("4 of Spades", card.toString());
    }
    @Test public void testToStringUnknownSuit(){
        Card card = new Card(4, 4);
        assertEquals("4 of Unknown Suit", card.toString());
    }
    @Test public void testToStringRankLessThan11(){
        Card card = new Card(3, 10);
        assertEquals("10 of Spades", card.toString());
    }
    @Test public void testToStringRankJack(){
        Card card = new Card(3, 11);
        assertEquals("Jack of Spades", card.toString());
    }
    @Test public void testToStringRankQueen(){
        Card card = new Card(3, 12);
        assertEquals("Queen of Spades", card.toString());
    }
    @Test public void testToStringRankKing(){
        Card card = new Card(3, 13);
        assertEquals("King of Spades", card.toString());
    }
    @Test public void testToStringRankAce(){
        Card card = new Card(3, 14);
        assertEquals("Ace of Spades", card.toString());
    }
    @Test public void testToStringUnknownRank(){
        Card card = new Card(3, 1);
        assertEquals("Unknown Rank of Spades", card.toString());
    }
}
