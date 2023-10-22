package Poker;

public class Card {
    private int suit;
    private int rank;

    public Card(int suit, int rank){
        if (suit >=0 && suit <=4) {
            this.suit = suit;
        }
        if (rank >= 1 && rank <=13) {
            this.rank = rank;
        }
    }

    public int getSuit()
    {
        return this.suit;
    }

    public int getRank(){
        return this.rank;
    }

    public String toString()
    {
        String suitName;
        String rankName;

        if (this.suit == 0){
            suitName = "Clubs";
        }
        else if (this.suit == 1){
            suitName = "Hearts";
        }
        else if (this.suit == 2){
            suitName = "Diamonds";
        }
        else if (this.suit == 3) {
            suitName = "Spades";
        }
        else{
            suitName = "Unknown Suit";
        }

        if (this.rank >= 11 || this.rank == 1){
            if(this.rank == 1){
                rankName = "Ace";
            }
            else if(this.rank == 13){
                rankName = "King";
            }
            else if(this.rank == 12){
                rankName = "Queen";
            }
            else if (this.rank == 11){
                rankName = "Jack";
            }
            else {
                rankName = String.valueOf(this.rank);
            }
        }
        else{
            rankName = "Unknown Rank";
        }

        return rankName + " of " + suitName;
    }
}
