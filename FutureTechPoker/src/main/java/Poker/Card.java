package Poker;

public class Card implements Comparable<Card> {
    private int suit;
    private int rank;

    /**
     * Card class constructor
     * @param suit int suit of card
     * @param rank int rank of card
     */
    public Card(int suit, int rank) {
        if (suit >= 0 && suit <= 4) {
            this.suit = suit;
        }
        if (rank >= 2 && rank <= 14) {
            this.rank = rank;
        }
    }

    /**
     * Getter for the suit attribute
     * @return int, the suit of the card
     */
    public int getSuit() {
        return this.suit;
    }

    /**
     * Getter for the rank attribute
     * @return int, the rank of the card
     */
    public int getRank() {
        return this.rank;
    }

    /**
     * Compares the rank of this card to the rank of otherCard
     * @param otherCard the object to be compared.
     * @return int, Greater than 0 if this Card has a higher rank,
     * less than 0 if otherCard has a greater rank, 0 if ranks are even
     */
    @Override
    public int compareTo(Card otherCard) {
        return this.rank - otherCard.getRank();
    }

    /**
     * Card class toString
     * @return String, Readable representation of a Card
     */
    public String toString() {
        String suitName;
        String rankName;

        if (this.suit == 0) {
            suitName = "Clubs";
        } else if (this.suit == 1) {
            suitName = "Hearts";
        } else if (this.suit == 2) {
            suitName = "Diamonds";
        } else if (this.suit == 3) {
            suitName = "Spades";
        } else {
            suitName = "Unknown Suit";
        }

        if (this.rank <= 14 && this.rank >=2) {
            if (this.rank == 14) {
                rankName = "Ace";
            } else if (this.rank == 13) {
                rankName = "King";
            } else if (this.rank == 12) {
                rankName = "Queen";
            } else if (this.rank == 11) {
                rankName = "Jack";
            } else {
                rankName = String.valueOf(this.rank);
            }
        }else {
                rankName = "Unknown Rank";
            }

        return rankName + " of " + suitName;
    }
}
