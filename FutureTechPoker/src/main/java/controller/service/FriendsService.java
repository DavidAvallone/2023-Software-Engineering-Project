package controller.service;

import model.dao.FriendsDAO;
import model.entity.Friends;
import model.entity.User;

import java.util.List;

public class FriendsService {

    public static FriendsDAO dao = new FriendsDAO();

    public static void setDAO(FriendsDAO dao){
        FriendsService.dao = dao;
    }

    //Read Friends / getfriends list?

    //Update friends -> status?
    public static Friends updateFriends(Friends f){
        return dao.update(f);
    }
    //Delete friends -> Unadd friend
    public static void deleteFriends(int id){
        dao.delete(id);
    }
}
