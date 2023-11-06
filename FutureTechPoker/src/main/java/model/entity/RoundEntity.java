package model.entity;
import Poker.Player;
import Poker.Round;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RoundEntity extends BaseEntity {
    @Id @Column(name="id_round") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = null;
    private Integer round_num;
    private String river;
    private String winning_player;
    private double current_bet;
    private double current_pot;
    private double starting_bet;
    private Integer current_player_turn;
    private Integer last_raise;
    private boolean game_over;
    private boolean tie;
    private long seed;
    private String tied_players;
    private String players;

    public RoundEntity(ArrayList<Player> players, long seed, double starting_bet){
        Round round = new Round(players,starting_bet, seed);
        this.starting_bet = starting_bet;
        this.seed = seed;
        this.players = players.toString();
        this.tie = round.getTie();
        this.last_raise = round.getLast_raise();
        this.game_over = round.getGameOver();
        this.round_num = round.getRound_num();
        this.current_player_turn = round.getCurrent_player();
        this.current_bet = round.getCurrent_bet();
        this.current_pot = round.getCurrent_pot();
    }

    public Integer getID() {
        return id;
    }
    public void setID(Integer id) {
        this.id = id;
    }




}
