package controller.service;

import model.dao.FriendsDAO;
import model.dao.MessageDAO;
import model.entity.Message;

import java.util.List;

public class MessageService {

    public static MessageDAO dao = new MessageDAO();
    public static void setDAO(MessageDAO dao){
        MessageService.dao = dao;
    }

    public static Message addMessage(Message message){
        return dao.create(message);
    }

    public static void deleteMessage(int id){
        dao.delete(id);
    }

    public static Message updateMessage(Message message){
        return dao.update(message);
    }

    public static Message readMessage(int messageId){
        return dao.read(messageId);
    }

    public static List<Message> getMessages(int receiverId){
        return dao.getReceivedMessages(receiverId);
    }

    public static List<Message> getToAndFromMessages(int userId, int otherUserId){
        return dao.getToAndFromMessages(userId, otherUserId);
    }

}
