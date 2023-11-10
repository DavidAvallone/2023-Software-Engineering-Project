package model.dao;

import model.entity.Friends;
import model.entity.User;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;


public class FriendsDAO extends GenericDAO<Friends> {

    public FriendsDAO(){
        super(Friends.class);
    }

}
