This file will contain the design for the Poker Game.

Card Class:

    //0 clubs, 1 hearts, 2 diamonds, 3 spades
    int suit;
    //1 ace, 2-10, 11 jack, 12 queen, 13 king
    int rank;
    dictionary contains all cards

    string tostring();

Deck Class:

    Card array[52] deck;
    
    void fill_deck();
    void shuffle deck();
    void draw();
    string tostring();

Hand Class:

    Card array[2] hand;
    int evaluate_hand();
    string tostring();

Player Class:

    int turn_order;
    boolean fold;
    int current_bet;
    boolean big_blind;
    boolean small_blind;

    string tostring();

Round Class: one match 
    
    player array;
    card array river;
    int current_pot;
    int num_players;

    void match()
    string tostring();

Game Class: multiple games

    basically just call the round class until there are no players at table
    number of matches

Dealer Class:
    
    ?

