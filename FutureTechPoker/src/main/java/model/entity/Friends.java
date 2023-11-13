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
public class Friends extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "friend")
    private User friend;


    public Friends() {
    }

    public Friends(User owner, User friend) {
        this.owner = owner;
        this.friend = friend;
    }

    public Friends(Integer id, User owner, User friend) {
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }
}