package controller.service;

import model.dao.FriendsDAO;
import model.entity.User;
import model.entity.Friends;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testGetFriendsList(){
        User user1= new User(101, "name", "test@test.com", "123456", User.NORMAL_PERMISSION);
        User user2 = new User(102, "name2", "test2@test.com", "123456", User.NORMAL_PERMISSION);
        User user3 = new User(103, "name3", "test3@test.com", "123456", User.NORMAL_PERMISSION);
        Friends newFriends = new Friends(10, user1, user2);
        Friends newFriends2 = new Friends(11, user1, user3);
        Friends newFriends3 = new Friends(12, user2, user3);
        Friends newFriends4 = new Friends(13, user3, user1);
        List<Friends> friendList = new ArrayList<>();
        friendList.add(newFriends);
        friendList.add(newFriends2);

        FriendsDAO mockDAO = mock(FriendsDAO.class);
        when(mockDAO.getFriendsByUserId(any(Integer.class))).thenReturn(friendList);
        FriendsService.setDAO(mockDAO);

        List<Friends> returned = FriendsService.getFriendsList(user1);
        assertAll(
                () -> assertEquals(returned.size(), 2),
                () -> assertEquals(returned.get(0).getID(), 10),
                () -> assertEquals(returned.get(1).getID(), 11)
        );
    }


    @Test
    public void testUpdateFriends(){
        User user1= new User(101, "name", "test@test.com", "123456", User.NORMAL_PERMISSION);
        User user2 = new User(102, "name2", "test2@test.com", "123456", User.NORMAL_PERMISSION);
        User user3 = new User(102, "name3", "test3@test.com", "123456", User.NORMAL_PERMISSION);
        Friends newFriends = new Friends(10, user1, user2);
        Friends updated = new Friends(10, user1, user3);

        FriendsDAO mockDAO = mock(FriendsDAO.class);
        when(mockDAO.update(any(Friends.class))).thenReturn(updated);
        FriendsService.setDAO(mockDAO);

        Friends returned = FriendsService.updateFriends(updated);
        assertAll(
                () -> assertEquals(returned.getID(), updated.getID()),
                () -> assertEquals(returned.getOwner().getID(), updated.getOwner().getID()),
                () -> assertEquals(returned.getFriend().getID(), updated.getFriend().getID())
        );
    }


    @Test
    public void smokeTestDeleteFriends() {
        //We need to use Mocks to test the controller layer
        FriendsDAO mockDAO = mock(FriendsDAO.class);

        FriendsService.setDAO(mockDAO);

        assertDoesNotThrow(() -> FriendsService.deleteFriends(1));
    }

}
