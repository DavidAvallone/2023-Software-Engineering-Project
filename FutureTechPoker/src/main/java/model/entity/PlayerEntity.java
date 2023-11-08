package model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import javax.persistence.Column;

@Entity
@Table(name = "player")
public class PlayerEntity extends BaseEntity{

    @Id @Column
    private int id_player; // should be the user id primary key from user table
    @Column
    private String player_name; // should be pulled from the user table
    @Column
    private double currency; // should be pulled from the uer table
    @Column
    private String hand; // will be updated after the game has started
    @Column
    private int turn_order;
    @Column
    private double current_bet;
    @Column
    private String status;



    @Override
    public Integer getID() {
        return id_player;
    }

    public void setId(int id) {
        this.id_player = id;
    }
}
