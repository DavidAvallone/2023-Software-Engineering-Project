package Poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Deck {
    private ArrayList<Card> deck;
    private int size;
    private Long seed;

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

    public int getSize(){
        return this.size;
    }

    public boolean isEmpty(){
        return size <= 0;
    }

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

    public Card draw(){
        if (this.deck.isEmpty()){
            return null;
        }
        Card card = this.deck.get(size-1);
        this.deck.remove(size-1);
        this.size--;
        return card;
    }

    public String toString(){
        String contents = "";
        for (int i = 0; i < size; i++){
            contents = contents + deck.get(i).toString() + "\n";
        }
        return contents;
    }
}
