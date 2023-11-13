package model.entity;

import javax.persistence.*;

@Entity
public class Player extends BaseEntity{

    @Id
    @Column(name = "id_player")
    private int id;

    private String player_name;

    private double currency;

    private String hand;

    private int turn_order;

    private double current_bet;

    private String status;

    @OneToOne(mappedBy = "player")
    private User user;

    private Integer id_user;

    public Player(){
        //this.user = null;
        this.currency = 0;
        this.player_name = null;
        this.current_bet = 0;
        this.turn_order = -1;
        this.hand = null;
        this.status = null;
        this.id_user = null;
    }

    public Player(User user){
        this();
        //this.user = user;
        this.currency = user.getBalance();
        this.player_name = user.getUsername();
        this.id_user = user.getID();
    }


    @Override
    public Integer getID() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public double getCurrency() {
        return currency;
    }

    public double getCurrentBet() {
        return current_bet;
    }

    public int getTurnOrder() {
        return turn_order;
    }

    public String getPlayerName() {
        return player_name;
    }

    public String getHand() {
        return hand;
    }

    public void setCurrency(double currency) {
        this.currency = currency;
    }

    public void setCurrentBet(double currentBet) {
        this.current_bet = currentBet;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTurnOrder(int turnOrder) {
        this.turn_order = turnOrder;
    }

    public void setHand(String string) {
        this.hand = string;
    }

    public void setName(String playerName) {
    }

    public Integer getId_user(){
        return this.id_user;
    }

    public void setId_user(Integer id){
        this.id_user = id;
    }
}
