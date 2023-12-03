package model.dao;

import model.entity.Friends;
import model.entity.Message;
import model.entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageDAOTest {

    public static MessageDAO dao = null;
    private static UserDAO udao = null;


    @BeforeAll
    public static void createDao() {
        dao = new MessageDAO();
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
//    public static Friends createNewMessageEntity() {
//        User u1 = createNewUserEntity("test@test.com","Bob", "111", User.NORMAL_PERMISSION );
//        User u2 = createNewUserEntity("email@test.com","Timmy","222", User.NORMAL_PERMISSION );
//        return new Friends(u1.getID(), u2.getID());
//   }
    public static User createNewUserEntity(String login, String name, String password, int permission){
        User u = new User();
        u.setLogin(login);
        u.setName(name);
        u.setPassword(password);
        u.setPermission(permission);
        udao.create(u);
        return u;
    }

    @Test
    public void testCreateMessage() {

        User user1 = createNewUserEntity("login", "name", "password", User.NORMAL_PERMISSION);
        User user2 = createNewUserEntity("login2", "name2", "password2", User.NORMAL_PERMISSION);
        Message m1 = new Message(user1.getID(), user2.getID(), "1");
        dao.create(m1);
        Message found = dao.read(m1.getID());
        assertAll(
                () -> assertNotNull(m1.getID(), "ID should not be null after creation"),
                () -> assertNotNull(found, "Found Message after reading should not be null"),
                () -> assertEquals(found.getSender(), m1.getSender()),
                () -> assertEquals(found.getReceiver(), m1.getReceiver()),
                () -> assertEquals(found.getMessage(), m1.getMessage())
        );
    }

    @Test
    public void testGetReceivedMessages() {
        User user1 = createNewUserEntity("login", "name", "password", User.NORMAL_PERMISSION);
        User user2 = createNewUserEntity("login2", "name2", "password2", User.NORMAL_PERMISSION);
        Message m1 = new Message(user1.getID(), user2.getID(), "1");
        Message m2 = new Message(user1.getID(), user2.getID(), "2");
        Message m3 = new Message(user2.getID(), user1.getID(), "3");
        dao.create(m1);
        dao.create(m2);
        dao.create(m3);
        List<Message> messages = dao.getReceivedMessages(user2.getID());

        assertAll(
                () -> assertNotNull(messages),
                () -> assertEquals(messages.size(), 2),
                () -> assertEquals(messages.get(0).getMessage(),m1.getMessage()),
                () -> assertEquals(messages.get(1).getMessage(),m2.getMessage())
        );
    }
    @Test
    public void testGetToAndFromMessages() {
        User user1 = createNewUserEntity("login", "name", "password", User.NORMAL_PERMISSION);
        User user2 = createNewUserEntity("login2", "name2", "password2", User.NORMAL_PERMISSION);
        Message m1 = new Message(user1.getID(), user2.getID(), "1");
        Message m2 = new Message(user1.getID(), user2.getID(), "2");
        Message m3 = new Message(user2.getID(), user1.getID(), "3");
        dao.create(m1);
        dao.create(m2);
        dao.create(m3);
        List<Message> messages = dao.getToAndFromMessages(user1.getID(), user2.getID());

        assertAll(
                () -> assertNotNull(messages),
                () -> assertEquals(messages.size(), 3),
                () -> assertEquals(messages.get(0).getMessage(),m1.getMessage()),
                () -> assertEquals(messages.get(1).getMessage(),m2.getMessage()),
                () -> assertEquals(messages.get(2).getMessage(),m3.getMessage())
        );
    }
}