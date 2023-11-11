package model.entity;

import javax.persistence.*;


@Entity
@Table(name = "user_friends")
public class Friends extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
    private int user;
//
//    @ManyToOne
//    @JoinColumn(name = "friend_id")
    private int friend;


    public Friends() {
    }

    public Friends(Integer user, Integer friend) {
        this.user = user;
        this.friend = friend;
    }
    public Integer getID() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getFriend() {
        return friend;
    }

    public void setFriend(Integer friend) {
        this.friend = friend;
    }
}