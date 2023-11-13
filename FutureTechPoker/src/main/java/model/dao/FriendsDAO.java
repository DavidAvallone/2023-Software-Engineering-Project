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

    public List<Friends> getFriendsByUserId(Integer userId) {
        EntityManager em = getEntityManager();
        List<Friends> friendsList = em.createQuery("SELECT f FROM "+ getTableName() +" f WHERE f.owner.id = :userId", Friends.class)
                .setParameter("userId", userId)
                .getResultList();
        em.close();
        return friendsList;
    }

}
