package model.entity;

import javax.persistence.*;
/*
CREATE TABLE Friends (
        id INT PRIMARY KEY AUTO_INCREMENT,
        owner INT,
        friend INT,
        FOREIGN KEY (owner) REFERENCES user(id_user),
        FOREIGN KEY (friend) REFERENCES user(id_user)
        );
 */

@Entity
@Table(name = "Friends")

public class Friends extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer owner;

    private Integer friend;


    public Friends() {
    }

    public Friends(Integer owner, Integer friend) {
        this.owner = owner;
        this.friend = friend;
    }

    public Friends(Integer id, Integer owner, Integer friend) {
        this.id = id;
        this.owner = owner;
        this.friend = friend;
    }
    public Integer getID() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Integer getFriend() {
        return friend;
    }

    public void setFriend(Integer friend) {
        this.friend = friend;
    }
}