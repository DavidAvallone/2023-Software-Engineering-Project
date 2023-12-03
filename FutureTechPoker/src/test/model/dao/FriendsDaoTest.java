package model.dao;

import model.entity.User;
import model.entity.Friends;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FriendsDaoTest {

    public static FriendsDAO dao = null;
    private static UserDAO udao = null;


    @BeforeAll
    public static void createDao() {
        dao = new FriendsDAO();
        udao = new UserDAO();
        dao.setDbTypeOutput(GenericDAO.DbType.TEST);
        udao.setDbTypeOutput(GenericDAO.DbType.TEST);
    }

    @BeforeEach
    public void deleteAll() {
        dao.deleteAll();
        udao.deleteAll();
    }

    @AfterAll
    public static void deleteAllAfter() {
        dao.deleteAll();
        udao.deleteAll();
    }
    public static Friends createNewFriendsEntity() {
        User u1 = createNewUserEntity("test@test.com","Bob", "111", User.NORMAL_PERMISSION );
        User u2 = createNewUserEntity("email@test.com","Timmy","222", User.NORMAL_PERMISSION );
        return new Friends(u1.getID(), u2.getID());
    }
    public static User createNewUserEntity(String login, String name, String password, int permission){
        User u = new User();
        u.setLogin(login);
        u.setName(name);
        u.setPassword(password);
        u.setPermission(permission);
        udao.create(u);
        return u;
    }

    @Test public void testCreateFriends(){

        Friends newFriends = createNewFriendsEntity();

        dao.create(newFriends);
        Friends found = dao.read(newFriends.getID());
        assertAll("Grouped Assertions of Create Friends",
                () -> assertNotNull(newFriends.getID(), "ID should not be null after creation"),
                () -> assertNotNull(found, "Found Friends after reading should not be null"),
                () -> assertEquals(found.getOwner(), newFriends.getOwner()),
                () -> assertEquals(found.getFriend(), newFriends.getFriend())
        );
    }

    @Test public void testDeleteFriendsById(){
        Friends newFriends = createNewFriendsEntity();
        dao.create(newFriends);
        dao.delete(newFriends.getID());
        Friends found = dao.read(newFriends.getID());
        assertNull(found, "Friends cannot be in the DB after deletion");
    }

    @Test public void smokeTestDeleteWhatDoesNotExists(){
        //Smoke test has no assertion, we are only testing if this does not raise any exceptions
        Friends notsaved = createNewFriendsEntity();
        assertDoesNotThrow(() -> dao.delete(notsaved));
    }
    @Test public void testGetFriendsByUserIdEmpty(){
        Friends newFriends = createNewFriendsEntity();
        List<Friends> friendsList = dao.getFriendsByUserId(newFriends.getOwner());
        assertTrue(friendsList.isEmpty());
    }

    @Test public void testUpdateFriends(){
        Friends newFriends = createNewFriendsEntity();
        dao.create(newFriends);
        User newUser = createNewUserEntity("altered", "Danny", "password", User.NORMAL_PERMISSION);
        newFriends.setFriend(newUser.getID());
        Friends updated = dao.update(newFriends);
        Friends found = dao.read(newFriends.getID());
        assertAll("Grouped Assertions of Updated User",
                () -> assertEquals(updated.getID(), found.getID()),
                () -> assertEquals(updated.getFriend(),newFriends.getFriend()),
                () -> assertEquals(updated.getOwner(),found.getOwner())
        );
    }

    @Test public void testList(){
        User u1 = createNewUserEntity("login1", "name1", "password1", User.NORMAL_PERMISSION);
        User u2 = createNewUserEntity("login2", "name2", "password2", User.NORMAL_PERMISSION);
        User u3 = createNewUserEntity("login3", "name3", "password3", User.NORMAL_PERMISSION);
        User u4 = createNewUserEntity("login4", "name4", "password4", User.NORMAL_PERMISSION);
        User u5 = createNewUserEntity("login5", "name5", "password5", User.NORMAL_PERMISSION);
        User u6 = createNewUserEntity("login6", "name6", "password6", User.NORMAL_PERMISSION);

        Friends friend1 = new Friends(u1.getID(), u2.getID());
        Friends friend2 = new Friends(u3.getID(), u4.getID());
        Friends friend3= new Friends(u5.getID(), u6.getID());
        dao.create(friend1);
        dao.create(friend2);
        dao.create(friend3);
        List<Friends> friendsList = dao.list("id");
        assertEquals(friendsList.size(), 3);
    }

    @Test public void testGetFriendsByUserId(){
        Friends newFriends = createNewFriendsEntity();
        dao.create(newFriends);
        List<Friends> friendsList = dao.getFriendsByUserId(newFriends.getOwner());
        assertAll(
                () -> assertFalse(friendsList.isEmpty()),
                () -> assertEquals(friendsList.get(0).getOwner(), newFriends.getOwner()),
                () -> assertEquals(friendsList.get(0).getFriend(), newFriends.getFriend())
                );
    }

    @Test public void testGetFriendsByFriendPair(){
        Friends newFriends = createNewFriendsEntity();
        dao.create(newFriends);
        Friends friends = dao.getFriendsByFriendPair(newFriends.getOwner(), newFriends.getFriend());
        assertAll(
                () -> assertNotNull(friends),
                () -> assertEquals(friends.getOwner(), newFriends.getOwner()),
                () -> assertEquals(friends.getFriend(), newFriends.getFriend())
        );
    }
}

