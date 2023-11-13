package controller.service;

import model.dao.FriendsDAO;
import model.dao.UserDAO;
import model.entity.User;
import model.entity.Friends;
import controller.service.FriendsService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import util.PasswordUtil;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*; //Mock Framework
import static org.junit.jupiter.api.Assertions.*;

public class FriendsServiceTest {
    @Test public void testAddFriend(){
        User user1= new User(101, "name", "test@test.com", "123456", User.NORMAL_PERMISSION);
        User user2 = new User(102, "name2", "test2@test.com", "123456", User.NORMAL_PERMISSION);

        Friends newFriends = new Friends(null, user1, user2);
        Friends registered = new Friends(10, user1, user2);

        FriendsDAO mockDAO = mock(FriendsDAO.class);
        when(mockDAO.create(any(Friends.class))).thenReturn(registered);
        FriendsService.setDAO(mockDAO);

        Friends returned = FriendsService.addFriend(newFriends);
        assertAll(
                () -> assertEquals(returned.getID(), registered.getID()),
                () -> assertEquals(returned.getOwner().getID(), registered.getOwner().getID()),
                () -> assertEquals(returned.getFriend().getID(), registered.getFriend().getID())
        );
    }
}
