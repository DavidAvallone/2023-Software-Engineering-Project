package Poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Deck {
    private ArrayList<Card> deck;
    private int size;
    private Long seed;

    /**
     * Deck class constructor
     * @param seed long, the seed of the deck for randomization
     */
    public Deck(Long seed)
    {
        this.seed = seed;
        deck = new ArrayList<>();
        for (int suit = 0; suit < 4; suit++){
            for (int rank = 2; rank <= 14; rank++){
                deck.add(new Card(suit, rank));
            }
        }
        this.size = deck.size();
    }

    /**
     * Getter for the size attribute
     * @return int, the number of cards in the deck
     */
    public int getSize(){
        return this.size;
    }

    /**
     * Determines if the deck is empty
     * @return boolean, true if empty, false if not empty
     */
    public boolean isEmpty(){
        return size <= 0;
    }

    /**
     * Shuffles the cards within the deck
     * @return ArrayList of Cards, the shuffled list of Cards
     */
    public ArrayList<Card> shuffle(){
        if(seed == null){
            Collections.shuffle(this.deck);
        }
        else{
            Random r = new Random(seed);
            Collections.shuffle(this.deck,r);
        }
        return this.deck;
    }

    /**
     * Draws a card off the top of the deck. This Card is removed from the deck
     * @return Card, the Card drawn
     */
    public Card draw(){
        if (this.deck.isEmpty()){
            return null;
        }
        Card card = this.deck.get(size-1);
        this.deck.remove(size-1);
        this.size--;
        return card;
    }

    /**
     * Deck class toSting
     * @return String, String representation of the Cards in the deck
     */
    public String toString(){
        String contents = "";
        for (int i = 0; i < size; i++){
            contents = contents + deck.get(i).toString() + "\n";
        }
        return contents;
    }
}
