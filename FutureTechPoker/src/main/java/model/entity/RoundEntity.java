package model.entity;
import Poker.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "round")
public class RoundEntity extends BaseEntity {
    @Id
    @Column(name = "id_round")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_round;

    @Column(name = "round_num")
    private Integer roundNum;

    @Column(name = "river")
    private String river;

    @Column(name = "winning_player")
    private String winningPlayer;

    @Column(name = "current_bet")
    private double currentBet;

    @Column(name = "current_pot")
    private double currentPot;

    @Column(name = "starting_bet")
    private double startingBet;

    @Column(name = "current_player_turn")
    private Integer currentPlayerTurn;

    @Column(name = "last_raise")
    private Integer lastRaise;

    @Column(name = "game_over")
    private boolean gameOver;

    @Column(name = "tie")
    private boolean tie;

    @Column(name = "seed")
    private long seed;

    @Column(name = "tied_players")
    private String tiedPlayers;

    /*
    //@OneToOne
    //@JoinColumn(name = "id_player1")
    private PlayerEntity player1;

    //@OneToOne
    //@JoinColumn(name = "id_player2")
    private PlayerEntity player2;

    //@OneToOne
    //@JoinColumn(name = "id_player3")
    private PlayerEntity player3;

    //@OneToOne
    //@JoinColumn(name = "id_player4")
    private PlayerEntity player4;

    //@OneToOne
    //@JoinColumn(name = "id_player5")
    private PlayerEntity player5;

    //@OneToOne
    //@JoinColumn(name = "id_player6")
    private PlayerEntity player6;
*/
    public RoundEntity(Round round){
        this.roundNum = round.getRound_num();
        this.tie = round.getTie();
        this.seed = round.getSeed();
        this.currentBet = round.getCurrent_bet();
        this.currentPot = round.getCurrent_pot();
        this.currentPlayerTurn = round.getCurrent_player();
        this.lastRaise = round.getLast_raise();
        this.gameOver = round.getGameOver();
        this.winningPlayer = round.who_won().getName();
        this.tiedPlayers = round.tie().toString();
        this.startingBet = round.getStarting_bet();
        this.river = round.getRiver().toString();
    }
/*
    public void addPlayer(int which, Player p, PlayerEntity entity){
        switch (which){
            case 1:
                this.player1 = entity;
                this.player1.setTurnOrder(p.getTurnOrder());
                this.player1.setHand(p.getHand().toString());
                this.player1.setCurrentBet(p.getCurrentBet());
                this.player1.setStatus(p.getStatus());
                break;
            case 2:
                this.player2 = entity;
                this.player2.setTurnOrder(p.getTurnOrder());
                this.player2.setHand(p.getHand().toString());
                this.player2.setCurrentBet(p.getCurrentBet());
                this.player2.setStatus(p.getStatus());
                break;
            case 3:
                this.player3 = entity;
                this.player3.setTurnOrder(p.getTurnOrder());
                this.player3.setHand(p.getHand().toString());
                this.player3.setCurrentBet(p.getCurrentBet());
                this.player3.setStatus(p.getStatus());
                break;
            case 4:
                this.player4 = entity;
                this.player4.setTurnOrder(p.getTurnOrder());
                this.player4.setHand(p.getHand().toString());
                this.player4.setCurrentBet(p.getCurrentBet());
                this.player4.setStatus(p.getStatus());
                break;
            case 5:
                this.player5 = entity;
                this.player5.setTurnOrder(p.getTurnOrder());
                this.player5.setHand(p.getHand().toString());
                this.player5.setCurrentBet(p.getCurrentBet());
                this.player5.setStatus(p.getStatus());
                break;
            case 6:
                this.player6 = entity;
                this.player6.setTurnOrder(p.getTurnOrder());
                this.player6.setHand(p.getHand().toString());
                this.player6.setCurrentBet(p.getCurrentBet());
                this.player6.setStatus(p.getStatus());
                break;
        }
    }

    public double getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(double currentBet) {
        this.currentBet = currentBet;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isTie() {
        return tie;
    }

    public double getCurrentPot() {
        return currentPot;
    }

    public double getStartingBet() {
        return startingBet;
    }

    public Integer getCurrentPlayerTurn() {
        return currentPlayerTurn;
    }

    public Integer getRoundNum() {
        return roundNum;
    }

    public Integer getLastRaise() {
        return lastRaise;
    }

    public PlayerEntity getPlayer1() {
        return player1;
    }

    public PlayerEntity getPlayer2() {
        return player2;
    }

    public PlayerEntity getPlayer3() {
        return player3;
    }

    public PlayerEntity getPlayer4() {
        return player4;
    }

    public PlayerEntity getPlayer5() {
        return player5;
    }

    public PlayerEntity getPlayer6() {
        return player6;
    }

    public String getRiver() {
        return river;
    }

    public String getTiedPlayers() {
        return tiedPlayers;
    }

    public String getWinningPlayer() {
        return winningPlayer;
    }

    public void setCurrentPlayerTurn(Integer currentPlayerTurn) {
        this.currentPlayerTurn = currentPlayerTurn;
    }

    public void setCurrentPot(double currentPot) {
        this.currentPot = currentPot;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setLastRaise(Integer lastRaise) {
        this.lastRaise = lastRaise;
    }
  */
    public Integer getID() {
        return id_round;
    }



}
