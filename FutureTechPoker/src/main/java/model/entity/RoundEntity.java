package model.entity;
import Poker.Player;
import Poker.Round;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RoundEntity extends BaseEntity {
    private Integer id_round = null;
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
    private Integer player1_id;
    private Integer player2_id;
    private Integer player3_id;
    private Integer player4_id;
    private Integer player5_id;
    private Integer player6_id;

    /** SQL table creator
     create table poker.`round`(
     id_round INT not null,
     round_num INT,
     river Varchar(255),
     winning_player varchar(50),
     current_bet double,
     current_pot double,
     starting_bet double,
     current_player_turn int,
     last_raise int,
     game_over bit,
     tie bit,
     seed BIGINT,
     tied_players Varchar(255),
     player1_id int,
     player2_id int,
     player3_id int,
     player4_id int,
     player5_id int,
     player6_id int,
     constraint id_round_pk foreign key(id_round) references poker.round_players(id_round),
     constraint player1_fk foreign key(player1_id) references poker.round_players(id_player1),
     constraint player2_fk foreign key(player2_id) references poker.round_players(id_player2),
     constraint player3_fk foreign key(player3_id) references poker.round_players(id_player3),
     constraint player4_fk foreign key(player4_id) references poker.round_players(id_player4),
     constraint player5_fk foreign key(player5_id) references poker.round_players(id_player5),
     constraint player6_fk foreign key(player6_id) references poker.round_players(id_player6)
     );
     */

    /**
     *
     *
     * @param seed
     * @param starting_bet
     */
    public RoundEntity(long seed, double starting_bet){
        Round round = new Round(starting_bet, seed);
        this.starting_bet = starting_bet;
        this.seed = seed;
        this.tie = round.getTie();
        this.last_raise = round.getLast_raise();
        this.game_over = round.getGameOver();
        this.round_num = round.getRound_num();
        this.current_player_turn = round.getCurrent_player();
        this.current_bet = round.getCurrent_bet();
        this.current_pot = round.getCurrent_pot();
    }

    public Integer getID() {
        return id_round;
    }
    public void setID(Integer id) {
        this.id_round = id;
    }

}
