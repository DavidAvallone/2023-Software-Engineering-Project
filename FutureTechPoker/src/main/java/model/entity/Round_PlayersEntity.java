package model.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Round_PlayersEntity extends BaseEntity {

    @Id @Column(name="id_round") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_round = null;

    private Integer id_player1 = null;
    private Integer id_player2 = null;
    private Integer id_player3 = null;
    private Integer id_player4 = null;
    private Integer id_player5 = null;
    private Integer id_player6 = null;

    /** SQL table creator
     create table poker.`round_players`(
     id_round INT not null auto_increment,
     id_player1 INT,
     id_player2 INT,
     id_player3 INT,
     id_player4 INT,
     id_player5 INT,
     id_player6 INT,
     constraint round_pk primary key(id_round),
     constraint player1_id_fk foreign key(id_player1) references poker.user(id_user),
     constraint player2_id_fk foreign key(id_player2) references poker.user(id_user),
     constraint player3_id_fk foreign key(id_player3) references poker.user(id_user),
     constraint player4_id_fk foreign key(id_player4) references poker.user(id_user),
     constraint player5_id_fk foreign key(id_player5) references poker.user(id_user),
     constraint player6_id_fk foreign key(id_player6) references poker.user(id_user)
     );
     */

    @Override
    public Integer getID() {
        return id_round;
    }

    public Round_PlayersEntity(){

    }
}
