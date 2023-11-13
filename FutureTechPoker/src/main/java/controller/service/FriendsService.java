package controller.service;

import model.dao.FriendsDAO;
import model.entity.Friends;
import model.entity.User;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

public class FriendsService {

    public static FriendsDAO dao = new FriendsDAO();

    public static void setDAO(FriendsDAO dao){
        FriendsService.dao = dao;
    }

    public static Friends addFriend(Friends f){
        return dao.create(f);
    }

    @Transactional
    public static List<Friends> getFriendsList(User owner){
        return dao.getFriendsByUserId(owner.getID());
    }

    public static Friends updateFriends(Friends f){
        return dao.update(f);
    }

    public static void deleteFriends(int id){
        dao.delete(id);
    }
}
