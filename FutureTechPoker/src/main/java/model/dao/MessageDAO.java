package model.dao;

import model.entity.Friends;
import model.entity.Message;
import model.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

public class MessageDAO extends GenericDAO<Message> {

    public MessageDAO() {
        super(Message.class);
    }

    public List<Message> getReceivedMessages(int receiverId) {
        EntityManager em = getEntityManager();
        List<Message> messageList = em.createQuery("SELECT m FROM " + getTableName() + " m WHERE m.receiver = :userId", Message.class)
                .setParameter("userId", receiverId)
                .getResultList();
        em.close();
        return messageList;
    }

    public List<Message> getToAndFromMessages(int userId, int otherUserId) {

        EntityManager em = getEntityManager();
        List<Message> messageList = em.createQuery("SELECT m FROM " + getTableName() + " m WHERE (m.receiver = :userId AND m.sender = :otherId) OR ( m.sender = :userId AND m.receiver = :otherId)", Message.class)
                .setParameter("userId", userId)
                .setParameter("otherId", otherUserId)
                .getResultList();
        em.close();
        return messageList;
    }
}
