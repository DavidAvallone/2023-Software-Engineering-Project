package controller.service;

import model.dao.FriendsDAO;
import model.dao.MessageDAO;
import model.entity.Message;
import model.entity.User;
import model.entity.Friends;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*; //Mock Framework
import static org.junit.jupiter.api.Assertions.*;

public class MessageServiceTest {

    @Test
    public void addMessageTest(){
        User user1= new User(101, "name", "test@test.com", "123456", User.NORMAL_PERMISSION);
        User user2 = new User(102, "name2", "test2@test.com", "123456", User.NORMAL_PERMISSION);

        Message newMessage = new Message(null, user1.getID(), user2.getID(), "Message");
        Message registered = new Message(10, user1.getID(), user2.getID(), "Message");

        MessageDAO mockDAO = mock(MessageDAO.class);
        when(mockDAO.create(any(Message.class))).thenReturn(registered);
        MessageService.setDAO(mockDAO);

        Message returned = MessageService.addMessage(newMessage);
        assertAll(
                () -> assertEquals(returned.getID(), registered.getID()),
                () -> assertEquals(returned.getReceiver(), registered.getReceiver()),
                () -> assertEquals(returned.getSender(), registered.getSender()),
                () -> assertEquals(returned.getMessage(), registered.getMessage())
        );
    }

    @Test
    public void smokeTestDeleteMessage() {

        MessageDAO mockDAO = mock(MessageDAO.class);

        MessageService.setDAO(mockDAO);

        assertDoesNotThrow(() -> MessageService.deleteMessage(1));
    }

    @Test
    public void testUpdateMessage(){
        User user1= new User(101, "name", "test@test.com", "123456", User.NORMAL_PERMISSION);
        User user2 = new User(102, "name2", "test2@test.com", "123456", User.NORMAL_PERMISSION);
        User user3 = new User(102, "name3", "test3@test.com", "123456", User.NORMAL_PERMISSION);
        Message newMessage = new Message(10, user1.getID(), user2.getID(), "Message");
        Message updated = new Message(10, user1.getID(), user3.getID(), "Message");

        MessageDAO mockDAO = mock(MessageDAO.class);
        when(mockDAO.update(any(Message.class))).thenReturn(updated);
        MessageService.setDAO(mockDAO);

        Message returned = MessageService.updateMessage(newMessage);
        assertAll(
                () -> assertEquals(returned.getID(), updated.getID()),
                () -> assertEquals(returned.getSender(), updated.getSender()),
                () -> assertEquals(returned.getReceiver(), user3.getID()),
                () -> assertEquals(returned.getMessage(), updated.getMessage())
        );
    }
    @Test
    public void testReadMessage(){
        User user1= new User(101, "name", "test@test.com", "123456", User.NORMAL_PERMISSION);
        User user2 = new User(102, "name2", "test2@test.com", "123456", User.NORMAL_PERMISSION);

        Message newMessage = new Message(null, user1.getID(), user2.getID(), "Message");
        Message registered = new Message(10, user1.getID(), user2.getID(), "Message");

        MessageDAO mockDAO = mock(MessageDAO.class);
        when(mockDAO.read(any(int.class))).thenReturn(registered);
        MessageService.setDAO(mockDAO);

        Message returned = MessageService.readMessage(registered.getID());
        assertAll(
                () -> assertEquals(returned.getID(), registered.getID()),
                () -> assertEquals(returned.getReceiver(), registered.getReceiver()),
                () -> assertEquals(returned.getSender(), registered.getSender()),
                () -> assertEquals(returned.getMessage(), registered.getMessage())
        );
    }

    @Test
    public void testGetMessages(){
        User user1= new User(101, "name", "test@test.com", "123456", User.NORMAL_PERMISSION);
        User user2 = new User(102, "name2", "test2@test.com", "123456", User.NORMAL_PERMISSION);
        User user3 = new User(103, "name3", "test3@test.com", "123456", User.NORMAL_PERMISSION);
        Message newMessage = new Message(10, user1.getID(), user2.getID(), "1");
        Message newMessage2 = new Message(11, user1.getID(), user2.getID(), "2");


        List<Message> messageList = new ArrayList<>();
        messageList.add(newMessage);
        messageList.add(newMessage2);

        MessageDAO mockDAO = mock(MessageDAO.class);
        when(mockDAO.getReceivedMessages(any(Integer.class))).thenReturn(messageList);
        MessageService.setDAO(mockDAO);

        List<Message> returned = MessageService.getMessages(user1.getID());
        assertAll(
                () -> assertEquals(returned.size(), 2),
                () -> assertEquals(returned.get(0).getID(), 10),
                () -> assertEquals(returned.get(1).getID(), 11)
        );
    }

    @Test
    public void testGetToAndFromMessages(){
        User user1= new User(101, "name", "test@test.com", "123456", User.NORMAL_PERMISSION);
        User user2 = new User(102, "name2", "test2@test.com", "123456", User.NORMAL_PERMISSION);
        User user3 = new User(103, "name3", "test3@test.com", "123456", User.NORMAL_PERMISSION);
        Message newMessage = new Message(10, user1.getID(), user2.getID(), "1");
        Message newMessage2 = new Message(11, user2.getID(), user1.getID(), "2");


        List<Message> messageList = new ArrayList<>();
        messageList.add(newMessage);
        messageList.add(newMessage2);

        MessageDAO mockDAO = mock(MessageDAO.class);
        when(mockDAO.getToAndFromMessages(any(Integer.class), any(Integer.class))).thenReturn(messageList);
        MessageService.setDAO(mockDAO);

        List<Message> returned = MessageService.getToAndFromMessages(user1.getID(), user2.getID());
        assertAll(
                () -> assertEquals(returned.size(), 2),
                () -> assertEquals(returned.get(0).getID(), 10),
                () -> assertEquals(returned.get(1).getID(), 11)
        );
    }

}
