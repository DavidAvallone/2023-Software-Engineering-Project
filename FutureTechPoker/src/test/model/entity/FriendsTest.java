package model.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FriendsTest {
    @Test public void testEmptyUser(){
        Friends f = new Friends();
        assertAll(
                ()-> assertNull(f.getID()),
                ()-> assertNull(f.getOwner()),
                ()-> assertNull(f.getFriend())
        );
    }

    @Test public void testConstructor(){
        User owner = new User(1, "Name", "Login@gmail.com", "password", User.NORMAL_PERMISSION);
        User friend = new User(2, "Name2", "Login2@gmail.com", "password2", User.NORMAL_PERMISSION);
        Friends f = new Friends(owner.getID(), friend.getID());
        assertAll(
                ()-> assertEquals(f.getOwner(), owner.getID()),
                ()->assertEquals(f.getFriend(), friend.getID())
        );
    }
    @Test public void testSetOwnerAndFriend(){
        User owner = new User(1, "Name", "Login@gmail.com", "password", User.NORMAL_PERMISSION);
        User friend = new User(2, "Name2", "Login2@gmail.com", "password2", User.NORMAL_PERMISSION);
        Friends f = new Friends();
        f.setOwner(owner.getID());
        f.setFriend(friend.getID());
        assertAll(
                ()-> assertEquals(f.getOwner(), owner.getID()),
                ()->assertEquals(f.getFriend(), friend.getID())
        );
    }

}
