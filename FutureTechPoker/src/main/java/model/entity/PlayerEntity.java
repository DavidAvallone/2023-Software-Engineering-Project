package model.entity;

import javax.persistence.*;

@Entity
@Table(name = "player")
public class PlayerEntity extends BaseEntity{

    @Id
    @Column(name = "id_player", insertable = false, updatable = false)
    private int id_player;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "currency")
    private double currency;

    @Column(name = "hand")
    private String hand;

    @Column(name = "turn_order")
    private int turnOrder;

    @Column(name = "current_bet")
    private double currentBet;

    @Column(name = "status")
    private String status;

    /*
    //@OneToOne
    //@JoinColumn(name = "user_id") // This is the foreign key column
    private User user; // This is the reference to the User entity

     */

    public PlayerEntity(){
        //this.user = null;
        this.currency = 0;
        this.playerName = null;
        this.currentBet = 0;
        this.turnOrder = -1;
        this.hand = null;
        this.status = null;
    }

    public PlayerEntity(User user){
        this();
        //this.user = user;
        this.currency = user.getBalance();
        this.playerName = user.getName();
    }

    @Override
    public Integer getID() {
        return id_player;
    }

    public String getStatus() {
        return status;
    }

    public double getCurrency() {
        return currency;
    }

    public double getCurrentBet() {
        return currentBet;
    }

    public int getTurnOrder() {
        return turnOrder;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getHand() {
        return hand;
    }

    public void setCurrency(double currency) {
        this.currency = currency;
    }

    public void setCurrentBet(double currentBet) {
        this.currentBet = currentBet;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTurnOrder(int turnOrder) {
        this.turnOrder = turnOrder;
    }

    public void setHand(String string) {
        this.hand = string;
    }

    public void setName(String playerName) {
    }
}
